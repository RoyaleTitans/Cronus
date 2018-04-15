package com.royale.titans.messages;

import com.royale.titans.lib.Buffer;

public class ClientMessage {
    private final Buffer mBuffer;

    public ClientMessage(Buffer buffer) {
        mBuffer = buffer;
    }

    public Buffer getBuffer() {
        return mBuffer;
    }
}
