package com.royale.titans.cronus;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.Crypto;
import com.royale.titans.cronus.lib.Nonce;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.Configs;
import com.royale.titans.cronus.messages.Headers;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.client.*;
import com.royale.titans.cronus.messages.client.AskForAvatarStreamMessage;
import com.royale.titans.cronus.messages.server.*;

import java.nio.channels.AsynchronousSocketChannel;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedTransferQueue;

public class ServerLogic {
    private static ServerLogic sInstance;

    private final ServerWorker mWorker;

    private final ConcurrentHashMap<String, ClientInfo> mSessions;
    private final List<CronusChatEvent> mChatEvents;

    private final SecureRandom mSecureRandom;

    public static synchronized ServerLogic getInstance() {
        if (sInstance == null) {
            sInstance = new ServerLogic();
        }

        return sInstance;
    }

    private ServerLogic() {
        mSessions = new ConcurrentHashMap<>();
        mChatEvents = new ArrayList<>();
        mSecureRandom = new SecureRandom();

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
            case 15827:
                return new AskForBattleReplayStream(info, buffer);
            case 17101:
                return new AskForAvatarStreamMessage(info, buffer);
            case 19911:
                return new ClientKeepAlive(info, buffer);
        }

        return null;
    }

    ServerMessage[] handle(ClientInfo clientInfo, Headers headers, ClientMessage clientMessage) {
        switch (headers.getId()) {
            case 10100:
                if (!((ClientHello) clientMessage).getFringerPrint().equals(Configs.FINGERPRINT)) {
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
            case 17101:
                return new ServerMessage[0];
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
                        case POST_CRONUS_CHAT_MESSAGE:
                            SendChatMessageEvent sendChatMessageEvent = (SendChatMessageEvent) workerTask.getData()[0];
                            CronusChatEvent chatEvent = new CronusChatEvent(CronusChatEvent.CHAT_EVENT_MESSAGE,
                                    sendChatMessageEvent.getSenderInfo().getPlayerName(),
                                    sendChatMessageEvent.getContent());

                            List<CronusChatEvent> chatEvents = ServerLogic.getInstance().getChatEvents();
                            chatEvents.add(chatEvent);
                            if (chatEvents.size() > 75) {
                                while (chatEvents.size() != 75) {
                                    chatEvents.remove(0);
                                }
                            }

                            for (ClientInfo info : ServerLogic.getInstance().getSessions()) {
                                ServerLogic.getInstance().postMessage(info, chatEvent);
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

        void scheduleJob(WorkerTask workerTask) {
            try {
                mWorkerTasks.put(workerTask);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        static class WorkerTask {
            enum TASK {
                POST_CRONUS_CHAT_MESSAGE
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

        String getPlayerName() {
            return mPlayerName;
        }

        long getClientId() {
            return mClientId;
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
