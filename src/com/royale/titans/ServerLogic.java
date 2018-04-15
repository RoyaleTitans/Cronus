package com.royale.titans;

import com.royale.titans.lib.Buffer;
import com.royale.titans.lib.Crypto;
import com.royale.titans.messages.ClientMessage;
import com.royale.titans.messages.Headers;
import com.royale.titans.messages.ServerMessage;
import com.royale.titans.messages.client.ClientHello;
import com.royale.titans.messages.client.Login;
import com.royale.titans.messages.server.LoginOk;
import com.royale.titans.messages.server.OwnHomeData;
import com.royale.titans.messages.server.ServerHello;

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
                        new OwnHomeData()
                };
        }

        return null;
    }
}
