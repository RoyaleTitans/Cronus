package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.BattleLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.client.ClientBattleEvent;

public class ServerBattleEvent extends ServerMessage {
    private final BattleLogic.BattleInfo mBattleInfo;

    private int mEventId;
    private ClientBattleEvent mClientEvent;

    public ServerBattleEvent(BattleLogic.BattleInfo battleInfo) {
        mBattleInfo = battleInfo;
        mEventId = 0;
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
        outBuffer.writeRrsInt(mBattleInfo.getSequence());
        outBuffer.writeRrsInt(0xDEAD);
        outBuffer.writeRrsInt(mEventId);

        if (mEventId > 0) {
            outBuffer.writeRrsInt(mClientEvent.getBuffer().readRrsInt().getValue());
            mClientEvent.getBuffer().readRrsInt();
            outBuffer.writeRrsInt(System.currentTimeMillis() - mBattleInfo.getGameStartTimestamp());
            outBuffer.writeRrsInt(mClientEvent.getBuffer().readRrsInt().getValue());
            outBuffer.writeRrsInt(mClientEvent.getBuffer().readRrsInt().getValue());
            outBuffer.writeRrsInt(mClientEvent.getBuffer().readRrsInt().getValue());
            outBuffer.writeRrsInt(mClientEvent.getBuffer().readRrsInt().getValue());
            outBuffer.writeRrsInt(mClientEvent.getBuffer().readRrsInt().getValue());
            outBuffer.writeRrsInt(mClientEvent.getBuffer().readRrsInt().getValue());
            outBuffer.writeRrsInt(mClientEvent.getBuffer().readRrsInt().getValue());
            outBuffer.writeRrsInt(mClientEvent.getBuffer().readRrsInt().getValue());
            outBuffer.writeRrsInt(mClientEvent.getBuffer().readRrsInt().getValue());
            outBuffer.writeRrsInt(mClientEvent.getBuffer().readRrsInt().getValue());
        }

        return outBuffer.obtain();
    }

    public void setClientEvent(ClientBattleEvent clientBattleEvent) {
        mEventId = 1;
        mClientEvent = clientBattleEvent;
    }
}
