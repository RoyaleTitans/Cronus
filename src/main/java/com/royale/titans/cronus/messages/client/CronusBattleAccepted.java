package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.BattleLogic;
import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.server.CronusChatBattleEvent;
import com.royale.titans.cronus.models.BattleInfo;
import com.royale.titans.cronus.models.ClientInfo;
import com.royale.titans.cronus.models.PlayerInfo;

public class CronusBattleAccepted extends ClientMessage {
    private final int mSlotId;

    public CronusBattleAccepted(ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);

        buffer.read(7);
        mSlotId = buffer.readRrsInt().getValue();
    }

    @Override
    public ServerMessage[] handle(ClientInfo clientInfo) {
        BattleInfo battleInfo = BattleLogic.getInstance().getBattleInfo(mSlotId);
        if (battleInfo != null) {
            CronusChatBattleEvent cronusChatBattleEvent = ServerLogic.getInstance()
                    .getBattleChatEventsTagMap().get(battleInfo.getHostTag());
            if (cronusChatBattleEvent != null) {
                cronusChatBattleEvent.setOpponentInfo(clientInfo);
                battleInfo.getPlayers().add(new PlayerInfo(clientInfo));
                BattleLogic.getInstance().startBattle(battleInfo);

                ServerLogic.getInstance().scheduleTask(new ServerLogic.ServerWorker.WorkerTask(
                        ServerLogic.ServerWorker.TASK.POST_CRONUS_CHAT_GAME_START,
                        cronusChatBattleEvent));
            }
        }
        return new ServerMessage[0];
    }
}
