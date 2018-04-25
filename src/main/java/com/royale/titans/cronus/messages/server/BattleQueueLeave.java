package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class BattleQueueLeave extends ServerMessage {

    private final int mCode;

    public BattleQueueLeave(boolean joinMatch) {
        mCode = joinMatch ? 0 : 1;
    }

    @Override
    public int getId() {
        return 24696;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer outBuffer = OutBuffer.newBuffer();
        outBuffer.writeInt(mCode);
        return outBuffer.obtain();
    }
}
