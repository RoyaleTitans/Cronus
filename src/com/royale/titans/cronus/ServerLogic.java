package com.royale.titans.cronus;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.Crypto;
import com.royale.titans.cronus.lib.Nonce;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.Headers;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.client.*;
import com.royale.titans.cronus.messages.client.AskForAvatarStreamMessage;
import com.royale.titans.cronus.messages.server.*;

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
    private final LinkedHashMap<String, CronusChatBattleEvent> mBattleChatEventsSessionMap;

    private final SecureRandom mSecureRandom;

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
        mBattleChatEventsSessionMap = new LinkedHashMap<>();

        mWorker = new ServerWorker();

        System.out.println("[SERVER-LOGIC] Cronus logic initialized");
    }

    void initialize() {}

    ClientInfo openSession(AsynchronousSocketChannel socket, String sessionKey) {
        ClientInfo clientInfo = new ClientInfo(socket, sessionKey);
        mSessions.put(sessionKey, clientInfo);
        return clientInfo;
    }

    void closeSession(String sessionKey) {
        mSessions.remove(sessionKey);
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

    public LinkedHashMap<String, CronusChatBattleEvent> getBattleChatEventsSessionMap() {
        return mBattleChatEventsSessionMap;
    }

    public void postMessage(ClientInfo clientInfo, ServerMessage serverMessage) {
        Buffer b = serverMessage.getBuffer();
        Buffer encrypted = Crypto.encrypt(clientInfo, serverMessage.getId(), b);
        if (encrypted != null) {
            Headers headers = new Headers(serverMessage.getId(),
                    encrypted.capacity(),
                    serverMessage.getVersion());
            clientInfo.getSocket().write(headers.toBuffer().getByteBuffer());

            if (Configs.DEBUG) {
                System.out.println("[SERVER] [OUT] msgId: " + headers.getId() + " - len: " + headers.getLength());
            }

            clientInfo.getSocket().write(encrypted.getByteBuffer());
            encrypted.clear();

            if (Configs.DEBUG) {
                b.rewind();
                System.out.println("[SERVER] [OUT]: " + Utils.b2h(
                        serverMessage.getBuffer().array()));
            }
        }
    }

    public void postMessage(AsynchronousSocketChannel socket, ServerMessage serverMessage) {
        Buffer b = serverMessage.getBuffer();
        if (b != null) {
            Headers headers = new Headers(serverMessage.getId(),
                    b.capacity(),
                    serverMessage.getVersion());
            socket.write(headers.toBuffer().getByteBuffer());

            if (Configs.DEBUG) {
                System.out.println("[SERVER] [OUT] msgId: " + headers.getId() + " - len: " + headers.getLength());
            }

            socket.write(b.getByteBuffer());
            b.clear();

            if (Configs.DEBUG) {
                b.rewind();
                System.out.println("[SERVER] [OUT]: " + Utils.b2h(
                        serverMessage.getBuffer().array()));
            }
        }
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
            case 10554:
                return new SendChatMessageEvent(info, buffer);
            case 10609:
                return new GetClanInfo(info, buffer);
            case 11688:
                return new ClientStatus(info, buffer);
            case 12269:
                return new AskBattleQueueLeave(info, buffer);
            case 15827:
                return new AskForBattleReplayStream(info, buffer);
            case 15860:
                return new AskCronusBattleQueueLeave(info, buffer);
            case 17101:
                return new AskForAvatarStreamMessage(info, buffer);
            case 18688:
                return new AskForGameRoom(info, buffer);
            case 19911:
                return new ClientKeepAlive(info, buffer);
        }

        return null;
    }

    ServerMessage[] handle(ClientInfo clientInfo, Headers headers, ClientMessage clientMessage) {
        switch (headers.getId()) {
            case 10100:
                if (!((ClientHello) clientMessage).getFingerprint().equals(Configs.FINGERPRINT)) {
                    return new ServerMessage[] {
                            new LoginFailed(7)
                    };
                } else {
                    return new ServerMessage[] {
                            new ServerHello()
                    };
                }
            case 10101:
                return new ServerMessage[] {
                        new LoginOk(clientInfo.getClientId()),
                        new OwnHomeData(clientInfo.getClientId()),
                        new CronusChat()
                };
            case 10554:
                mWorker.scheduleJob(new ServerWorker.WorkerTask(
                        ServerWorker.WorkerTask.TASK.POST_CRONUS_CHAT_MESSAGE, clientMessage));
                return new ServerMessage[0];
            case 10609:
                return new ServerMessage[] {
                        new CronusClanInfo()
                };
            case 11688:
                return new ServerMessage[0];
            case 12269:
                return new ServerMessage[] {
                        new BattleQueueLeave(false),
                        new BattleQueueLeaveConfirm()
                };
            case 17101:
                return new ServerMessage[0];
            case 15860:
                mWorker.scheduleJob(new ServerWorker.WorkerTask(
                        ServerWorker.WorkerTask.TASK.POST_CRONUS_CHAT_GAME_QUEUE_CANCELLED,
                        clientInfo));
                return new ServerMessage[] {
                        new CronusBattleLeaveConfirm()
                };
            case 18688:
                AskForGameRoom askForGameRoom = (AskForGameRoom) clientMessage;
                if (askForGameRoom.isClanFriendlyMatch()) {
                    mWorker.scheduleJob(new ServerWorker.WorkerTask(
                            ServerWorker.WorkerTask.TASK.POST_CRONUS_CHAT_GAME_QUEUE_START,
                            clientInfo));
                } else {
                    if (askForGameRoom.haveCommand()) {
                        return new ServerMessage[] {
                                new BattleQueueLeave(false),
                                new BattleQueueLeaveConfirm()
                        };
                    }
                }
            case 19911:
                return new ServerMessage[] {
                        new ServerKeepAlive()
                };
        }

        return null;
    }

    private static class ServerWorker extends Thread {
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
                            CronusChatEvent chatEvent = new CronusChatEvent(sendChatMessageEvent.getSenderInfo(),
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
                        case POST_CRONUS_CHAT_GAME_QUEUE_START: {
                            ClientInfo info = (ClientInfo) workerTask.getData()[0];
                            int roomSlotId = BattleLogic.getInstance().queueBattle(info);
                            CronusChatBattleEvent gameQueueEvent = new CronusChatBattleEvent(info, roomSlotId);

                            List<CronusChatBattleEvent> battleChatEvents =
                                    ServerLogic.getInstance().getBattleChatEvents();
                            LinkedHashMap<String, CronusChatBattleEvent> battleChatEventsSessionMap =
                                    ServerLogic.getInstance().getBattleChatEventsSessionMap();

                            battleChatEvents.add(gameQueueEvent);
                            battleChatEventsSessionMap.put(info.getSessionKey(), gameQueueEvent);

                            for (ClientInfo clientInfo : ServerLogic.getInstance().getSessions()) {
                                ServerLogic.getInstance().postMessage(clientInfo, gameQueueEvent);
                            }
                        }
                        case POST_CRONUS_CHAT_GAME_QUEUE_CANCELLED: {
                            ClientInfo info = (ClientInfo) workerTask.getData()[0];

                            LinkedHashMap<String, CronusChatBattleEvent> battleChatEventsSessionMap =
                                    ServerLogic.getInstance().getBattleChatEventsSessionMap();
                            CronusChatBattleEvent battleChatEvent = battleChatEventsSessionMap.remove(info.getSessionKey());
                            if (battleChatEvent != null) {
                                ServerLogic.getInstance().getBattleChatEvents().remove(battleChatEvent);

                                BattleLogic.getInstance().cancelBattle(info.getSessionKey());

                                CronusChatMessageRemove cronusChatMessageRemove =
                                        new CronusChatMessageRemove(battleChatEvent.getSlotId());

                                for (ClientInfo clientInfo : ServerLogic.getInstance().getSessions()) {
                                    ServerLogic.getInstance().postMessage(clientInfo, cronusChatMessageRemove);
                                }
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        void scheduleJob(WorkerTask workerTask) {
            try {
                mWorkerTasks.put(workerTask);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        static class WorkerTask {
            enum TASK {
                POST_CRONUS_CHAT_MESSAGE,
                POST_CRONUS_CHAT_GAME_QUEUE_START,
                POST_CRONUS_CHAT_GAME_QUEUE_CANCELLED
            }

            final TASK mTask;
            final Object[] mData;

            WorkerTask(TASK task, Object... data) {
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

    public static class ClientInfo {
        private final AsynchronousSocketChannel mSocket;
        private final String mSessionKey;
        private final String mPlayerName;
        private final long mClientId;
        private final Nonce mR;

        private Nonce mS;

        ClientInfo(AsynchronousSocketChannel socket, String sessionKey) {
            mSocket = socket;
            mSessionKey = sessionKey;
            mClientId = ServerLogic.getInstance().getRandom().nextLong();
            mPlayerName = String.valueOf(mClientId);
            mR = new Nonce(new byte[24]);
        }

        AsynchronousSocketChannel getSocket() {
            return mSocket;
        }

        String getSessionKey() {
            return mSessionKey;
        }

        public String getPlayerName() {
            return mPlayerName;
        }

        public long getClientId() {
            return mClientId;
        }

        public int getClientIdHigh() {
            return (int) (mClientId >> 32);
        }

        public int getClientIdLow() {
            return (int) (mClientId);
        }

        public void setNonce(Nonce sNonce) {
            mS = sNonce;
        }

        public Nonce sNonce() {
            return mS;
        }

        public Nonce rNonce() {
            return mR;
        }
    }
}
