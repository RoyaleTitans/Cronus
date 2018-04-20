package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.BattleLogic;
import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;

public class CronusBattleAccepted extends ClientMessage {
    private final int mSlotId;

    public CronusBattleAccepted(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);

        buffer.read(7);
        mSlotId = buffer.readRrsInt().getValue();
    }

    @Override
    public ServerMessage[] handle(ServerLogic.ClientInfo clientInfo) {
        BattleLogic.BattleInfo battleInfo = BattleLogic.getInstance().getBattleInfo(mSlotId);
        if (battleInfo != null) {
            CronusChatBattleEvent cronusChatBattleEvent = ServerLogic.getInstance().getBattleChatEventsSessionMap()
                    .get(battleInfo.getHostPlayerInfo().getSessionKey());
            if (cronusChatBattleEvent != null) {
                cronusChatBattleEvent.setOpponentInfo(clientInfo);
                return new ServerMessage[]{
                        cronusChatBattleEvent
                };
            }
        }

        return new ServerMessage[0];
    }
}
