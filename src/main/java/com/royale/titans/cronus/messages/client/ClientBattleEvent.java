package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.BattleLogic;
import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;

public class ClientBattleEvent extends ClientMessage {
    public ClientBattleEvent(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);
    }

    @Override
    public ServerMessage[] handle(ServerLogic.ClientInfo clientInfo) {
        BattleLogic.getInstance().onClientBattleEvent(this, clientInfo);
        return new ServerMessage[0];
    }
}
