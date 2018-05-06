package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.server.LoginOk;
import com.royale.titans.cronus.messages.server.OwnHomeData;
import com.royale.titans.cronus.models.ClientInfo;

public class Login extends ClientMessage {
    public Login(ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);

        clientInfo.setClientId(buffer.readInt(), buffer.readInt());
        System.out.println(clientInfo.getClientId());
    }

    @Override
    public ServerMessage[] handle(ClientInfo clientInfo) {
        return new ServerMessage[] {
                new LoginOk(clientInfo.getClientId().lon()),
                new OwnHomeData(clientInfo)
        };
    }
}
