package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;

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

    public boolean haveCommand() {
        return mHaveCommand;
    }

    public boolean isClanFriendlyMatch() {
        return mClanFriendlyMatch;
    }
}
