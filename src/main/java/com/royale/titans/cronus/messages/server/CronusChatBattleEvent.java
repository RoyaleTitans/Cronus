package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;

public class CronusChatBattleEvent extends CronusChatEvent {
    private final int mSlotId;

    private ServerLogic.ClientInfo mOpponentInfo;

    public CronusChatBattleEvent(ServerLogic.ClientInfo info, int slotId) {
        super(info, CHAT_EVENT_BATTLE_MESSAGE, "");
        mSlotId = slotId;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer b = OutBuffer.newBuffer();
        b.write(mCrEventId);
        writeBattleMessage(b);
        return b.obtain();
    }

    public void setOpponentInfo(ServerLogic.ClientInfo opponentInfo) {
        mOpponentInfo = opponentInfo;
    }

    private void writeBattleMessage(OutBuffer b) {
        b.write((byte) -128);
        b.write((byte) -128);
        b.write((byte) -128);
        b.write((byte) -128);
        b.write((byte) 4);

        b.writeRrsInt(mSlotId);

        b.writeRrsInt(getClientInfo().getClientId().high());
        b.writeRrsInt(getClientInfo().getClientId().low());
        b.writeRrsInt(getClientInfo().getClientId().high());
        b.writeRrsInt(getClientInfo().getClientId().low());
        b.writeString(getClientInfo().getPlayerName());

        b.writeRrsInt(10);
        b.writeRrsInt(3);
        b.writeInt(0);
        b.write((byte) 0);
        b.write((byte) 0);

        if (mOpponentInfo != null) {
            b.write((byte) 1);
            b.writeString(mOpponentInfo.getPlayerName());
            b.write((byte) 0);
            b.write((byte) 1);
            b.write((byte) 0);
            b.write((byte) 2);
            b.writeLong(mOpponentInfo.getClientId().lon());
            b.write((byte) -120);
        } else {
            b.write((byte) 0);
            b.writeInt(136);
        }

        b.writeRrsInt(1);
        b.writeRrsInt(7);
        b.write((byte) 0);
    }

    public int getSlotId() {
        return mSlotId;
    }
}
