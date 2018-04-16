package com.royale.titans.cronus;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.Crypto;
import com.royale.titans.cronus.lib.Nonce;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.Configs;
import com.royale.titans.cronus.messages.Headers;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.client.ClientHello;
import com.royale.titans.cronus.messages.client.ClientKeepAlive;
import com.royale.titans.cronus.messages.client.ClientStatus;
import com.royale.titans.cronus.messages.client.Login;
import com.royale.titans.cronus.messages.server.*;

import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;

public class ServerLogic {
    private static ServerLogic sInstance;

    private final ConcurrentHashMap<String, ClientInfo> mSessions;

    public static synchronized ServerLogic getInstance() {
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
        System.out.println("find session plz " + sessionKey);
        return mSessions.get(sessionKey);
    }

    ClientMessage route(Headers headers, Buffer buffer) {
        if (headers.getId() != 10100) {
            if (headers.getId() == 10101) {
                buffer = Crypto.decryptLogin(buffer);

                if (buffer == null) {
                    return null;
                }
            }
        }

        switch (headers.getId()) {
            case 10100:
                return new ClientHello(buffer);
            case 10101:
                return new Login(buffer);
            case 11688:
                return new ClientStatus(buffer);
            case 19911:
                return new ClientKeepAlive(buffer);
        }
        return null;
    }

    ServerMessage[] handle(ClientInfo clientInfo, Headers headers, ClientMessage clientMessage) {
        switch (headers.getId()) {
            case 10100:
                if (!((ClientHello) clientMessage).getFringerPrint().equals(Configs.FINGERPRINT)) {
                    return new ServerMessage[]{
                            new LoginFailed(7)
                    };
                } else {
                    return new ServerMessage[]{
                            new ServerHello()
                    };
                }
            case 10101:
                return new ServerMessage[] {
                        new LoginOk(clientInfo.getClientId()),
                        new OwnHomeData(clientInfo.getClientId())
                };
            case 19911:
                return new ServerMessage[] {
                        new ServerKeepAlive()
                };
            case 11688:
                return new ServerMessage[0];
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
