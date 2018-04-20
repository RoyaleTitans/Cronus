package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

import java.util.ArrayList;
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

        List<CronusChatEvent> chatEvents = new ArrayList<>(ServerLogic.getInstance().getChatEvents());
        chatEvents.addAll(ServerLogic.getInstance().getBattleChatEvents());
        chatEvents.sort((o1, o2) -> Long.compare(o2.getTimestamp(), o1.getTimestamp()));

        outBuffer.writeRrsInt(chatEvents.size());
        for (CronusChatEvent chatEvent : chatEvents) {
            if (chatEvent instanceof CronusChatBattleEvent) {
                outBuffer.write((byte) -128);
                outBuffer.write((byte) -128);
                outBuffer.write((byte) -128);
                outBuffer.write((byte) -128);
                outBuffer.write((byte) 4);

                outBuffer.writeRrsInt(((CronusChatBattleEvent) chatEvent).getSlotId());

                outBuffer.writeRrsInt(chatEvent.getClientInfo().getClientIdHigh());
                outBuffer.writeRrsInt(chatEvent.getClientInfo().getClientIdLow());
                outBuffer.writeRrsInt(chatEvent.getClientInfo().getClientIdHigh());
                outBuffer.writeRrsInt(chatEvent.getClientInfo().getClientIdLow());
                outBuffer.writeString(chatEvent.getClientInfo().getPlayerName());

                outBuffer.writeRrsInt(10);
                outBuffer.writeRrsInt(3);
                outBuffer.writeInt(0);
                outBuffer.write((byte) 0);
                outBuffer.write((byte) 0);
                outBuffer.write((byte) 0);
                outBuffer.writeRrsInt(0);
                outBuffer.writeInt(136);
                outBuffer.writeRrsInt(1);
                outBuffer.writeRrsInt(7);
                outBuffer.write((byte) 0);
            } else {
                outBuffer.write((byte) 2);
                outBuffer.write((byte) 0);
                outBuffer.writeRrsInt(chatEvent.getUId());

                outBuffer.writeRrsInt(chatEvent.getClientInfo().getClientIdHigh());
                outBuffer.writeRrsInt(chatEvent.getClientInfo().getClientIdLow());
                outBuffer.writeRrsInt(chatEvent.getClientInfo().getClientIdHigh());
                outBuffer.writeRrsInt(chatEvent.getClientInfo().getClientIdLow());
                outBuffer.writeString(chatEvent.getClientInfo().getPlayerName());

                outBuffer.write((byte) 0);
                outBuffer.write((byte) 0);
                outBuffer.write((byte) 0);
                outBuffer.write((byte) 0);
                outBuffer.writeString(chatEvent.getContent());
            }
        }

        return outBuffer.obtain();
    }
}
