package com.royale.titans.cronus;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

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
                BattleInfo battleInfo = new BattleInfo(slotId);
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

    public static class BattleInfo {
        private final int mSlotId;

        public BattleInfo(int slotId) {
            mSlotId = slotId;
        }
    }
}
