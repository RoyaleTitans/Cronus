package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

import java.util.List;

public class CronusChat extends ServerMessage {
    @Override
    public int getId() {
        return 24719;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer outBuffer = OutBuffer.newBuffer();

        List<CronusChatEvent> chatEvents = ServerLogic.getInstance().getChatEvents();

        outBuffer.writeRrsInt(chatEvents.size());
        for (CronusChatEvent chatEvent : chatEvents) {
            outBuffer.write((byte) 2);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(chatEvent.getUId());
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeString(chatEvent.getClientInfo().getPlayerName());
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeString(chatEvent.getContent());
        }

        return outBuffer.obtain();
    }
}
