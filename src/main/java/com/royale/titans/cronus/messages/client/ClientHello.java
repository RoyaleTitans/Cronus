package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.Configs;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.server.LoginFailed;
import com.royale.titans.cronus.messages.server.ServerHello;
import com.royale.titans.cronus.models.ClientInfo;

public class ClientHello extends ClientMessage {
    private final String mFingerprint;

    public ClientHello(ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);

        buffer.readInt();
        buffer.readInt();
        buffer.readInt();
        buffer.readInt();
        buffer.readInt();
        mFingerprint = buffer.readString();
        buffer.readInt();
        buffer.readInt();
    }

    @Override
    public ServerMessage[] handle(ClientInfo clientInfo) {
        if (false && !mFingerprint.equals(Configs.FINGERPRINT)) {
            return new ServerMessage[] {
                    new LoginFailed(7)
            };
        } else {
            return new ServerMessage[] {
                    new ServerHello()
            };
        }
    }
}
