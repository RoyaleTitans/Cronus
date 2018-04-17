package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class CronusClanInfo extends ServerMessage {
    @Override
    public int getId() {
        return 26550;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        Buffer b = Buffer.allocate(54);
        b.writeLong(0);
        b.writeString("Cronus");

        b.writeRrsInt(16);
        b.writeRrsInt(152);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(39840);
        b.writeRrsInt(3800);
        b.writeInt(144);
        b.writeRrsInt(1);
        b.writeRrsInt(3396);
        b.writeRrsInt(22648);
        b.writeRrsInt(1);
        b.writeRrsInt(4);
        b.writeRrsInt(57);
        b.writeRrsInt(120);
        b.writeRrsInt(0);
        b.writeString("desc");
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        return b;
    }
}
