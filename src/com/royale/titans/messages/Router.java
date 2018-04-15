package com.royale.titans.messages;

import com.royale.titans.lib.Buffer;
import com.royale.titans.lib.Crypto;
import com.royale.titans.messages.client.ClientHello;
import com.royale.titans.messages.client.Login;

public class Router {

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
}
