package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.server.EmptyMessage;
import com.royale.titans.cronus.models.ClientInfo;

public class AskForAvatarStreamMessage extends ClientMessage {
    public AskForAvatarStreamMessage(ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);
    }

    @Override
    public ServerMessage[] handle(ClientInfo clientInfo) {
        return new ServerMessage[] {
                new EmptyMessage(29567, 4)
        };
    }
}
