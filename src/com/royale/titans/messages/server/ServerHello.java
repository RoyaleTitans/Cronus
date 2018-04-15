package com.royale.titans.messages.server;

import com.royale.titans.Utils;
import com.royale.titans.lib.Buffer;
import com.royale.titans.messages.ServerMessage;

public class ServerHello extends ServerMessage {

    private final String mSessionKey;

    public ServerHello() {
        mSessionKey = Utils.randomString(24);
    }

    @Override
    public int getId() {
        return 20100;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        Buffer buffer = Buffer.allocate(28);
        buffer.writeString(mSessionKey);
        return buffer;
    }

    public String getSessioneKey() {
        return mSessionKey;
    }
}
