package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class CronusChatEvent extends ServerMessage {
    public static final int CHAT_EVENT_JOIN = 0;
    public static final int CHAT_EVENT_LEAVE = 1;
    public static final int CHAT_EVENT_MESSAGE = 2;

    private final int mUId;
    private final int mEventId;
    private final String mPlayerName;
    private final String mContent;

    public CronusChatEvent(int eventId, String playerName, String content) {
        mUId = ServerLogic.getInstance().getRandom().nextInt();
        mEventId = eventId;
        mPlayerName = playerName;
        mContent = content;
    }

    @Override
    public int getId() {
        return 21075;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer b = OutBuffer.newBuffer();

        byte crEvent;
        switch (mEventId) {
            case 0:
            case 1:
                crEvent = 4;
                break;
            case 2:
                crEvent = 2;
                break;
            default:
                return b.obtain();
        }

        b.write((byte) crEvent);
        b.write((byte) 0);

        b.writeRrsInt(mUId);

        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeString(mPlayerName);
        b.writeRrsInt(10);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);

        if (mEventId == CHAT_EVENT_JOIN ) {
            b.write((byte) 4);
            b.write((byte) 0);
            b.write((byte) 0);
            b.writeString(mPlayerName);
        } else if (mEventId == CHAT_EVENT_LEAVE) {
            b.write((byte) 3);
            b.write((byte) 0);
            b.write((byte) 0);
            b.writeString(mPlayerName);
        } else if (mEventId == CHAT_EVENT_MESSAGE) {
            b.writeString(mContent);
        }

        return b.obtain();
    }

    int getUId() {
        return mUId;
    }

    String getPlayerName() {
        return mPlayerName;
    }

    String getContent() {
        return mContent;
    }
}
