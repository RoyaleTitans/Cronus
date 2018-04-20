package com.royale.titans.cronus.messages;

import com.royale.titans.cronus.lib.Buffer;

public abstract class ServerMessage {
    public abstract int getId();
    public abstract int getVersion();
    public abstract Buffer getBuffer();
}
