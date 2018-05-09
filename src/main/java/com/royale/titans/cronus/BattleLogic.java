package com.royale.titans.cronus;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.client.ClientBattleEvent;
import com.royale.titans.cronus.messages.server.*;
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
        cancelBattle(slot);
    }

    public void cancelBattle(Integer slot) {
        if (slot != null && slot > 0) {
            BattleInfo battleInfo = mBattleInfos.remove(slot);
            if (battleInfo != null) {
                for (PlayerInfo playerInfo : battleInfo.getPlayers()) {
                    mBattleInfosHashMap.remove(playerInfo.getClientId().toString());
                }
            }

            CronusChatMessageRemove cronusChatMessageRemove =
                    new CronusChatMessageRemove(slot);
            for (ClientInfo clientInfo : ServerLogic.getInstance().getSessions()) {
                ServerLogic.getInstance().postMessage(clientInfo, cronusChatMessageRemove);
            }
        }
    }

    public BattleInfo getBattleInfo(int mSlotId) {
        return mBattleInfos.get(mSlotId);
    }

    public BattleInfo getBattleInfoForClient(ClientInfo clientInfo) {
        try {
            return mBattleInfos.get(mBattleInfosHashMap.get(clientInfo.getClientId().toString()));
        } catch (Exception ignored) { }

        return null;
    }

    public void startBattle(BattleInfo battleInfo) {
        for (PlayerInfo playerInfo : battleInfo.getPlayers()) {
            mBattleInfosHashMap.putIfAbsent(playerInfo.getClientId().toString(), battleInfo.getSlotId());
        }

        scheduleTask(new BattleTask(BattleTask.TASK.START_BATTLE, battleInfo));
    }

    public void onClientBattleEvent(ClientBattleEvent clientBattleEvent, ClientInfo clientInfo) {
        BattleInfo battleInfo = getBattleInfoForClient(clientInfo);

        if (battleInfo != null) {
            battleInfo.onBattleEvent(clientBattleEvent);
        }
    }

    private void scheduleTask(BattleTask task) {
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

                        ClientBattleEvent clientBattleEvent;
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
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }

                        if (battleInfo.getLastBattleEvent() > 0 &&
                                System.currentTimeMillis() - battleInfo.getLastBattleEvent() > 2750) {

                            for (PlayerInfo playerInfo : battleInfo.getPlayers()) {
                                ServerLogic.getInstance().postMessage(playerInfo.getClientInfo(),
                                        new BattleResult(battleInfo));
                            }

                            BattleLogic.getInstance().cancelBattle(battleInfo.getSlotId());
                            break;
                        }
                    }
                }

                break;
            }
        }
    }
}
