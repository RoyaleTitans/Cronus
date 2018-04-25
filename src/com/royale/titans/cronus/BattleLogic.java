package com.royale.titans.cronus;

import com.royale.titans.cronus.messages.client.ClientBattleEvent;
import com.royale.titans.cronus.messages.server.ServerBattleEvent;
import com.royale.titans.cronus.messages.server.SectorState;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BattleLogic {

    private static BattleLogic sInstance;

    private final ConcurrentHashMap<Integer, BattleInfo> mBattleInfos = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Integer> mBattleInfosHashMap = new ConcurrentHashMap<>();

    private final ExecutorService mExecutor;

    public synchronized static BattleLogic getInstance() {
        if (sInstance == null) {
            sInstance = new BattleLogic();
        }

        return sInstance;
    }

    private BattleLogic() {
        mExecutor = Executors.newScheduledThreadPool(8);
    }

    public int queueBattle(ServerLogic.ClientInfo clientInfo) {
        int slotId = 1;
        while (true) {
            if (mBattleInfos.get(slotId) == null) {
                BattleInfo battleInfo = new BattleInfo(slotId, clientInfo);
                mBattleInfos.put(slotId, battleInfo);
                mBattleInfosHashMap.put(clientInfo.getClientId().toString(), slotId);
                return slotId;
            }

            slotId++;
        }
    }

    public void cancelBattle(String hashTag) {
        Integer slot = mBattleInfosHashMap.remove(hashTag);
        if (slot != null && slot > 0) {
            mBattleInfos.remove(slot);
        }
    }

    public BattleInfo getBattleInfo(int mSlotId) {
        return mBattleInfos.get(mSlotId);
    }

    public void startBattle(BattleInfo battleInfo) {
        for (ServerLogic.ClientInfo clientInfo : battleInfo.getPlayers()) {
            mBattleInfosHashMap.put(clientInfo.getClientId().toString(), battleInfo.getSlotId());
        }

        scheduleTask(new BattleTask(BattleTask.TASK.START_BATTLE, battleInfo));
    }

    public void onClientBattleEvent(ClientBattleEvent clientBattleEvent, ServerLogic.ClientInfo clientInfo) {
        Integer slotId = mBattleInfosHashMap.get(clientInfo.getClientId().toString());
        if (slotId != null && slotId > 0) {
            BattleInfo battleInfo = mBattleInfos.get(slotId);
            if (battleInfo != null) {
                battleInfo.getBattleEvents().add(clientBattleEvent);
            }
        }
    }

    public void scheduleTask(BattleTask task) {
        mExecutor.execute(task);
    }

    private static class BattleTask implements Runnable {
        enum TASK {
            START_BATTLE
        }

        private final TASK mTask;
        private final Object[] mData;

        BattleTask(final TASK task, Object... data) {
            mTask = task;
            mData = data;
        }

        @Override
        public void run() {
            switch (mTask) {
                case START_BATTLE: {
                    BattleInfo battleInfo = (BattleInfo) mData[0];

                    battleInfo.startGame();

                    for (ServerLogic.ClientInfo clientInfo : battleInfo.getPlayers()) {
                        SectorState sectorState = new SectorState(battleInfo, clientInfo);
                        ServerLogic.getInstance().postMessage(clientInfo, sectorState);
                    }

                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (true) {
                        ServerBattleEvent battleEvent = new ServerBattleEvent(battleInfo);

                        if (battleInfo.getBattleEvents().size() > battleInfo.getEventIndex()) {
                            ClientBattleEvent clientBattleEvent = battleInfo.getBattleEvents().get(battleInfo.getEventIndex());
                            battleEvent.setClientEvent(clientBattleEvent);
                            battleInfo.incrementEventIndex();
                        }

                        for (ServerLogic.ClientInfo clientInfo : battleInfo.getPlayers()) {
                            ServerLogic.getInstance().postMessage(clientInfo, battleEvent);
                        }

                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }

                        battleInfo.incrementSequence();
                    }
                }

                break;
            }
        }
    }

    public static class BattleInfo {
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
            mSequence++;
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
}
