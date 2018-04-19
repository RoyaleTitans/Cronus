package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class BatlleQueueLeaveAccepted extends ServerMessage {
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
        outBuffer.writeInt(1);
        return outBuffer.obtain();
    }
}
