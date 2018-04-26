package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.server.BattleQueueLeave;
import com.royale.titans.cronus.messages.server.BattleQueueLeaveConfirm;

public class AskBattleQueueLeave extends ClientMessage {
    public AskBattleQueueLeave(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);
    }

    @Override
    public ServerMessage[] handle(ServerLogic.ClientInfo clientInfo) {
        return new ServerMessage[] {
                new BattleQueueLeaveConfirm()
        };
    }
}
