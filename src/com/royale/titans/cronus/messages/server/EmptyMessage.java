package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class EmptyMessage extends ServerMessage {
    private int mId;
    private int mSize;

    public EmptyMessage(int id, int size) {
        mId = id;
        mSize = size;
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
        return Buffer.allocate(mSize);
    }
}
