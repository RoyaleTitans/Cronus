package com.royale.titans.messages;

import com.royale.titans.lib.Buffer;
import com.royale.titans.messages.client.ClientHello;

public class Router {

    public static ClientMessage route(Headers headers, Buffer buffer) {
        switch (headers.getId()) {
            case 10100:
                return new ClientHello(buffer);
        }
        return null;
    }
}
