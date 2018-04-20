package com.royale.titans.cronus.messages;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;

public abstract class ClientMessage {
    private final ServerLogic.ClientInfo mSenderInfo;
    private final Buffer mBuffer;

    public ClientMessage(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        mSenderInfo = clientInfo;
        mBuffer = buffer;
    }

    public ServerLogic.ClientInfo getSenderInfo() {
        return mSenderInfo;
    }

    public Buffer getBuffer() {
        return mBuffer;
    }

    public abstract ServerMessage[] handle(ServerLogic.ClientInfo clientInfo);
}
