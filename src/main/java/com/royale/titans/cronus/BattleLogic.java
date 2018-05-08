package com.royale.titans.cronus;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.client.ClientBattleEvent;
import com.royale.titans.cronus.messages.server.CronusChatMessageRemove;
import com.royale.titans.cronus.messages.server.CustomBufferMessage;
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
                                System.currentTimeMillis() - battleInfo.getLastBattleEvent() > 2500) {

                            for (PlayerInfo playerInfo : battleInfo.getPlayers()) {
                                ServerLogic.getInstance().postMessage(playerInfo.getClientInfo(),
                                        new CustomBufferMessage(22561,
                                                Buffer.wrap(Utils.h2b("011e62923f963e96030000020113a2030000036e01dd040000789c95525d6c5375143fe77fefb9bdbd9f9441e7feeb606d01910624a87b30713a2790003e687cf2c5cc20328923211a226b7775ccf58ec1868394b055605909534664f2d12e7c9988fa64f041627c3010880fc28b40c28b1a3db72d9df3cd26e79c9edff9fcfdcfc51542772f168fd3ac0200ed9ded5d1fbcfa8c9d6b9d56fa9a8aad29b8f49c15a8be36b0f50861e12f2231399f14412a92958f920de420b9a71651d45f480de33f15af2b24eb2886d4d4ef5f7f89e24009a025404b790010697d6d14dab390cc15b4c0a0463b2c15558654e9aa72be2aa38a6c56e59a2765ebea1808005344760feee242e3b5b73ade6dded4f9f6d6f706c419fc646b123e4e9d4da11e7405a120b28900eed886f6475fff189a554178fd4a7be4799f7a970dbfb01cc6d63a29986af7d781a3d711e687ea499caa274521559035bd886c498e46eee12445f72da686dcc33f2604499362620ea76499d6b25966fe3a0a7d1e2573312db0a8d1d16558c8460a083d4e8d2111d3d4984e318b5fda124d859b4bb8ca79b163cb96ceb6973bbade7f7347e7f6c3e2047dd68f711879b6d4e239156e50e6d604b87627a47adb27da2fdc1efac2347bdb475f39f0fae82f17077507ecca091f11aefc823af61230de8a10db6c9cd6beeafeb6474f621292909089a50937114fcc434e6401e2a312d134d2974845a412d20c9af6544b7e4801f09eb89aee1f9180959dc0b027f7626915076ea52f214744755ba9867abcb48a425340f154f0fcd30668907bba01207be7d7c776b3e4588eb10cb37cf8e7cf7284ad17949b4157ff9b60de83ccddf2c05a5bad5bed168a2a4428e409e0f4a06933c01817f77393a0a1cf762f4bf6c18d8613ec0fb094afe35e5e73659877a769f15de6c86d216a3cdca31bcf5fc57c3d6825712db3e7fc6c885cbfebf8044eb580f67dfaf79efd79ac85843bb8f2dc66b6879401d1bbad86ebeee886a33b01946b991971f08d1a0ed5c70252012aa5c00f3c17af9402bf3a4cb59447075e91757579de1ee7a4fd3ba0e1c970f5f033e2870cf40d22789e16f85e053e325646f053b38add4fff9681cb91ffa4651f066973b1631643fbb40242e9295693eb59656d5667dc02fa4e01c7ad025e8914ca7c2bfaff99bf71188d380ea15ea7eba486e751bd018c1a5a16c371711069142946cb8d1cf2f6a4012ab5ef1c84295158fc67ff857b372cf807ccae0c1100360c360c360c360c00"))));
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
