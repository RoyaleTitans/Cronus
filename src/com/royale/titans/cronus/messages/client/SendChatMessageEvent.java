package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;
import com.royale.titans.cronus.messages.ServerMessage;

public class SendChatMessageEvent extends ClientMessage {
    private final String mContent;

    public SendChatMessageEvent(ServerLogic.ClientInfo clientInfo, Buffer buffer) {
        super(clientInfo, buffer);
        mContent = buffer.readString();
    }

    @Override
    public ServerMessage[] handle(ServerLogic.ClientInfo clientInfo) {
        ServerLogic.getInstance().scheduleJob(new ServerLogic.ServerWorker.WorkerTask(
                ServerLogic.ServerWorker.TASK.POST_CRONUS_CHAT_MESSAGE, this));
        return new ServerMessage[0];
    }

    public String getContent() {
        return mContent;
    }
}
