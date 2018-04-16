package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class LoginOk extends ServerMessage {
    private final long mClientId;

    public LoginOk(long clientId) {
        mClientId = clientId;
    }

    @Override
    public int getId() {
        return 22280;
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public Buffer getBuffer() {
        Buffer b = Buffer.allocate(404);
        b.writeLong(mClientId);
        b.writeLong(mClientId);
        b.writeString("");
        b.writeString("");
        b.writeString("");
        b.writeRrsInt(3);
        b.writeRrsInt(830);
        b.writeRrsInt(830);
        b.writeRrsInt(18);
        b.writeString("prod");
        b.writeRrsInt(7430);
        b.writeRrsInt(2685560);
        b.writeRrsInt(506);
        b.writeString("1475268786112433");
        b.writeString(String.valueOf(System.currentTimeMillis()));
        b.writeString("1480035212000");
        b.writeRrsInt(0);
        b.writeString("");
        b.writeInt(-1);
        b.writeInt(-1);
        b.writeString("");
        b.writeString("");
        b.writeString("10");
        b.writeInt(61710645);
        b.writeInt(-1732440062);
        b.writeString("https://game-assets.clashroyaleapp.com");
        b.writeString("https://99faf1e355c749a9a049-2a63f4436c967aa7d355061bd0d924a1.ssl.cf1.rackcdn.com");
        b.writeRrsInt(1);
        b.writeString("https://event-assets.clashroyale.com");
        return b;
    }
}
