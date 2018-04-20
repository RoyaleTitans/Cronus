package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class CustomBufferMessage extends ServerMessage {
    private final int mId;
    private final Buffer mBuffer;

    public CustomBufferMessage(int id, Buffer b) {
        mId = id;
        mBuffer = b;
    }

    @Override
    public int getId() {
        return mId;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        return mBuffer;
    }
}
