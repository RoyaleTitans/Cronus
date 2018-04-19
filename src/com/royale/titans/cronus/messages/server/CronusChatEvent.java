package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class CronusChatEvent extends ServerMessage {
    public static final int CHAT_EVENT_JOIN = 0;
    public static final int CHAT_EVENT_LEAVE = 1;
    public static final int CHAT_EVENT_MESSAGE = 2;
    public static final int CHAT_EVENT_GAME_QUEUE_START = 3;

    private final int mUId;
    private final int mEventId;

    // Player involved in the event
    private final ServerLogic.ClientInfo mInfo;

    private String mContent;

    /**
     * chat messages
     */
    public CronusChatEvent(ServerLogic.ClientInfo info, int eventId, String content) {
        mUId = ServerLogic.getInstance().getRandom().nextInt();
        mEventId = eventId;
        mInfo = info;
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
            case CHAT_EVENT_JOIN:
            case CHAT_EVENT_LEAVE:
                crEvent = 4;
                break;
            case CHAT_EVENT_MESSAGE:
                crEvent = 2;
                break;
            default:
                return b.obtain();
        }

        b.write((byte) crEvent);

        writeStandardChatEvent(b);

        return b.obtain();
    }

    private void writeStandardChatEvent(OutBuffer b) {
        b.write((byte) 0);
        b.writeRrsInt(mUId);
        b.writeRrsInt(mInfo.getClientIdHigh());
        b.writeRrsInt(mInfo.getClientIdLow());
        b.writeRrsInt(mInfo.getClientIdHigh());
        b.writeRrsInt(mInfo.getClientIdLow());

        b.writeString(mInfo.getPlayerName());
        b.writeRrsInt(10);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);

        if (mEventId == CHAT_EVENT_JOIN ) {
            b.write((byte) 4);
            b.write((byte) 0);
            b.write((byte) 0);
            b.writeString(mInfo.getPlayerName());
        } else if (mEventId == CHAT_EVENT_LEAVE) {
            b.write((byte) 3);
            b.write((byte) 0);
            b.write((byte) 0);
            b.writeString(mInfo.getPlayerName());
        } else if (mEventId == CHAT_EVENT_MESSAGE) {
            b.writeString(mContent);
        }
    }

    int getUId() {
        return mUId;
    }

    ServerLogic.ClientInfo getClientInfo() {
        return mInfo;
    }

    String getContent() {
        return mContent;
    }
}
