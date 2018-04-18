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

import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;

public class ServerLogic {
    private static ServerLogic sInstance;

    private final ConcurrentHashMap<String, ClientInfo> mSessions;

    static synchronized ServerLogic getInstance() {
        if (sInstance == null) {
            sInstance = new ServerLogic();
        }

        return sInstance;
    }

    private ServerLogic() {
        mSessions = new ConcurrentHashMap<>();
        System.out.println("[SERVER-LOGIC] Cronus logic initialized");
    }

    void initialize() {}

    ClientInfo openSession(String sessionKey) {
        ClientInfo clientInfo = new ClientInfo(sessionKey);
        mSessions.put(sessionKey, clientInfo);
        return clientInfo;
    }

    void closeSession(String sessionKey) {
        mSessions.remove(sessionKey);
    }

    public ClientInfo findSession(String sessionKey) {
        return mSessions.get(sessionKey);
    }

    ClientMessage route(ClientInfo info, Headers headers, Buffer buffer) {
        if (headers.getId() != 10100) {
            buffer = Crypto.decrypt(info, headers.getId(),buffer);
        }

        switch (headers.getId()) {
            case 10100:
                return new ClientHello(buffer);
            case 10101:
                return new Login(buffer);
            case 10609:
                return new GetClanInfo(buffer);
            case 11688:
                return new ClientStatus(buffer);
            case 15827:
                return new AskForBattleReplayStream(buffer);
            case 17101:
                return new AskForAvatarStreamMessage(buffer);
            case 18688:
                return new AskForGameRoom(buffer);
            case 19911:
                return new ClientKeepAlive(buffer);
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
                        new OwnHomeData(clientInfo.getClientId())
                };
            case 10609:
                return new ServerMessage[] {
                        new CronusClanInfo()
                };
            case 11688:
            case 17101:
                return new ServerMessage[0];
            case 18688:
                return new ServerMessage[] {
                        new SectorState()
                };
            case 19911:
                return new ServerMessage[] {
                        new ServerKeepAlive()
                };
        }

        return null;
    }

    public static class ClientInfo {
        private final String mSessionKey;
        private final long mClientId;
        private final Nonce mR;

        private Nonce mS;

        ClientInfo(String sessionKey) {
            mSessionKey = sessionKey;
            mClientId = new SecureRandom().nextLong();
            mR = new Nonce(new byte[24]);
        }

        String getSessionKey() {
            return mSessionKey;
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
