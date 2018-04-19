package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;

public class SendChatMessageEvent extends ClientMessage {
    private final String mContent;

    public SendChatMessageEvent(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);
        mContent = buffer.readString();
    }

    public String getContent() {
        return mContent;
    }
}
