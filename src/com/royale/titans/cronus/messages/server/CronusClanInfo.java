package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.Utils;
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
        Buffer b = Buffer.allocate(4024);
        b.writeLong(0);
        b.writeString("Cronus");

        b.writeRrsInt(16);
        b.writeRrsInt(152);
        b.writeRrsInt(1);

        b.writeRrsInt(0);

        b.writeRrsInt(39650);
        b.writeRrsInt(3800);
        b.writeInt(148);
        b.writeRrsInt(1);
        b.writeRrsInt(6044);
        b.writeRrsInt(22648);
        b.writeRrsInt(1);
        b.writeRrsInt(4);
        b.writeRrsInt(57);
        b.writeRrsInt(120);
        b.writeRrsInt(0);
        b.writeString("stiamo reclutando! poche regole, 20 corone e donare! forza! scalata top clan 200 \uD83D\uDE0E\uD83D\uDCAA\uD83C\uDFFB\uD83D\uDCAA\uD83C\uDFFB\uD83D\uDD1D");

        b.writeRrsInt(0);

        b.write((byte) 1);
        b.writeRrsInt(0);
        b.writeRrsInt(211478);
        b.writeRrsInt(1600);
        b.writeInt(1523603292);
        b.writeInt(1523862492);
        b.writeRrsInt(16175762);
        b.write((byte) 0);
        b.write((byte) 0);

        return b;
    }
}
