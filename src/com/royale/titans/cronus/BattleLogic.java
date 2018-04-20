package com.royale.titans.cronus;

import com.royale.titans.cronus.messages.server.SectorState;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BattleLogic {

    private static BattleLogic sInstance;

    private final ConcurrentHashMap<Integer, BattleInfo> mBattleInfos = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Integer> mBattleInfosSessionMap = new ConcurrentHashMap<>();

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
                mBattleInfosSessionMap.put(clientInfo.getSessionKey(), slotId);
                return slotId;
            }

            slotId++;
        }
    }

    public void cancelBattle(String sessionKey) {
        Integer slot = mBattleInfosSessionMap.remove(sessionKey);
        if (slot != null && slot > 0) {
            mBattleInfos.remove(slot);
        }
    }

    public BattleInfo getBattleInfo(int mSlotId) {
        return mBattleInfos.get(mSlotId);
    }

    public void startBattle(BattleInfo battleInfo) {
        scheduleTask(new BattleTask(BattleTask.TASK.START_BATTLE, battleInfo));
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
                    for (ServerLogic.ClientInfo clientInfo : battleInfo.getPlayers()) {
                        ServerLogic.getInstance().postMessage(clientInfo,
                                new SectorState());
                    }
                }
                break;
            }
        }
    }

    public static class BattleInfo {
        private final int mSlotId;

        private final ArrayList<ServerLogic.ClientInfo> mPlayersInfo = new ArrayList<>();

        public BattleInfo(int slotId, ServerLogic.ClientInfo clientInfo) {
            mSlotId = slotId;
            mPlayersInfo.add(clientInfo);
        }

        public int getSlotId() {
            return mSlotId;
        }

        public String getHostSessionKey() {
            return mPlayersInfo.get(0).getSessionKey();
        }

        public ArrayList<ServerLogic.ClientInfo> getPlayers() {
            return mPlayersInfo;
        }
    }
}
