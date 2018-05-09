package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.server.BattleQueueLeaveConfirm;
import com.royale.titans.cronus.models.ClientInfo;

public class AskForGameRoom extends ClientMessage {
    private final boolean mHaveCommand;
    private final boolean mClanFriendlyMatch;

    private boolean mTeamMatch = false;
    private int mMode = 0;
    private int mArena = 0;
    private int mEventId = 0;

    public AskForGameRoom(ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);

        buffer.readRrsInt();
        buffer.readRrsInt();
        int t = buffer.readRrsInt().getValue();
        mHaveCommand = t > 0;
        if (t == 1) {
            t = buffer.readRrsInt().getValue();
            mClanFriendlyMatch = t == 529;
            if (mClanFriendlyMatch) {
                buffer.read(4);

                mTeamMatch = buffer.readRrsInt().getValue() == 2;
                mMode = buffer.readRrsInt().getValue() * 1000000 + buffer.readRrsInt().getValue();

                buffer.read(6);

                mArena = buffer.readRrsInt().getValue();

                buffer.read(4);

                mEventId = buffer.readRrsInt().getValue();
            }
        } else {
            mClanFriendlyMatch = false;
        }
    }

    @Override
    public ServerMessage[] handle(ClientInfo clientInfo) {
        if (mClanFriendlyMatch) {
            ServerLogic.getInstance().scheduleTask(new ServerLogic.ServerWorker.WorkerTask(
                    ServerLogic.ServerWorker.TASK.POST_CRONUS_CHAT_GAME_QUEUE_START,
                    this));
        } else {
            if (mHaveCommand) {
                return new ServerMessage[] {
                        //new BattleQueueLeave(false),
                        new BattleQueueLeaveConfirm()
                };
            }
        }
        return new ServerMessage[0];
    }

    public int getArena() {
        return mArena;
    }

    public int getEventId() {
        return mEventId;
    }
}
