package com.royale.titans.cronus;

import com.royale.titans.cronus.messages.client.ClientBattleEvent;
import com.royale.titans.cronus.messages.server.SectorState;
import com.royale.titans.cronus.messages.server.ServerBattleEvent;
import com.royale.titans.cronus.models.BattleInfo;
import com.royale.titans.cronus.models.ClientInfo;
import com.royale.titans.cronus.models.PlayerInfo;

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

    public int queueBattle(ClientInfo clientInfo) {
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
            BattleInfo battleInfo = mBattleInfos.remove(slot);
            if (battleInfo != null) {

            }
        }
    }

    public BattleInfo getBattleInfo(int mSlotId) {
        return mBattleInfos.get(mSlotId);
    }

    public void startBattle(BattleInfo battleInfo) {
        for (PlayerInfo playerInfo : battleInfo.getPlayers()) {
            mBattleInfosHashMap.put(playerInfo.getClientId().toString(), battleInfo.getSlotId());
        }

        scheduleTask(new BattleTask(BattleTask.TASK.START_BATTLE, battleInfo));
    }

    public void onClientBattleEvent(ClientBattleEvent clientBattleEvent, ClientInfo clientInfo) {
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

                    for (PlayerInfo playerInfo : battleInfo.getPlayers()) {
                        SectorState sectorState = new SectorState(battleInfo, playerInfo);
                        ServerLogic.getInstance().postMessage(playerInfo.getClientInfo(), sectorState);
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (true) {
                        ServerBattleEvent battleEvent = new ServerBattleEvent(battleInfo);
                        boolean hasEvent = battleInfo.getBattleEvents().size() > battleInfo.getEventIndex();
                        if (hasEvent) {
                            System.out.println("CARD DEPLOYED ON SEQ: " + battleInfo.getSequence());
                        }

                        ClientBattleEvent clientBattleEvent = null;
                        for (PlayerInfo playerInfo : battleInfo.getPlayers()) {
                            if (hasEvent) {
                                clientBattleEvent = battleInfo.getBattleEvents().get(battleInfo.getEventIndex());
                                battleEvent.setClientEvent(clientBattleEvent);
                            }

                            ServerLogic.getInstance().postMessage(playerInfo.getClientInfo(), battleEvent);
                        }

                        battleInfo.incrementSequence();

                        if (hasEvent) {
                            battleInfo.incrementEventIndex();
                        }

                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }

                break;
            }
        }
    }
}
