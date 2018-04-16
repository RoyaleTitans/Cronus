package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class ServerKeepAlive extends ServerMessage {
    @Override
    public int getId() {
        return 24135;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        return Buffer.allocate(0);
    }
}
