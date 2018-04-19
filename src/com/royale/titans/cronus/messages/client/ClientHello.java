package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;

public class ClientHello extends ClientMessage {
    private final String mFingerprint;

    public ClientHello(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
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

    public String getFingerprint() {
        return mFingerprint;
    }
}
