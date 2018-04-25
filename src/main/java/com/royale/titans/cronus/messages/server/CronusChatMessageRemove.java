package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class CronusChatMessageRemove extends ServerMessage {
    private final int mSlotId;

    public CronusChatMessageRemove(int slotId) {
        mSlotId = slotId;
    }

    @Override
    public int getId() {
        return 25643;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer outBuffer = OutBuffer.newBuffer();
        outBuffer.write((byte) 32);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) mSlotId);
        return outBuffer.obtain();
    }
}
