package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.models.ClientInfo;

public class SendChatMessageEvent extends ClientMessage {
    private final String mContent;

    public SendChatMessageEvent(ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);
        mContent = buffer.readString();
    }

    @Override
    public ServerMessage[] handle(ClientInfo clientInfo) {
        ServerLogic.getInstance().scheduleTask(new ServerLogic.ServerWorker.WorkerTask(
                ServerLogic.ServerWorker.TASK.POST_CRONUS_CHAT_MESSAGE, this));
        return new ServerMessage[0];
    }

    public String getContent() {
        return mContent;
    }
}
