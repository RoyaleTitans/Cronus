package com.royale.titans.cronus;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.Crypto;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.Headers;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.client.ClientHello;
import com.royale.titans.cronus.messages.client.Login;
import com.royale.titans.cronus.messages.server.LoginOk;
import com.royale.titans.cronus.messages.server.OwnHomeData;
import com.royale.titans.cronus.messages.server.ServerHello;

public class ServerLogic {

    public static ClientMessage route(Headers headers, Buffer buffer) {
        if (headers.getId() != 10100) {
            if (headers.getId() == 10101) {
                buffer = Crypto.decryptLogin(buffer);
            }
        }
        switch (headers.getId()) {
            case 10100:
                return new ClientHello(buffer);
            case 10101:
                return new Login(buffer);
        }
        return null;
    }

    public static ServerMessage[] handle(Headers headers, ClientMessage clientMessage) {
        switch (headers.getId()) {
            case 10100:
                return new ServerMessage[] {
                        new ServerHello()
                };
            case 10101:
                return new ServerMessage[] {
                        new LoginOk((Login) clientMessage),
                        new OwnHomeData((Login) clientMessage)
                };
        }

        return null;
    }
}
