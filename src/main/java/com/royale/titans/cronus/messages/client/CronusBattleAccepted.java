package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.BattleLogic;
import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.server.CronusChatBattleEvent;

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
            CronusChatBattleEvent cronusChatBattleEvent = ServerLogic.getInstance()
                    .getBattleChatEventsTagMap().get(battleInfo.getHostTag());
            if (cronusChatBattleEvent != null) {
                cronusChatBattleEvent.setOpponentInfo(clientInfo);
                battleInfo.getPlayers().add(clientInfo);
                BattleLogic.getInstance().startBattle(battleInfo);

                ServerLogic.getInstance().scheduleTask(new ServerLogic.ServerWorker.WorkerTask(
                        ServerLogic.ServerWorker.TASK.POST_CRONUS_CHAT_GAME_START,
                        cronusChatBattleEvent));
            }
        }
        return new ServerMessage[0];
    }
}
