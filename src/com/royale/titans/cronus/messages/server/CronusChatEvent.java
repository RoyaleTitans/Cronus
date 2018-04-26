package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class CronusChatEvent extends ServerMessage {
    public static final int CHAT_EVENT_JOIN = 0;
    public static final int CHAT_EVENT_LEAVE = 1;
    public static final int CHAT_EVENT_MESSAGE = 2;
    public static final int CHAT_EVENT_BATTLE_MESSAGE = 3;

    private final int mUId;
    private final int mEventId;

    protected final byte mCrEventId;

    // Player involved in the event
    private final ServerLogic.ClientInfo mInfo;

    private String mContent;

    private final long mTimestamp;

    /**
     * chat messages
     */
    public CronusChatEvent(ServerLogic.ClientInfo info, int eventId, String content) {
        mUId = ServerLogic.getInstance().getRandom().nextInt();
        mEventId = eventId;

        switch (mEventId) {
            case CHAT_EVENT_MESSAGE:
                mCrEventId = 2;
                break;
            case CHAT_EVENT_JOIN:
            case CHAT_EVENT_LEAVE:
                mCrEventId = 4;
                break;
            case CHAT_EVENT_BATTLE_MESSAGE:
                mCrEventId = 10;
                break;
            default:
                mCrEventId = 0;
                break;
        }

        mInfo = info;
        mContent = content;

        mTimestamp = System.currentTimeMillis();
    }

    @Override
    public int getId() {
        return 21747;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer b = OutBuffer.newBuffer();
        b.write(mCrEventId);
        writeStandardChatEvent(b);
        return b.obtain();
    }

    private void writeStandardChatEvent(OutBuffer b) {
        b.write((byte) 0);
        b.writeRrsInt(mUId);
        b.writeRrsInt(mInfo.getClientId().high());
        b.writeRrsInt(mInfo.getClientId().low());
        b.writeRrsInt(mInfo.getClientId().high());
        b.writeRrsInt(mInfo.getClientId().low());

        b.writeString(mInfo.getPlayerName());
        b.writeRrsInt(10);
        b.write((byte) 0);
        b.write((byte) 0);
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

    long getTimestamp() {
        return mTimestamp;
    }
}
