package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.server.OwnHomeData;
import com.royale.titans.cronus.models.ClientInfo;

public class GoHome extends ClientMessage {

    private final int mReason;

    public GoHome(ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);

        buffer.read(4);

        // assuming this byte is reason
        mReason = buffer.read();
    }

    @Override
    public ServerMessage[] handle(ClientInfo clientInfo) {
        if (mReason == 0x20) {
            return new ServerMessage[] {
                    new OwnHomeData(clientInfo)
            };
        }

        return new ServerMessage[0];
    }
}
