package com.royale.titans.cronus.messages;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.models.ClientInfo;

public abstract class ClientMessage {
    private final ClientInfo mClientInfo;
    private final Buffer mBuffer;

    public ClientMessage(ClientInfo clientInfo, Buffer buffer) {
        mClientInfo = clientInfo;
        mBuffer = buffer;
    }

    public ClientInfo getClientInfo() {
        return mClientInfo;
    }

    public Buffer getBuffer() {
        return mBuffer;
    }

    public abstract ServerMessage[] handle(ClientInfo clientInfo);
}
