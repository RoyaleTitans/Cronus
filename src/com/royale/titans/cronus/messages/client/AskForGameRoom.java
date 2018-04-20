package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.server.BattleQueueLeave;
import com.royale.titans.cronus.messages.server.BattleQueueLeaveConfirm;

public class AskForGameRoom extends ClientMessage {
    private final boolean mHaveCommand;
    private final boolean mClanFriendlyMatch;

    public AskForGameRoom(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);

        buffer.readRrsInt();
        buffer.readRrsInt();
        int t = buffer.readRrsInt().getValue();
        mHaveCommand = t > 0;
        if (t == 1) {
            t = buffer.readRrsInt().getValue();
            mClanFriendlyMatch = t == 509;
        } else {
            mClanFriendlyMatch = false;
        }
    }

    @Override
    public ServerMessage[] handle(ServerLogic.ClientInfo clientInfo) {
        if (mClanFriendlyMatch) {
            ServerLogic.getInstance().scheduleTask(new ServerLogic.ServerWorker.WorkerTask(
                    ServerLogic.ServerWorker.TASK.POST_CRONUS_CHAT_GAME_QUEUE_START,
                    clientInfo));
        } else {
            if (mHaveCommand) {
                return new ServerMessage[] {
                        new BattleQueueLeave(false),
                        new BattleQueueLeaveConfirm()
                };
            }
        }
        return new ServerMessage[0];
    }
}
