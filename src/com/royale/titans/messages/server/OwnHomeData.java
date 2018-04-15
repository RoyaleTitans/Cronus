package com.royale.titans.messages.server;

import com.royale.titans.Utils;
import com.royale.titans.lib.Buffer;
import com.royale.titans.messages.ServerMessage;

public class OwnHomeData extends ServerMessage {
    @Override
    public int getId() {
        return 28502;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        return Buffer.wrap(Utils.h2b(""));
    }
}
