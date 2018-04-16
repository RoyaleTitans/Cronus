package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;

public class ClientKeepAlive extends ClientMessage {

    public ClientKeepAlive(Buffer buffer) {
        super(buffer);
    }
}
