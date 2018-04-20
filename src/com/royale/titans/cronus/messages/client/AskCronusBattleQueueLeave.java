package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.server.CronusBattleLeaveConfirm;

public class AskCronusBattleQueueLeave extends ClientMessage {
    public AskCronusBattleQueueLeave(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);
    }

    @Override
    public ServerMessage[] handle(ServerLogic.ClientInfo clientInfo) {
        ServerLogic.getInstance().scheduleTask(new ServerLogic.ServerWorker.WorkerTask(
                ServerLogic.ServerWorker.TASK.POST_CRONUS_CHAT_GAME_QUEUE_CANCELLED,
                clientInfo));
        return new ServerMessage[] {
                new CronusBattleLeaveConfirm()
        };
    }
}
