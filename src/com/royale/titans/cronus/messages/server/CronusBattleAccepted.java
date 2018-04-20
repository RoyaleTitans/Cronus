package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;

public class CronusBattleAccepted extends ClientMessage {
    private final int mSlotId;

    public CronusBattleAccepted(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);

        buffer.read(7);
        mSlotId = buffer.readRrsInt().getValue();
    }

    public int getSlotId() {
        return mSlotId;
    }
}
