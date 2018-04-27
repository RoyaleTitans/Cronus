package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.BattleLogic;
import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class SectorState extends ServerMessage  {
    private final BattleLogic.BattleInfo mBattleInfo;
    private final ServerLogic.ClientInfo mThisPlayer;

    public SectorState(BattleLogic.BattleInfo battleInfo, ServerLogic.ClientInfo thisPlayer) {
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
        OutBuffer outBuffer = OutBuffer.newBuffer();

        outBuffer.write((byte) 0);

        outBuffer.write((byte) 1);
        outBuffer.writeRrsInt(42);

        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(2);

        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(261);
        outBuffer.write((byte) 0);
        outBuffer.writeString("TID_LADDER_QUEST_2V2_WIN");
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeInt(5);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(288);
        outBuffer.write((byte) 0);
        outBuffer.writeString("TID_LADDER_QUEST_PLAY");
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeInt(10);
        outBuffer.write((byte) 5);
        outBuffer.write((byte) 5);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.write((byte) 1);
        outBuffer.write((byte) 1);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 4);
        outBuffer.writeRrsInt(289);
        outBuffer.write((byte) 0);
        outBuffer.writeString("TID_CAST_QUEST_SPELL");
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(50);
        outBuffer.write((byte) 4);
        outBuffer.write((byte) 4);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(28);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.write((byte) 1);
        outBuffer.write((byte) 1);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 2);
        outBuffer.writeRrsInt(1369);
        outBuffer.write((byte) 1);
        outBuffer.writeString("Clan Wars Quest");
        outBuffer.writeString("icon_quest_type_specialevent");
        outBuffer.write((byte) 1);
        outBuffer.writeRrsInt(20);
        outBuffer.writeRrsInt(3);
        outBuffer.writeRrsInt(3);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(64);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2);

        for (ServerLogic.ClientInfo player : mBattleInfo.getPlayers()) {
            if (!player.getClientId().equals(mThisPlayer.getClientId())) {
                writePlayerInfo(outBuffer, player);
            }
        }
        writePlayerInfo(outBuffer, mThisPlayer);

        outBuffer.writeRrsInt(43);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(33);
        System.out.println("GAME START: " + String.valueOf(mBattleInfo.getGameStartTimestamp() / 1000));
        //outBuffer.writeRrsInt(mBattleInfo.getGameStartTimestamp() / 1000);
        outBuffer.writeRrsInt(1524864081);

        outBuffer.writeRrsInt(11);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(21);
        outBuffer.writeInt(1254674151);
        outBuffer.writeRrsInt(-12374346);

        outBuffer.writeRrsInt(30);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(25);

        for (ServerLogic.ClientInfo player : mBattleInfo.getPlayers()) {
            if (!player.getClientId().equals(mThisPlayer.getClientId())) {
                outBuffer.writeRrsInt(player.getClientId().high());
                outBuffer.writeRrsInt(player.getClientId().low());
                outBuffer.write((byte) 0);
            }
        }
        outBuffer.writeRrsInt(mThisPlayer.getClientId().high());
        outBuffer.writeRrsInt(mThisPlayer.getClientId().low());
        outBuffer.write((byte) 0);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(4);
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

        outBuffer.writeRrsInt(116);
        outBuffer.writeRrsInt(-8077);

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

        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(14500);
        outBuffer.writeRrsInt(25500);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-7937);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(3500);
        outBuffer.writeRrsInt(6500);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(256);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(3500);
        outBuffer.writeRrsInt(25500);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-7937);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(14500);
        outBuffer.writeRrsInt(6500);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(256);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(9000);
        outBuffer.writeRrsInt(3000);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(256);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(24);
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

        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(9000);
        outBuffer.writeRrsInt(29000);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-7937);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(3);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(-59);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(4);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
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

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
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
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(100);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(100);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(100);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(100);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(100);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(100);

        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-255);

        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(0).getId());
        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(0).getMaxLevel());

        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(1).getId());
        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(1).getMaxLevel());

        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(2).getId());
        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(2).getMaxLevel());

        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(3).getId());
        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(3).getMaxLevel());

        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(4).getId());
        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(4).getMaxLevel());

        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(5).getId());
        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(5).getMaxLevel());

        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(6).getId());
        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(6).getMaxLevel());

        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(7).getId());
        outBuffer.writeRrsInt(mThisPlayer.getCurrentDeck().get(7).getMaxLevel());

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(6);

        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(2);

        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(2);

        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(3);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(3);
        outBuffer.writeRrsInt(12);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-1118102222);
        outBuffer.write((byte) 0);

        return outBuffer.obtain();
    }

    private void writePlayerInfo(OutBuffer outBuffer, ServerLogic.ClientInfo clientInfo) {
        outBuffer.writeRrsInt(clientInfo.getClientId().high());
        outBuffer.writeRrsInt(clientInfo.getClientId().low());
        outBuffer.writeRrsInt(clientInfo.getClientId().high());
        outBuffer.writeRrsInt(clientInfo.getClientId().low());
        outBuffer.writeRrsInt(clientInfo.getClientId().high());
        outBuffer.writeRrsInt(clientInfo.getClientId().low());
        outBuffer.writeString(clientInfo.getPlayerName());

        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(3689);
        outBuffer.writeRrsInt(30);
        outBuffer.writeRrsInt(2238);

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
        outBuffer.write((byte) 8);

        outBuffer.writeRrsInt(19);

        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(20041);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(1066);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(3);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(12);
        outBuffer.writeRrsInt(1098);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(14);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(15);
        outBuffer.writeRrsInt(1170);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(22);
        outBuffer.writeRrsInt(1188);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(25);
        outBuffer.writeRrsInt(285998745);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(9);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(28);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(29);
        outBuffer.writeRrsInt(72000023);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(33);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(34);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(35);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(36);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(38);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(37);
        outBuffer.writeRrsInt(0);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 5);

        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(6);
        outBuffer.writeRrsInt(3905);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(7);
        outBuffer.writeRrsInt(697);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(11);
        outBuffer.writeRrsInt(42);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(20);
        outBuffer.writeRrsInt(10);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(27);
        outBuffer.writeRrsInt(12);

        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(62);
        outBuffer.writeRrsInt(52);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(0);
        outBuffer.writeRrsInt(10);
        outBuffer.writeRrsInt(2);

        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(1);
        outBuffer.writeString("Cronus");

        outBuffer.writeRrsInt(153);

        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(3576);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1193);
        outBuffer.writeRrsInt(1113);

        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(7);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);

        if (!clientInfo.getClientId().equals(mThisPlayer.getClientId())) {
            outBuffer.writeRrsInt(2);
        } else {
            outBuffer.write((byte) 0);
        }
    }

    public BattleLogic.BattleInfo getBattleInfo() {
        return mBattleInfo;
    }
}