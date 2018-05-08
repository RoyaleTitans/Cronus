package com.royale.titans.cronus.models;

import com.royale.titans.cronus.lib.HashTag;
import com.royale.titans.cronus.messages.client.ClientBattleEvent;

import java.util.ArrayList;

public class PlayerInfo {

    private final ClientInfo mClientInfo;

    public PlayerInfo(ClientInfo clientInfo) {
        mClientInfo = clientInfo;
    }

    public HashTag getClientId() {
        return mClientInfo.getClientId();
    }

    public ClientInfo getClientInfo() {
        return mClientInfo;
    }
}
