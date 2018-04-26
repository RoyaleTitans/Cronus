package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class BattleQueueLeaveConfirm extends ServerMessage {
    @Override
    public int getId() {
        return 27919;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer outBuffer = OutBuffer.newBuffer();
        return outBuffer.obtain();
    }
}
