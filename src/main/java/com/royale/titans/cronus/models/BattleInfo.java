package com.royale.titans.cronus.models;

import com.royale.titans.cronus.CRUtils;
import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.messages.client.AskForGameRoom;
import com.royale.titans.cronus.messages.client.ClientBattleEvent;

import java.util.ArrayList;

public class BattleInfo {
    private final int mSlotId;

    private final ArrayList<PlayerInfo> mPlayersInfo = new ArrayList<>();
    private final ArrayList<ClientBattleEvent> mBattleEvents = new ArrayList<>();

    private final int mArena;
    private final int mArenaScId;
    private final int mEventId;

    private final ArrayList<Tower> mTowers = new ArrayList<>();

    private int mSequence;
    private int mEventIndex;

    private long mGameStartTimestamp;
    private long mLastBattleEvent;

    /**
     * Build battle info object.
     * Takes slotId and askForGameRoom messages as param where
     * slotId is a free slotIt on the BattleLogic map,
     * askForGameRoom is the message which initialize the battle
     * (a.k.a) message dispatched when we request a new friendly match in clan
     */
    public BattleInfo(int slotId, AskForGameRoom askForGameRoom) {
        mSlotId = slotId;

        mEventId = askForGameRoom.getEventId();

        if (mEventId == 1385) {
            mArena = 27;
            mArenaScId = 27;
        } else {
            if (askForGameRoom.getArena() < 1) {
                mArena = 1 + (12 - 1) * ServerLogic.getInstance().getRandom().nextInt();
            } else {
                mArena = askForGameRoom.getArena();
            }
            mArenaScId = CRUtils.arenaToId(mArena);
        }

        mPlayersInfo.add(new PlayerInfo(askForGameRoom.getClientInfo()));
        mSequence = 1;
        mEventIndex = 0;
        mLastBattleEvent = 0;
    }

    /**
     * Invoked from battle logic before serializing sector state
     */
    public void startGame() {
        mGameStartTimestamp = System.currentTimeMillis() / 1000;

        // write towers
        mTowers.add(new Tower(0, 0, 1, 14500, 25500));
        mTowers.add(new Tower(1, 1, 0, 3500, 6500));
        mTowers.add(new Tower(2, 2, 1, 3500, 25500));
        mTowers.add(new Tower(3, 3, 0, 14500, 6500));

        if (mEventId == 1390) {
            mTowers.add(new Tower(4, 2, 1, 9000, 25500));
            mTowers.add(new Tower(5, 3, 0, 9000, 6500));
        }
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

    public int getArena() {
        return mArena;
    }

    public int getArenaScId() {
        return mArenaScId;
    }

    public int getEventId() {
        return mEventId;
    }

    public ArrayList<Tower> getTowers() {
        return mTowers;
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

    public static class Tower {
        private final int mTowerIndex;
        private final int mScId;
        private final int mPlayerIndex;
        private final int mX;
        private final int mY;

        Tower(int towerIndex, int scId, int playerIndex, int x, int y) {
            mTowerIndex = towerIndex;
            mScId = scId;
            mPlayerIndex = playerIndex;
            mX = x;
            mY = y;
        }

        public int getTowerIndex() {
            return mTowerIndex;
        }

        public int getTowerScId() {
            return mScId;
        }

        public int getPlayerIndex() {
            return mPlayerIndex;
        }

        public int getX() {
            return mX;
        }

        public int getY() {
            return mY;
        }
    }
}
