package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;

public class AskCronusBattleQueueLeave extends ClientMessage {
    public AskCronusBattleQueueLeave(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);
    }
}
