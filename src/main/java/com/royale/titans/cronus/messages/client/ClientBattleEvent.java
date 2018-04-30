package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.BattleLogic;
import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;

public class ClientBattleEvent extends ClientMessage {
    private final int mCommand;
    private final int mTick;
    private final int mDeckIndex;
    private final int mCardScId[] = new int[2];
    private final int mCoords[] = new int[2];

    public ClientBattleEvent(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);

        // checksum + tick
        buffer.readRrsInt();
        buffer.readRrsInt();

        // event
        int event = buffer.read();
        if (event == 1) {
            // command + tick + empty
            mCommand = buffer.readRrsInt().getValue();
            mTick = buffer.readRrsInt().getValue();
            buffer.readRrsInt();

            // account id
            buffer.readRrsInt();
            buffer.readRrsInt();

            // deck index
            mDeckIndex = buffer.readRrsInt().getValue();
            // always 0
            buffer.readRrsInt();

            // card id high and low
            mCardScId[0] = buffer.readRrsInt().getValue();
            mCardScId[1] = buffer.readRrsInt().getValue();

            // empty
            buffer.read();

            // no ext
            buffer.read();

            // coords
            mCoords[0] = buffer.readRrsInt().getValue();
            mCoords[1] = buffer.readRrsInt().getValue();
        } else {
            mCommand = 0;
            mTick = 0;
            mDeckIndex = 0;
        }
    }

    @Override
    public ServerMessage[] handle(ServerLogic.ClientInfo clientInfo) {
        if (mCommand > 0) {
            BattleLogic.getInstance().onClientBattleEvent(this, clientInfo);
        }
        return new ServerMessage[0];
    }

    public int getCommand() {
        return mCommand;
    }

    public int getTick() {
        return mTick;
    }

    public int getDeckIndex() {
        return mDeckIndex;
    }

    public int[] getCardScId() {
        return mCardScId;
    }

    public int[] getCoords() {
        return mCoords;
    }
}
