package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class BattleEvent extends ServerMessage {

    private final int mSequence;
    private final int mEventId;

    public BattleEvent(int sequence) {
        mSequence = sequence;
        mEventId = 0;
    }

    public BattleEvent(int sequence, int eventId) {
        mSequence = sequence;
        mEventId = eventId;
    }

    @Override
    public int getId() {
        return 21443;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer outBuffer = OutBuffer.newBuffer();
        outBuffer.writeRrsInt(mSequence);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(mEventId);

        if (mEventId > 0) {
            outBuffer.writeRrsInt(51);
            outBuffer.writeRrsInt(201);
            outBuffer.writeRrsInt(-64);
            outBuffer.writeRrsInt(13);
            outBuffer.writeRrsInt(8032449);
            outBuffer.writeRrsInt(0);
            outBuffer.writeRrsInt(26);
            outBuffer.writeRrsInt(9);
            outBuffer.writeRrsInt(-64);
            outBuffer.writeRrsInt(1);
            outBuffer.writeRrsInt(10);
            outBuffer.writeRrsInt(3);
            outBuffer.writeRrsInt(9500);
            outBuffer.writeRrsInt(500);
        }

        return outBuffer.obtain();
    }
}
