package com.royale.titans.cronus.messages;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;

public abstract class ClientMessage {
    private final ServerLogic.ClientInfo mClientInfo;
    private final Buffer mBuffer;

    public ClientMessage(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        mClientInfo = clientInfo;
        mBuffer = buffer;
    }

    public ServerLogic.ClientInfo getClientInfo() {
        return mClientInfo;
    }

    public Buffer getBuffer() {
        return mBuffer;
    }

    public abstract ServerMessage[] handle(ServerLogic.ClientInfo clientInfo);
}
