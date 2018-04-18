package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;

public class AskForGameRoom extends ClientMessage {
    public AskForGameRoom(Buffer buffer) {
        super(buffer);
    }
}
