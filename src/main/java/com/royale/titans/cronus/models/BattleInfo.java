package com.royale.titans.cronus.models;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.messages.client.ClientBattleEvent;

import java.util.ArrayList;

public class BattleInfo {
    private final int mSlotId;

    private final ArrayList<ServerLogic.ClientInfo> mPlayersInfo = new ArrayList<>();
    private final ArrayList<ClientBattleEvent> mBattleEvents = new ArrayList<>();

    private int mSequence;
    private int mEventIndex;

    private long mGameStartTimestamp;

    public BattleInfo(int slotId, ServerLogic.ClientInfo clientInfo) {
        mSlotId = slotId;
        mPlayersInfo.add(clientInfo);
        mSequence = 1;
        mEventIndex = 0;
    }

    public void startGame() {
        mGameStartTimestamp = System.currentTimeMillis() / 1000;
    }

    public void incrementSequence() {
        mSequence = mSequence + 1;
    }

    public void incrementEventIndex() {
        mSequence++;
    }

    public int getSlotId() {
        return mSlotId;
    }

    public int getSequence() {
        return mSequence;
    }

    public int getEventIndex() {
        return mEventIndex;
    }

    public String getHostTag() {
        return mPlayersInfo.get(0).getClientId().toString();
    }

    public ArrayList<ServerLogic.ClientInfo> getPlayers() {
        return mPlayersInfo;
    }

    public ArrayList<ClientBattleEvent> getBattleEvents() {
        return mBattleEvents;
    }

    public long getGameStartTimestamp() {
        return mGameStartTimestamp;
    }
}
