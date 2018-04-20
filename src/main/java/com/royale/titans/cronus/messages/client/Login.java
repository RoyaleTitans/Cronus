package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;

public class Login extends ClientMessage {
    public Login(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);
    }
}
