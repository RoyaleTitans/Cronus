package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.CRUtils;
import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.Utils;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.models.BattleInfo;
import com.royale.titans.cronus.models.PlayerInfo;

public class SectorState extends ServerMessage  {
    private final BattleInfo mBattleInfo;
    private final PlayerInfo mThisPlayer;

    public SectorState(BattleInfo battleInfo, PlayerInfo thisPlayer) {
        mBattleInfo = battleInfo;
        mThisPlayer = thisPlayer;
    }

    @Override
    public int getId() {
        return 23876;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        String p1_5 = "00000005060202040201030000000000000000040105000c000c000000c8e99c0b00";
        String p2_5 = "00000506020204020103000000000000000000031404000c000000f6f7f0f30600";

        int thisPlayerIndex = mBattleInfo.getPlayers().indexOf(mThisPlayer);

        OutBuffer outBuffer = OutBuffer.newBuffer();

        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(42);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(2);
        if (thisPlayerIndex == 0) {
            outBuffer.write((byte) 0);
        } else if (thisPlayerIndex == 1) {
            outBuffer.writeRrsInt(1);
        }

        writeEvents(outBuffer);

        for (int i=0;i<mBattleInfo.getPlayers().size();i++) {
            writePlayerInfo(outBuffer, mBattleInfo.getPlayers().get(i), i);
        }

        outBuffer.writeRrsInt(43);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(33);
        outBuffer.writeRrsInt(mBattleInfo.getGameStartTimestamp() / 1000);
        outBuffer.writeRrsInt(11);

        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(21);

        outBuffer.write((byte) -47);
        outBuffer.write((byte) 18);
        outBuffer.write((byte) -62);
        outBuffer.write((byte) -111);
        outBuffer.write((byte) -5);
        outBuffer.write((byte) -38);
        outBuffer.write((byte) -23);

        int arena = mBattleInfo.getArena();
        int arenaId;

        if (mBattleInfo.getEventId() == 1385) {
            arena = 27;
            arenaId = 27;
        } else {
            if (arena == -64) {
                arena = 1 + (12 - 1) * ServerLogic.getInstance().getRandom().nextInt();
            }
            arenaId = CRUtils.arenaToId(arena);
        }

        outBuffer.write((byte) arena);
        outBuffer.write((byte) (arenaId + 1));
        outBuffer.write((byte) 0);
        outBuffer.write((byte) arenaId);

        for (PlayerInfo playerInfo : mBattleInfo.getPlayers()) {
            outBuffer.writeRrsInt(playerInfo.getClientId().high());
            outBuffer.writeRrsInt(playerInfo.getClientId().low());
            outBuffer.write((byte) 0);
        }

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(3);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(8);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(28);
        outBuffer.writeRrsInt(-37);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(6);
        outBuffer.writeRrsInt(-59);

        outBuffer.writeRrsInt(6);

        outBuffer.writeRrsInt(35);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(35);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(35);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(35);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(35);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(35);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(5);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(3);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(5);

        writeTower(outBuffer, 0, 14500, 25500);
        writeTower(outBuffer, 1, 3500, 6500);
        writeTower(outBuffer, 2, 3500, 25500);
        writeTower(outBuffer, 3, 14500, 6500);

        writeKingTower(outBuffer, 0, 9000, 3000, thisPlayerIndex);
        writeKingTower(outBuffer, 1, 9000, 29000, thisPlayerIndex);

        byte[] b = new byte[48];
        outBuffer.write(b);

        outBuffer.writeRrsInt(2534);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2534);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2534);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2534);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(4008);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(4008);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);

        outBuffer.write((byte) 0);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(100);

        outBuffer.write((byte) 0);

        if (thisPlayerIndex == 0) {
            outBuffer.writeRrsInt(-128);
        } else if (thisPlayerIndex == 1) {
            outBuffer.writeRrsInt(-255);
        }

        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(0).getId());
        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(0).getMaxLevel() - 1);

        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(1).getId());
        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(1).getMaxLevel() - 1);

        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(2).getId());
        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(2).getMaxLevel() - 1);

        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(3).getId());
        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(3).getMaxLevel() - 1);

        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(4).getId());
        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(4).getMaxLevel() - 1);

        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(5).getId());
        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(5).getMaxLevel() - 1);

        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(6).getId());
        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(6).getMaxLevel() - 1);

        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(7).getId());
        outBuffer.writeRrsInt(mThisPlayer.getClientInfo().getCurrentDeck().get(7).getMaxLevel() - 1);

        if (thisPlayerIndex == 0) {
            outBuffer.write(Utils.h2b(p1_5));
        } else if (thisPlayerIndex == 1) {
            outBuffer.write(Utils.h2b(p2_5));
        }

        return outBuffer.obtain();
    }

    private void writeEvents(OutBuffer outBuffer) {
        outBuffer.writeRrsInt(4);

        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(261);
        outBuffer.write((byte) 0);
        outBuffer.writeString("TID_LADDER_QUEST_2V2_WIN");
        outBuffer.writeString("");
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(293);
        outBuffer.write((byte) 0);
        outBuffer.writeString("TID_LADDER_QUEST_PLAY");
        outBuffer.writeString("");
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(10);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(5);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(295);
        outBuffer.write((byte) 0);
        outBuffer.writeString("TID_LADDER_QUEST_WIN_2_CARDS");
        outBuffer.writeString("");
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(3);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(17);
        outBuffer.writeRrsInt(32);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(1369);
        outBuffer.writeRrsInt(1);
        outBuffer.writeString("Clan Wars Quest");
        outBuffer.writeString("icon_quest_type_specialevent");
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(20);
        outBuffer.writeRrsInt(12);
        outBuffer.writeRrsInt(12);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(64);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2);
    }

    private void writePlayerInfo(OutBuffer outBuffer, PlayerInfo playerInfo, int playerIndex) {
        outBuffer.writeRrsInt(playerInfo.getClientId().high());
        outBuffer.writeRrsInt(playerInfo.getClientId().low());
        outBuffer.writeRrsInt(playerInfo.getClientId().high());
        outBuffer.writeRrsInt(playerInfo.getClientId().low());
        outBuffer.writeRrsInt(playerInfo.getClientId().high());
        outBuffer.writeRrsInt(playerInfo.getClientId().low());
        outBuffer.writeString(playerInfo.getClientInfo().getPlayerName());

        // writing first player
        if (playerIndex == 0) {
            outBuffer.writeRrsInt(13);
            outBuffer.writeRrsInt(6500);
            outBuffer.writeRrsInt(328);
            outBuffer.writeRrsInt(6500);
            outBuffer.writeRrsInt(6500);
            outBuffer.writeRrsInt(40);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(6500);
            outBuffer.writeRrsInt(14);
            outBuffer.writeRrsInt(42);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(6500);
            outBuffer.writeRrsInt(6500);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(14);
            outBuffer.writeRrsInt(8);
            outBuffer.writeRrsInt(19);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(1);
            outBuffer.writeRrsInt(141684);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(2);
            outBuffer.writeRrsInt(1498);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(3);
            outBuffer.writeRrsInt(1);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(4);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(12);
            outBuffer.writeRrsInt(1969);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(13);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(14);
            outBuffer.writeRrsInt(1);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(15);
            outBuffer.writeRrsInt(1511);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(22);
            outBuffer.writeRrsInt(1997);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(25);
            outBuffer.writeRrsInt(311625908);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(26);
            outBuffer.writeRrsInt(6);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(28);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(29);
            outBuffer.writeRrsInt(72000007);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(33);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(34);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(35);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(36);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(38);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(37);
            outBuffer.write((byte) 0);

            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(6);
            outBuffer.writeRrsInt(6500);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(7);
            outBuffer.writeRrsInt(1429);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(11);
            outBuffer.writeRrsInt(31);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(20);
            outBuffer.writeRrsInt(12);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(27);
            outBuffer.writeRrsInt(14);

            outBuffer.write((byte) 0);

            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(12);
            outBuffer.writeRrsInt(2);
            outBuffer.writeRrsInt(1);
            outBuffer.writeRrsInt(1);
            outBuffer.writeString("Cronus");
            outBuffer.writeRrsInt(153);
            outBuffer.writeRrsInt(48);
            outBuffer.writeRrsInt(8491);
            outBuffer.writeRrsInt(33);
            outBuffer.writeRrsInt(2005);
            outBuffer.writeRrsInt(3666);
            outBuffer.writeRrsInt(3459);
            outBuffer.writeRrsInt(1);
            outBuffer.writeRrsInt(14);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(1);
            outBuffer.writeRrsInt(4);
            outBuffer.writeShort(23017);
            outBuffer.writeRrsInt(356);
            outBuffer.writeRrsInt(1);
            outBuffer.writeRrsInt(12);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(-64);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(1);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(29);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(6485);
            outBuffer.writeRrsInt(1);
        } else if (playerIndex == 1) {
            // write second player
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(6500);
            outBuffer.writeRrsInt(72);
            outBuffer.writeRrsInt(1138);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(42);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(8);
            outBuffer.writeRrsInt(13);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(1);
            outBuffer.writeRrsInt(506);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(2);
            outBuffer.writeRrsInt(73);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(3);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(4);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(12);
            outBuffer.writeRrsInt(369);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(13);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(14);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(22);
            outBuffer.writeRrsInt(435);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(28);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(29);
            outBuffer.writeRrsInt(72000007);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(34);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(36);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(37);
            outBuffer.write((byte) 0);

            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(4);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(6);
            outBuffer.writeRrsInt(6500);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(7);
            outBuffer.writeRrsInt(53);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(11);
            outBuffer.writeRrsInt(42);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(27);
            outBuffer.writeRrsInt(4);

            outBuffer.write((byte) 0);

            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(6);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(156);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(78);
            outBuffer.writeRrsInt(57);
            outBuffer.writeRrsInt(-64);
            outBuffer.writeRrsInt(11);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(-64);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
        }
    }

    private void writeTower(OutBuffer outBuffer, int towerIndex, int x, int y) {
        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(x);
        outBuffer.writeRrsInt(y);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);
        if (towerIndex == 0 || towerIndex == 2) {
            outBuffer.writeRrsInt(-7937);
        } else if (towerIndex == 1 || towerIndex == 3) {
            outBuffer.writeRrsInt(256);
        }
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        if (towerIndex == 0 || towerIndex == 3) {
            outBuffer.writeRrsInt(2);
        } else if (towerIndex == 1 || towerIndex == 2) {
            outBuffer.writeRrsInt(1);
        }
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
    }

    private void writeKingTower(OutBuffer outBuffer, int towerIndex, int x, int y, int thisPlayerIndex) {
        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(x);
        outBuffer.writeRrsInt(y);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);
        if (towerIndex == 0) {
            outBuffer.writeRrsInt(256);
        } else if (towerIndex == 1) {
            outBuffer.writeRrsInt(-7937);
        }
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        if (thisPlayerIndex == 0 && towerIndex == 0) {
            outBuffer.writeRrsInt(26);
            outBuffer.writeRrsInt(4);
            outBuffer.writeRrsInt(7);
            outBuffer.writeRrsInt(-62);
            outBuffer.writeRrsInt(-63);
            outBuffer.writeRrsInt(4);
            outBuffer.writeRrsInt(4);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(3);
            outBuffer.writeRrsInt(1);
            outBuffer.write((byte) 0);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(-64);
            outBuffer.writeRrsInt(-64);
            outBuffer.writeRrsInt(-64);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(-64);
        } else if (thisPlayerIndex == 1 && towerIndex == 1) {
            outBuffer.writeRrsInt(26);
            outBuffer.writeRrsInt(4);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(6);
            outBuffer.writeRrsInt(-61);
            outBuffer.writeRrsInt(2);
            outBuffer.writeRrsInt(4);
            outBuffer.writeRrsInt(5);
            outBuffer.writeRrsInt(3);
            outBuffer.writeRrsInt(7);
            outBuffer.writeRrsInt(1);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(-64);
            outBuffer.writeRrsInt(-64);
            outBuffer.writeRrsInt(-64);
            outBuffer.write((byte) 0);
            outBuffer.writeRrsInt(-64);
        } else if ((thisPlayerIndex == 1 && towerIndex == 0) ||
                (thisPlayerIndex == 0 && towerIndex == 1)) {
            outBuffer.writeRrsInt(24);
        }

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(5);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
    }
}