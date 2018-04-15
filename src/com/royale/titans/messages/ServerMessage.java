package com.royale.titans.messages;

import com.royale.titans.lib.Buffer;

public abstract class ServerMessage {
    public abstract int getId();
    public abstract int getVersion();
    public abstract Buffer getBuffer();
}
