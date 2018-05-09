package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.CRUtils;
import com.royale.titans.cronus.Utils;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.models.BattleInfo;
import com.royale.titans.cronus.models.PlayerInfo;

import java.io.IOException;

public class BattleResult extends ServerMessage {
    private final BattleInfo mBattleInfo;

    public BattleResult(BattleInfo battleInfo) {
        mBattleInfo = battleInfo;
    }

    @Override
    public int getId() {
        return 22561;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer outBuffer = OutBuffer.newBuffer();

        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(42);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(8);

        for (PlayerInfo playerInfo : mBattleInfo.getPlayers()) {
            writePlayer(outBuffer, playerInfo);
        }

        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(43);
        outBuffer.writeRrsInt(4290);
        outBuffer.writeRrsInt(4323);
        outBuffer.writeRrsInt(System.currentTimeMillis() / 1000);
        outBuffer.writeRrsInt(11);
        outBuffer.writeRrsInt(4290);
        outBuffer.writeRrsInt(5210);
        outBuffer.writeRrsInt(5779);
        outBuffer.writeRrsInt(1085773658);
        outBuffer.writeRrsInt(14);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(13);

        for (PlayerInfo playerInfo : mBattleInfo.getPlayers()) {
            outBuffer.writeRrsInt(playerInfo.getClientId().high());
            outBuffer.writeRrsInt(playerInfo.getClientId().low());
            outBuffer.write((byte) 0);
        }

        outBuffer.writeInt(0);
        outBuffer.writeInt(0);

        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(7);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(34);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(4001);
        outBuffer.writeRrsInt(1);

        outBuffer.writeInt(1860618);
        outBuffer.writeRrsInt(432);
        outBuffer.writeRrsInt(-7873);
        outBuffer.writeRrsInt(-8071);

        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(35);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(35);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(35);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(34);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(34);
        outBuffer.writeRrsInt(37);
        outBuffer.writeRrsInt(34);
        outBuffer.writeRrsInt(15);
        outBuffer.writeRrsInt(34);
        outBuffer.writeRrsInt(33);
        outBuffer.writeRrsInt(34);
        outBuffer.writeRrsInt(16);

        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(113);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(114);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(119);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(120);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(121);

        outBuffer.writeRrsInt(11);
        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(3500);
        outBuffer.writeRrsInt(25500);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(41);
        outBuffer.writeRrsInt(-7941);
        outBuffer.writeRrsInt(214150);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(10);
        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(9000);
        outBuffer.writeRrsInt(3000);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-7971);
        outBuffer.writeRrsInt(125);
        outBuffer.writeRrsInt(214150);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(7);
        outBuffer.writeRrsInt(-63);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-61);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(6);
        outBuffer.writeRrsInt(3);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(3);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(4);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(85000);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(6);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(3350);
        outBuffer.writeRrsInt(25);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        PlayerInfo playerInfo = mBattleInfo.getPlayers().get(0);

        for (CRUtils.CardInfo cardInfo : playerInfo.getClientInfo().getCurrentDeck()) {
            outBuffer.writeRrsInt(cardInfo.getScId());
        }
        outBuffer.writeRrsInt(-64);
        outBuffer.writeInt(0);

        outBuffer.writeRrsInt(11);
        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(9000);
        outBuffer.writeRrsInt(29000);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-8052);
        outBuffer.writeRrsInt(-7980);
        outBuffer.writeRrsInt(214150);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(6);
        outBuffer.writeRrsInt(-60);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(-60);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(3);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(7);
        outBuffer.writeRrsInt(7);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(6);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(3350);
        outBuffer.writeRrsInt(32);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        playerInfo = mBattleInfo.getPlayers().get(1);

        for (CRUtils.CardInfo cardInfo : playerInfo.getClientInfo().getCurrentDeck()) {
            outBuffer.writeRrsInt(cardInfo.getScId());
        }
        outBuffer.writeInt(0);

        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(15);
        outBuffer.writeRrsInt(3262);
        outBuffer.writeRrsInt(9215);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(177);
        outBuffer.writeRrsInt(-8008);
        outBuffer.writeRrsInt(22750);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(1);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(10);
        outBuffer.writeRrsInt(15);
        outBuffer.writeRrsInt(4831);
        outBuffer.writeRrsInt(12529);
        outBuffer.writeRrsInt(1500);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(6);
        outBuffer.writeRrsInt(184);
        outBuffer.writeRrsInt(-8016);
        outBuffer.writeRrsInt(19850);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(1);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(15);
        outBuffer.writeRrsInt(7048);
        outBuffer.writeRrsInt(10469);
        outBuffer.writeRrsInt(3500);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(6);
        outBuffer.writeRrsInt(-7950);
        outBuffer.writeRrsInt(-8113);
        outBuffer.writeRrsInt(10000);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(1);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(15);
        outBuffer.writeRrsInt(2891);
        outBuffer.writeRrsInt(6453);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(215);
        outBuffer.writeRrsInt(137);
        outBuffer.writeRrsInt(6850);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(1);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(15);
        outBuffer.writeRrsInt(4762);
        outBuffer.writeRrsInt(7647);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(3);
        outBuffer.writeRrsInt(-8016);
        outBuffer.writeRrsInt(185);
        outBuffer.writeRrsInt(6100);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(1);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(9000);
        outBuffer.writeRrsInt(3000);

        outBuffer.writeInt(5);
        outBuffer.writeRrsInt(4);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2891);
        outBuffer.writeRrsInt(6453);

        outBuffer.writeInt(5);
        outBuffer.writeRrsInt(120);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(9000);
        outBuffer.writeRrsInt(3000);

        outBuffer.writeInt(5);
        outBuffer.writeRrsInt(4);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(4762);
        outBuffer.writeRrsInt(7647);

        outBuffer.writeInt(5);
        outBuffer.writeRrsInt(121);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(3500);
        outBuffer.writeRrsInt(7048);
        outBuffer.writeRrsInt(10469);

        outBuffer.writeInt(5);
        outBuffer.writeRrsInt(119);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(3262);
        outBuffer.writeRrsInt(9215);

        outBuffer.writeInt(5);
        outBuffer.writeRrsInt(113);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(4762);
        outBuffer.writeRrsInt(7647);

        outBuffer.writeInt(5);
        outBuffer.writeRrsInt(121);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(3262);
        outBuffer.writeRrsInt(9215);

        outBuffer.writeInt(5);
        outBuffer.writeRrsInt(113);
        outBuffer.writeRrsInt(6);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(619);
        outBuffer.write((byte) 0);

        outBuffer.writeInt(0);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(185);
        outBuffer.writeRrsInt(-8017);

        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(8900);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(6);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeInt(0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(9950);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(6);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(733);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-7987);
        outBuffer.writeRrsInt(-8042);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1150);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(6);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeInt(0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(15751);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(6);
        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeInt(0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(800);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-64);
        outBuffer.writeRrsInt(-64);

        outBuffer.writeRrsInt(397);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(3320);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(4584);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(839);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(1011);
        outBuffer.writeRrsInt(100);

        outBuffer.writeRrsInt(904);
        outBuffer.writeRrsInt(100);

        outBuffer.writeRrsInt(801);
        outBuffer.writeRrsInt(100);

        outBuffer.writeRrsInt(1151);
        outBuffer.writeRrsInt(100);

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

        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);

        outBuffer.writeInt(0);
        outBuffer.writeRrsInt(100);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(-128);
        outBuffer.writeRrsInt(79);
        outBuffer.writeRrsInt(10);
        outBuffer.writeRrsInt(33);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(78);
        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(19);
        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(8);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(9);
        outBuffer.writeRrsInt(16);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(23);
        outBuffer.writeRrsInt(10);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(-128);
        outBuffer.writeRrsInt(10);
        outBuffer.writeRrsInt(6);
        outBuffer.writeRrsInt(71);
        outBuffer.writeRrsInt(9);
        outBuffer.writeRrsInt(33);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(84);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(90);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(28);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(40);
        outBuffer.writeRrsInt(10);
        outBuffer.writeRrsInt(86);
        outBuffer.writeRrsInt(11);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(6);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(3);

        outBuffer.writeInt(0);
        outBuffer.writeInt(0);

        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(11);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(12);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(1711156944);
        outBuffer.write((byte) 0);

        byte[] out = outBuffer.obtain().rewind().array();
        byte[] compressed;
        int l = out.length;
        try {
            compressed = Utils.compress(out);
        } catch (IOException e) {
            compressed = new byte[0];
        }

        OutBuffer returnBuffer = OutBuffer.newBuffer();
        returnBuffer.writeRrsInt(1);
        returnBuffer.writeRrsInt(19);
        returnBuffer.writeRrsInt(226);
        returnBuffer.writeInt(compressed.length + 5);
        returnBuffer.writeRrsInt(1);
        returnBuffer.writeIntLe(l);
        returnBuffer.write(compressed);

        return returnBuffer.obtain();
    }

    private void writePlayer(OutBuffer outBuffer, PlayerInfo playerInfo) {
        outBuffer.writeRrsInt(playerInfo.getClientId().high());
        outBuffer.writeRrsInt(playerInfo.getClientId().low());
        outBuffer.writeRrsInt(playerInfo.getClientId().high());
        outBuffer.writeRrsInt(playerInfo.getClientId().low());
        outBuffer.writeRrsInt(playerInfo.getClientId().high());
        outBuffer.writeRrsInt(playerInfo.getClientId().low());
        outBuffer.writeString(playerInfo.getClientInfo().getPlayerName());

        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(6500);
        outBuffer.writeRrsInt(241);
        outBuffer.writeRrsInt(1860);
        outBuffer.writeRrsInt(4023);
        outBuffer.writeRrsInt(43);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(3965);
        outBuffer.writeRrsInt(12);
        outBuffer.writeRrsInt(43);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(3965);
        outBuffer.writeRrsInt(4164);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(13);
        outBuffer.writeRrsInt(8);

        outBuffer.writeRrsInt(17);

        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(48996);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(1192);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(3);
        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(12);
        outBuffer.writeRrsInt(1436);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(13);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(14);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(15);
        outBuffer.writeRrsInt(1965);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(22);
        outBuffer.writeRrsInt(1352);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(25);
        outBuffer.writeRrsInt(492238305);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(19);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(28);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(29);
        outBuffer.writeRrsInt(72000006);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(33);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(34);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(36);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(37);
        outBuffer.write((byte) 0);

        outBuffer.writeInt(5);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(6);
        outBuffer.writeRrsInt(4164);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(7);
        outBuffer.writeRrsInt(1354);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(11);
        outBuffer.writeRrsInt(42);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(20);
        outBuffer.writeRrsInt(10);
        outBuffer.writeRrsInt(5);
        outBuffer.writeRrsInt(27);
        outBuffer.writeRrsInt(13);

        outBuffer.writeRrsInt(9);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(3);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(7);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(18);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(32);
        outBuffer.writeRrsInt(4);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(50);
        outBuffer.writeRrsInt(47);
        outBuffer.writeRrsInt(26);
        outBuffer.writeRrsInt(62);
        outBuffer.writeRrsInt(48);

        outBuffer.writeRrsInt(28);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(11);
        outBuffer.writeRrsInt(2);

        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(1);
        outBuffer.writeString("Cronus");

        outBuffer.writeRrsInt(137);
        outBuffer.writeRrsInt(115);
        outBuffer.writeRrsInt(6673);
        outBuffer.writeRrsInt(35);
        outBuffer.write((byte) 0);
        outBuffer.writeRrsInt(2757);
        outBuffer.writeRrsInt(2804);

        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(8);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);
        outBuffer.write((byte) 0);

        outBuffer.writeRrsInt(2);
        outBuffer.writeRrsInt(3);
        outBuffer.writeRrsInt(1);
        outBuffer.writeRrsInt(1);

        outBuffer.writeInt(1);
        outBuffer.writeInt(1);
    }
}
