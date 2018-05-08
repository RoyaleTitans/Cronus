package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.CRUtils;
import com.royale.titans.cronus.Utils;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.client.ClientBattleEvent;
import com.royale.titans.cronus.models.BattleInfo;

public class ServerBattleEvent extends ServerMessage {
    private final BattleInfo mBattleInfo;

    private ClientBattleEvent mClientEvent;

    public ServerBattleEvent(BattleInfo battleInfo) {
        mBattleInfo = battleInfo;
    }

    @Override
    public int getId() {
        return 27145;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer outBuffer = OutBuffer.newBuffer();
        outBuffer.writeRrsInt(mBattleInfo.getSequence());
        outBuffer.writeRrsInt(0xdead);

        if (mClientEvent != null) {
            outBuffer.write((byte) 1);
            outBuffer.writeRrsInt(mClientEvent.getCommand());
            outBuffer.writeRrsInt(mClientEvent.getTick() + 10);
            outBuffer.writeRrsInt(-64);
            outBuffer.writeRrsInt(mClientEvent.getClientInfo().getClientId().high());
            outBuffer.writeRrsInt(mClientEvent.getClientInfo().getClientId().low());
            outBuffer.writeRrsInt(mClientEvent.getDeckIndex());
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(mClientEvent.getCardScId()[0]);
            outBuffer.writeRrsInt(mClientEvent.getCardScId()[1]);
            outBuffer.writeRrsInt(-64);
            outBuffer.write((byte) 1);
            CRUtils.CardInfo cardInfo = CRUtils.sCardsScIdMap.get(
                    mClientEvent.getCardScId()[0] * 1000000 + mClientEvent.getCardScId()[1]);
            outBuffer.writeRrsInt(cardInfo.getId());
            outBuffer.writeRrsInt(cardInfo.getMaxLevel());
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(mClientEvent.getCoords()[0]);
            outBuffer.writeRrsInt(mClientEvent.getCoords()[1]);
        } else {
            outBuffer.write((byte) 0);
        }

        System.out.println(Utils.b2h(outBuffer.obtain().rewind().array()));

        return outBuffer.obtain();
    }

    public void setClientEvent(ClientBattleEvent clientBattleEvent) {
        mClientEvent = clientBattleEvent;
    }
}
