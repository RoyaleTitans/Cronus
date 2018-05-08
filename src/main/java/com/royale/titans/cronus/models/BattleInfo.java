package com.royale.titans.cronus.models;

import com.royale.titans.cronus.messages.client.ClientBattleEvent;

import java.util.ArrayList;

public class BattleInfo {
    private final int mSlotId;

    private final ArrayList<PlayerInfo> mPlayersInfo = new ArrayList<>();
    private final ArrayList<ClientBattleEvent> mBattleEvents = new ArrayList<>();

    private int mSequence;
    private int mEventIndex;

    private long mGameStartTimestamp;
    private long mLastBattleEvent;

    public BattleInfo(int slotId, ClientInfo clientInfo) {
        mSlotId = slotId;
        mPlayersInfo.add(new PlayerInfo(clientInfo));
        mSequence = 1;
        mEventIndex = 0;
        mLastBattleEvent = 0;
    }

    public void startGame() {
        mGameStartTimestamp = System.currentTimeMillis() / 1000;
    }

    public void incrementSequence() {
        mSequence = mSequence + 1;
    }

    public int getSlotId() {
        return mSlotId;
    }

    public int getSequence() {
        return mSequence;
    }

    public long getLastBattleEvent() { return mLastBattleEvent; }

    public String getHostTag() {
        return mPlayersInfo.get(0).getClientId().toString();
    }

    public ArrayList<PlayerInfo> getPlayers() {
        return mPlayersInfo;
    }

    public ArrayList<ClientBattleEvent> getBattleEvents() {
        return mBattleEvents;
    }

    public long getGameStartTimestamp() {
        return mGameStartTimestamp;
    }

    public void onBattleEvent(ClientBattleEvent clientBattleEvent) {
        System.out.println("BATTLEEVENT FROM " + clientBattleEvent.getClientInfo().getClientId().toString() + " " + System.currentTimeMillis());
        if (clientBattleEvent.getCommand() > 0) {
            mBattleEvents.add(clientBattleEvent);
        }
        mLastBattleEvent = System.currentTimeMillis();
    }

    public int getEventIndex() {
        return mEventIndex;
    }

    public void incrementEventIndex() {
        mEventIndex = mEventIndex + 1;
    }
}
