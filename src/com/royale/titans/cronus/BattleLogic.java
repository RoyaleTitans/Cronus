package com.royale.titans.cronus;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BattleLogic {

    private static BattleLogic sInstance;

    private final LinkedHashMap<Integer, BattleInfo> mBattleInfos = new LinkedHashMap<>();
    private final LinkedHashMap<String, Integer> mBattleInfosSessionMap = new LinkedHashMap<>();

    public synchronized static BattleLogic getInstance() {
        if (sInstance == null) {
            sInstance = new BattleLogic();
        }

        return sInstance;
    }

    private BattleLogic() {}

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

        public ServerLogic.ClientInfo getHostPlayerInfo() {
            return mPlayersInfo.get(0);
        }
    }
}
