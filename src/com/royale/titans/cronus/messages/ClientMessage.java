package com.royale.titans.cronus.messages;

import com.royale.titans.cronus.lib.Buffer;

public class ClientMessage {
    private final Buffer mBuffer;

    public ClientMessage(Buffer buffer) {
        mBuffer = buffer;
    }

    public Buffer getBuffer() {
        return mBuffer;
    }
}
