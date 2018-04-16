package com.royale.titans.cronus;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.Crypto;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.Headers;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.client.ClientHello;
import com.royale.titans.cronus.messages.client.ClientKeepAlive;
import com.royale.titans.cronus.messages.client.ClientStatus;
import com.royale.titans.cronus.messages.client.Login;
import com.royale.titans.cronus.messages.server.LoginOk;
import com.royale.titans.cronus.messages.server.OwnHomeData;
import com.royale.titans.cronus.messages.server.ServerHello;
import com.royale.titans.cronus.messages.server.ServerKeepAlive;

class ServerLogic {

    static ClientMessage route(Headers headers, Buffer buffer) {
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
            case 11688:
                return new ClientStatus(buffer);
            case 19911:
                return new ClientKeepAlive(buffer);
        }
        return null;
    }

    static ServerMessage[] handle(Headers headers, ClientMessage clientMessage) {
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
            case 19911:
                return new ServerMessage[] {
                        new ServerKeepAlive()
                };
        }

        return null;
    }
}
