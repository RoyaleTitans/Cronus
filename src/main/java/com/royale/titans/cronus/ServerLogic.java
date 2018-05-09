package com.royale.titans.cronus;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.CronusClanInfo;
import com.royale.titans.cronus.lib.Crypto;
import com.royale.titans.cronus.lib.HashTag;
import com.royale.titans.cronus.lib.Nonce;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.Headers;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.client.*;
import com.royale.titans.cronus.messages.client.AskForAvatarStreamMessage;
import com.royale.titans.cronus.messages.server.*;
import com.royale.titans.cronus.models.ClientInfo;

import java.nio.channels.AsynchronousSocketChannel;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedTransferQueue;

public class ServerLogic {
    private static ServerLogic sInstance;

    private final ServerWorker mWorker;

    private final ConcurrentHashMap<String, ClientInfo> mSessions;
    private final List<CronusChatEvent> mChatEvents;

    private final List<CronusChatBattleEvent> mBattleChatEvents;
    private final LinkedHashMap<String, CronusChatBattleEvent> mBattleChatEventsTagMap;

    private final SecureRandom mSecureRandom;

    private final CronusClanInfo mCronusClanInfo;

    public static synchronized ServerLogic getInstance() {
        if (sInstance == null) {
            sInstance = new ServerLogic();
        }

        return sInstance;
    }

    private ServerLogic() {
        mSessions = new ConcurrentHashMap<>();
        mSecureRandom = new SecureRandom();

        mChatEvents = new ArrayList<>();

        mBattleChatEvents = new ArrayList<>();
        mBattleChatEventsTagMap = new LinkedHashMap<>();

        mCronusClanInfo = new CronusClanInfo(HashTag.randomHashTag());

        mWorker = new ServerWorker();

        System.out.println("[SERVER-LOGIC] Cronus logic initialized");
    }

    void initialize() {

    }

    ClientInfo openSession(AsynchronousSocketChannel socket, String sessionKey) {
        ClientInfo clientInfo = new ClientInfo(socket, sessionKey);
        mSessions.put(sessionKey, clientInfo);
        return clientInfo;
    }

    void closeSession(String sessionKey) {
        ClientInfo info = mSessions.remove(sessionKey);

        // Check for pending matches
        if (info.getClientId() != null && mBattleChatEventsTagMap.get(info.getClientId().toString()) != null) {
            mWorker.scheduleTask(new ServerWorker.WorkerTask(
                    ServerWorker.TASK.POST_CRONUS_CHAT_GAME_QUEUE_CANCELLED,
                    info));
        }
    }

    public ClientInfo findSession(String sessionKey) {
        return mSessions.get(sessionKey);
    }

    public ArrayList<ClientInfo> getSessions() {
        return new ArrayList<>(mSessions.values());
    }

    public SecureRandom getRandom() {
        return mSecureRandom;
    }

    public List<CronusChatEvent> getChatEvents() {
        return mChatEvents;
    }

    public List<CronusChatBattleEvent> getBattleChatEvents() {
        return mBattleChatEvents;
    }

    public LinkedHashMap<String, CronusChatBattleEvent> getBattleChatEventsTagMap() {
        return mBattleChatEventsTagMap;
    }

    public CronusClanInfo getCronusClanInfo() {
        return mCronusClanInfo;
    }

    public void postMessage(ClientInfo clientInfo, ServerMessage serverMessage) {
        Buffer b = serverMessage.getBuffer();
        Buffer encrypted = Crypto.encrypt(clientInfo, serverMessage.getId(), b);
        if (encrypted != null) {
            post(clientInfo.getSocket(), serverMessage.getId(), serverMessage.getVersion(),
                    encrypted);
        }
    }

    public void postMessage(AsynchronousSocketChannel socket, ServerMessage serverMessage) {
        Buffer b = serverMessage.getBuffer();
        if (b != null) {
            post(socket, serverMessage.getId(), serverMessage.getVersion(),
                    serverMessage.getBuffer());
        }
    }

    private void post(AsynchronousSocketChannel socket, int id, int version, Buffer b) {
        Headers headers = new Headers(id, b.capacity(),
                version);
        socket.write(headers.toBuffer().getByteBuffer());

        if (Configs.DEBUG) {
            System.out.println("[SERVER] [OUT] msgId: " + headers.getId() + " - len: " + headers.getLength());
        }

        socket.write(b.getByteBuffer());
        b.clear();
    }

    ClientMessage route(ClientInfo info, Headers headers, Buffer buffer) {
        if (headers.getId() != 10100) {
            buffer = Crypto.decrypt(info, headers.getId(),buffer);
        }

        switch (headers.getId()) {
            case 10100:
                return new ClientHello(info, buffer);
            case 10101:
                return new Login(info, buffer);
            case 10644:
                return new SendChatMessageEvent(info, buffer);
            case 11688:
                return new ClientStatus(info, buffer);
            case 12337:
                return new ClientKeepAlive(info, buffer);
            case 13793:
                return new AskCronusBattleQueueLeave(info, buffer);
            case 14041:
                return new GoHome(info, buffer);
            case 14361:
                return new AskForGameRoom(info, buffer);
            case 15083:
                return new GetClanInfo(info, buffer);
            case 15827:
                return new AskForBattleReplayStream(info, buffer);
            case 17101:
                return new AskForAvatarStreamMessage(info, buffer);
            case 17509:
                return new ClientBattleEvent(info, buffer);
            case 19234:
                return new SectorCommand(info, buffer);
            case 19939:
                return new CronusBattleAccepted(info, buffer);
            case 9999:
                // todo (find this id)
                return new AskBattleQueueLeave(info, buffer);
        }

        return null;
    }

    public void scheduleTask(ServerWorker.WorkerTask workerTask) {
        mWorker.scheduleTask(workerTask);
    }

    public static class ServerWorker extends Thread {
        public enum TASK {
            POST_CRONUS_CHAT_MESSAGE,
            POST_CRONUS_CHAT_GAME_QUEUE_START,
            POST_CRONUS_CHAT_GAME_QUEUE_CANCELLED,
            POST_CRONUS_CHAT_GAME_START,
        }

        private final BlockingQueue<WorkerTask> mWorkerTasks = new LinkedTransferQueue<>();

        ServerWorker() {
            start();
        }

        @Override
        public void run() {
            while (true) {
                WorkerTask workerTask;
                while ((workerTask = mWorkerTasks.poll()) != null) {
                    switch (workerTask.getTask()) {
                        case POST_CRONUS_CHAT_MESSAGE: {
                            SendChatMessageEvent sendChatMessageEvent = (SendChatMessageEvent) workerTask.getData()[0];
                            CronusChatEvent chatEvent = new CronusChatEvent(sendChatMessageEvent.getClientInfo(),
                                    CronusChatEvent.CHAT_EVENT_MESSAGE,
                                    sendChatMessageEvent.getContent());

                            List<CronusChatEvent> chatEvents = ServerLogic.getInstance().getChatEvents();
                            chatEvents.add(chatEvent);


                            if (chatEvents.size() > 75) {
                                while (chatEvents.size() != 75) {
                                    chatEvents.remove(0);
                                }
                            }

                            for (ClientInfo clientInfo : ServerLogic.getInstance().getSessions()) {
                                ServerLogic.getInstance().postMessage(clientInfo, chatEvent);
                            }
                        }
                        break;
                        case POST_CRONUS_CHAT_GAME_QUEUE_START: {
                            AskForGameRoom askForGameRoom = (AskForGameRoom) workerTask.getData()[0];
                            int roomSlotId = BattleLogic.getInstance().queueBattle(askForGameRoom);
                            CronusChatBattleEvent gameQueueEvent = new CronusChatBattleEvent(
                                    askForGameRoom.getClientInfo(), roomSlotId);

                            List<CronusChatBattleEvent> battleChatEvents =
                                    ServerLogic.getInstance().getBattleChatEvents();
                            LinkedHashMap<String, CronusChatBattleEvent> battleChatEventsSessionMap =
                                    ServerLogic.getInstance().getBattleChatEventsTagMap();

                            battleChatEvents.add(gameQueueEvent);
                            battleChatEventsSessionMap.put(askForGameRoom.getClientInfo()
                                    .getClientId().toString(), gameQueueEvent);

                            for (ClientInfo clientInfo : ServerLogic.getInstance().getSessions()) {
                                ServerLogic.getInstance().postMessage(clientInfo, gameQueueEvent);
                            }
                        }
                        break;
                        case POST_CRONUS_CHAT_GAME_QUEUE_CANCELLED: {
                            ClientInfo info = (ClientInfo) workerTask.getData()[0];

                            LinkedHashMap<String, CronusChatBattleEvent> battleChatEventsSessionMap =
                                    ServerLogic.getInstance().getBattleChatEventsTagMap();
                            CronusChatBattleEvent battleChatEvent = battleChatEventsSessionMap.remove(
                                    info.getClientId().toString());
                            if (battleChatEvent != null) {
                                ServerLogic.getInstance().getBattleChatEvents().remove(battleChatEvent);

                                BattleLogic.getInstance().cancelBattle(info.getClientId().toString());
                            }
                        }
                        break;
                        case POST_CRONUS_CHAT_GAME_START: {
                            CronusChatBattleEvent battleEvent = (CronusChatBattleEvent) workerTask.getData()[0];
                            if (battleEvent != null) {
                                for (ClientInfo clientInfo : ServerLogic.getInstance().getSessions()) {
                                    ServerLogic.getInstance().postMessage(clientInfo, battleEvent);
                                }
                            }
                        }
                        break;
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        void scheduleTask(WorkerTask workerTask) {
            try {
                mWorkerTasks.put(workerTask);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static class WorkerTask {
            final TASK mTask;
            final Object[] mData;

            public WorkerTask(TASK task, Object... data) {
                mTask = task;
                mData = data;
            }

            TASK getTask() {
                return mTask;
            }

            Object[] getData() {
                return mData;
            }
        }
    }
}
