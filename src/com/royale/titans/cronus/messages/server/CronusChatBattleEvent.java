package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;

public class CronusChatBattleEvent extends CronusChatEvent {
    private final int mSlotId;

    public CronusChatBattleEvent(ServerLogic.ClientInfo info, int slotId) {
        super(info, CHAT_EVENT_GAME_QUEUE_START, "");
        mSlotId = slotId;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer b = OutBuffer.newBuffer();

        b.write((byte) 10);

        writeQueueStart(b);

        return b.obtain();
    }

    private void writeQueueStart(OutBuffer b) {
        b.write((byte) -128);
        b.write((byte) -128);
        b.write((byte) -128);
        b.write((byte) -128);
        b.write((byte) 4);

        b.writeRrsInt(mSlotId);

        b.writeRrsInt(getClientInfo().getClientIdHigh());
        b.writeRrsInt(getClientInfo().getClientIdLow());
        b.writeRrsInt(getClientInfo().getClientIdHigh());
        b.writeRrsInt(getClientInfo().getClientIdLow());

        b.writeString(getClientInfo().getPlayerName());
        b.writeRrsInt(12);
        b.writeRrsInt(4);
        b.writeInt(0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(4204);
        b.writeInt(136);
        b.writeRrsInt(1);
        b.writeRrsInt(7);
        b.write((byte) 0);
    }

    public int getSlotId() {
        return mSlotId;
    }
}
