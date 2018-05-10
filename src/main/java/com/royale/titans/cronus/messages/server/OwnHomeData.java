package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.Utils;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.CRUtils;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.models.ClientInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OwnHomeData extends ServerMessage {
    private final ClientInfo mInfo;

    private final boolean mCardOffersEnabled = false;

    public OwnHomeData(ClientInfo info) {
        mInfo = info;
    }

    @Override
    public int getId() {
        return 28337;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer b = OutBuffer.newBuffer();

        b.writeLong(mInfo.getClientId().lon());
        b.writeRrsInt(6845);
        b.writeRrsInt(1501);
        b.writeRrsInt(587420);
        b.writeRrsInt(1386060);
        b.writeRrsInt(System.currentTimeMillis() / 1000);
        b.writeRrsInt(28);

        ArrayList<CRUtils.CardInfo> selectedDeck = writeDecks(b);

        mInfo.setCurrentDeck(selectedDeck);

        b.write((byte) -1);

        writeCard(b, selectedDeck.get(0));
        writeCard(b, selectedDeck.get(1));
        writeCard(b, selectedDeck.get(2));
        writeCard(b, selectedDeck.get(3));
        writeCard(b, selectedDeck.get(4));
        writeCard(b, selectedDeck.get(5));
        writeCard(b, selectedDeck.get(6));
        writeCard(b, selectedDeck.get(7));

        b.writeRrsInt(CRUtils.CARDS.length - 8);
        for (int i=0;i<CRUtils.CARDS.length;i++) {
            if (!selectedDeck.contains(CRUtils.CARDS[i])) {
                writeCard(b, CRUtils.CARDS[i]);
            }
        }

        b.write((byte) 0);
        b.write((byte) -1);

        int[] compBytes = new int[] { 39, 24, 47, 88, 29, 31, 42, 36 };
        for (int i=0;i<8;i++) {
            b.writeRrsInt(compBytes[i]);
            b.write((byte) 0);
            b.write((byte) 127);
            b.write((byte) 0);
            b.write((byte) 0);
            b.write((byte) 0);
            b.write((byte) 0);
        }

        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 1);
        b.write((byte) 0);

        // events
        writeEvents(b);

        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(64);
        b.write((byte) 0);
        b.writeRrsInt(1522569600);
        b.writeRrsInt(-64);
        b.writeRrsInt(1370);

        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 9);

        b.writeRrsInt(1109);
        b.write((byte) 1);

        b.writeRrsInt(1354);
        b.write((byte) 1);

        b.writeRrsInt(1364);
        b.write((byte) 1);

        b.writeRrsInt(1365);
        b.write((byte) 1);

        b.writeRrsInt(1366);
        b.write((byte) 1);

        b.writeRrsInt(1367);
        b.write((byte) 1);

        b.writeRrsInt(1368);
        b.write((byte) 1);

        b.writeRrsInt(1369);
        b.write((byte) 1);

        b.writeRrsInt(1370);
        b.write((byte) 1);

        b.writeRrsInt(9);

        b.writeRrsInt(1109);
        b.write((byte) 2);
        b.write((byte) 0);

        b.writeRrsInt(1354);
        b.write((byte) 2);
        b.write((byte) 0);

        b.writeRrsInt(1364);
        b.write((byte) 3);
        b.write((byte) 0);

        b.writeRrsInt(1365);
        b.write((byte) 3);
        b.write((byte) 0);

        b.writeRrsInt(1366);
        b.write((byte) 3);
        b.write((byte) 0);

        b.writeRrsInt(1367);
        b.write((byte) 3);
        b.write((byte) 0);

        b.writeRrsInt(1368);
        b.write((byte) 3);
        b.write((byte) 0);

        b.writeRrsInt(1369);
        b.write((byte) 2);
        b.write((byte) 0);

        b.writeRrsInt(1370);
        b.write((byte) 2);
        b.write((byte) 0);

        b.write((byte) 3);

        b.writeString("{\"ID\":\"SHOP_CYCLE_MANAGEMENT\",\"Params\":{\"LegendaryChestCycleDuration\":14}}");
        b.write((byte) 1);
        b.writeString("{\"ID\":\"CARD_RELEASE_V2\",\"Params\":{\"Cards\":[{\"ShowAsSoon\":false,\"Spell\":\"Ghost\",\"Date\":\"20180104\"},{\"ShowAsSoon\":false,\"Spell\":\"EliteArcher\",\"Date\":\"20180302\"},{\"ShowAsSoon\":false,\"Spell\":\"BarbLog\",\"Date\":\"20180406\"}]}}");
        b.write((byte) 4);
        b.writeString("{\"ID\":\"CLAN_CHEST\",\"Params\":{\"StartTime\":\"20170317T070000.000Z\",\"ActiveDuration\":\"P3dT0h\",\"InactiveDuration\":\"P4dT0h\",\"ChestType\":[\"ClanCrowns\"],\"Disable\":true}}");
        b.write((byte) 1);
        b.writeString("{\"ID\":\"PERMANENT_GAME_MODES\",\"Params\":{\"FixedDeckOrderOptionEnabled\":false}}");

        b.write((byte) 0);
        b.write((byte) 4);

        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);

        // clan chest crowns
        b.write((byte) 0);

        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(587420);
        b.writeRrsInt(1386060);
        b.writeRrsInt(1524791126);

        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(15);
        b.writeInt(0);
        b.writeInt(2);

        b.writeRrsInt(13);
        b.writeRrsInt(54);
        b.writeRrsInt(20);
        b.writeRrsInt(5);
        b.writeRrsInt(482);
        b.writeRrsInt(5);

        b.writeRrsInt(364880);
        b.writeRrsInt(364880);
        b.writeRrsInt(1524779999);

        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(0);
        b.writeRrsInt(15);
        b.writeRrsInt(11);
        b.writeRrsInt(8461);
        b.writeRrsInt(33);
        b.writeRrsInt(0);
        b.writeRrsInt(3);
        b.writeRrsInt(830);
        b.writeRrsInt(14);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(26);
        b.writeRrsInt(49);
        b.writeRrsInt(1);
        b.writeRrsInt(10);
        b.writeRrsInt(0);
        b.writeRrsInt(42);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(-505);

        b.writeRrsInt(10);
        b.writeRrsInt(6);
        b.writeRrsInt(24692012);
        b.writeRrsInt(140);
        b.writeRrsInt(0);
        b.writeRrsInt(3);
        b.writeRrsInt(0);

        b.writeRrsInt(71);
        b.writeRrsInt(9);
        b.writeRrsInt(24684825);
        b.writeRrsInt(27);
        b.writeRrsInt(25);
        b.writeRrsInt(5);
        b.writeRrsInt(0);

        b.writeRrsInt(33);
        b.writeRrsInt(2);
        b.writeRrsInt(24707474);
        b.writeRrsInt(7);
        b.writeRrsInt(0);
        b.writeRrsInt(8);
        b.writeRrsInt(0);

        b.writeRrsInt(84);
        b.writeRrsInt(5);
        b.writeRrsInt(24684790);
        b.writeRrsInt(44);
        b.writeRrsInt(3);
        b.writeRrsInt(6);
        b.writeRrsInt(0);

        b.writeRrsInt(50);
        b.writeRrsInt(11);
        b.writeRrsInt(24992837);
        b.writeRrsInt(1541);
        b.writeRrsInt(20);
        b.writeRrsInt(15);
        b.writeRrsInt(0);

        b.writeRrsInt(28);
        b.writeRrsInt(5);
        b.writeRrsInt(24710442);
        b.writeRrsInt(27);
        b.writeRrsInt(16);
        b.writeRrsInt(10);
        b.writeRrsInt(0);

        b.writeRrsInt(40);
        b.writeRrsInt(10);
        b.writeRrsInt(24698634);
        b.writeRrsInt(2);
        b.writeRrsInt(0);
        b.writeRrsInt(13);
        b.writeRrsInt(0);

        b.writeRrsInt(31);
        b.writeRrsInt(11);
        b.writeRrsInt(24714263);
        b.writeRrsInt(1880);
        b.writeRrsInt(5);
        b.writeRrsInt(21);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(10);
        b.writeRrsInt(66000010);
        b.writeRrsInt(66000011);
        b.writeRrsInt(66000012);
        b.writeRrsInt(66000013);
        b.writeRrsInt(66000014);
        b.writeRrsInt(66000028);
        b.writeRrsInt(66000009);
        b.writeRrsInt(66000015);
        b.writeRrsInt(66000049);
        b.writeRrsInt(66000052);

        b.writeRrsInt(1);

        b.writeRrsInt(10);
        b.writeRrsInt(66000009);
        b.writeRrsInt(66000010);
        b.writeRrsInt(66000011);
        b.writeRrsInt(66000012);
        b.writeRrsInt(66000013);
        b.writeRrsInt(66000014);
        b.writeRrsInt(66000028);
        b.writeRrsInt(66000015);
        b.writeRrsInt(66000049);
        b.writeRrsInt(66000052);

        b.writeRrsInt(1);

        b.writeRrsInt(4);
        b.writeRrsInt(66000031);
        b.writeRrsInt(66000030);
        b.writeRrsInt(66000034);
        b.writeRrsInt(66000033);

        b.writeRrsInt(1);
        b.writeRrsInt(1608786000);

        b.writeRrsInt(1);
        b.writeRrsInt(104);
        b.writeRrsInt(1);
        b.writeRrsInt(4);
        b.writeRrsInt(0);
        b.writeRrsInt(342943);
        b.writeRrsInt(1600);

        b.writeInt(1524208092);
        b.writeInt(1524467292);
        b.writeRrsInt(16394800);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeInt(0);

        b.writeInt(1);

        // this kill quests button (1 turn it on)
        b.writeRrsInt(0);
        // number of quests. 0 will generate 3 random quests
        b.writeRrsInt(0);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(130);
        b.writeRrsInt(350);
        b.writeRrsInt(9);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(482);
        b.writeRrsInt(290);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(8);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(148);
        b.writeRrsInt(7);
        b.writeRrsInt(44);
        b.writeRrsInt(0);
        b.writeRrsInt(2);
        b.writeRrsInt(5);
        b.writeRrsInt(75);
        b.writeRrsInt(0);
        b.writeRrsInt(19);
        b.writeRrsInt(0);
        b.writeRrsInt(9);
        b.writeRrsInt(8);
        b.writeRrsInt(2);
        b.writeRrsInt(0);
        b.writeRrsInt(2);
        b.writeRrsInt(3);
        b.writeRrsInt(4);
        b.writeRrsInt(4);
        b.writeRrsInt(4);
        b.writeRrsInt(0);
        b.writeRrsInt(6);
        b.writeRrsInt(0);
        b.writeRrsInt(4);
        b.writeRrsInt(4);
        b.writeRrsInt(5);
        b.writeRrsInt(2);
        b.writeRrsInt(2);
        b.writeRrsInt(0);
        b.writeRrsInt(16);
        b.writeRrsInt(22);
        b.writeRrsInt(35);
        b.writeRrsInt(0);
        b.writeRrsInt(1330);
        b.writeRrsInt(8);

        writeCardOffer(b);

        b.writeRrsInt(3);
        b.writeRrsInt(19);
        b.writeRrsInt(317);
        b.writeRrsInt(88);
        b.writeRrsInt(0);

        b.writeString("Lightning");
        b.writeRrsInt(482);
        b.writeRrsInt(0);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(19);
        b.writeRrsInt(329);
        b.writeRrsInt(88);
        b.writeRrsInt(2);
        b.writeString("Fortune");
        b.writeRrsInt(482);
        b.writeRrsInt(1);
        b.writeRrsInt(4);
        b.writeRrsInt(26);
        b.writeRrsInt(49);
        b.writeRrsInt(27);
        b.writeRrsInt(7);
        b.writeRrsInt(26);
        b.writeRrsInt(3);
        b.writeRrsInt(26);
        b.writeRrsInt(45);
        b.writeRrsInt(90000003);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(19);
        b.writeRrsInt(341);
        b.writeRrsInt(88);
        b.writeRrsInt(4);
        b.writeString("Kings");
        b.writeRrsInt(482);
        b.writeRrsInt(2);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(0);
        b.writeRrsInt(3);
        b.writeRrsInt(1);
        b.writeRrsInt(2);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(1109);
        b.writeRrsInt(0);
        b.writeRrsInt(-511);
        b.writeRrsInt(17);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(34);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(65);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(87);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(86);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(50);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(6);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(14);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(29);
        b.writeRrsInt(2398);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(0);
        b.writeRrsInt(4);
        b.writeRrsInt(355);
        b.writeRrsInt(109971920);

        b.writeRrsInt(mInfo.getClientId().high());
        b.writeRrsInt(mInfo.getClientId().low());
        b.writeRrsInt(mInfo.getClientId().high());
        b.writeRrsInt(mInfo.getClientId().low());
        b.writeRrsInt(mInfo.getClientId().high());
        b.writeRrsInt(mInfo.getClientId().low());
        b.writeString(mInfo.getPlayerName());

        b.writeRrsInt(1);
        b.writeRrsInt(13);
        b.writeRrsInt(6500);
        b.writeRrsInt(328);
        b.writeRrsInt(2431);
        b.writeRrsInt(0);
        b.writeRrsInt(6500);
        b.writeRrsInt(40);
        b.writeRrsInt(0);
        b.writeRrsInt(6500);
        b.writeRrsInt(14);
        b.writeRrsInt(42);
        b.writeRrsInt(0);
        b.writeRrsInt(6500);
        b.writeRrsInt(6500);
        b.writeRrsInt(0);
        b.writeRrsInt(14);
        b.writeRrsInt(8);
        b.writeRrsInt(23);
        b.writeRrsInt(5);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(2);
        b.writeRrsInt(1461);
        b.writeRrsInt(5);
        b.writeRrsInt(3);
        b.writeRrsInt(2);
        b.writeRrsInt(5);
        b.writeRrsInt(4);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(5);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(12);
        b.writeRrsInt(1969);
        b.writeRrsInt(5);
        b.writeRrsInt(13);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(14);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(15);
        b.writeRrsInt(1511);
        b.writeRrsInt(5);
        b.writeRrsInt(16);
        b.writeRrsInt(543);
        b.writeRrsInt(5);
        b.writeRrsInt(17);
        b.writeRrsInt(544);
        b.writeRrsInt(5);
        b.writeRrsInt(18);
        b.writeRrsInt(548);
        b.writeRrsInt(5);
        b.writeRrsInt(19);
        b.writeRrsInt(555);
        b.writeRrsInt(5);
        b.writeRrsInt(22);
        b.writeRrsInt(1997);
        b.writeRrsInt(5);
        b.writeRrsInt(25);
        b.writeRrsInt(542525556);
        b.writeRrsInt(5);
        b.writeRrsInt(26);
        b.writeRrsInt(4);
        b.writeRrsInt(5);
        b.writeRrsInt(28);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(29);
        b.writeRrsInt(72000006);
        b.writeRrsInt(5);
        b.writeRrsInt(33);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(34);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(35);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(36);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(38);
        b.writeRrsInt(0);

        b.writeRrsInt(0);

        b.writeRrsInt(30);

        b.writeRrsInt(60);
        b.writeRrsInt(0);
        b.writeRrsInt(8);
        b.writeRrsInt(60);
        b.writeRrsInt(1);
        b.writeRrsInt(62504);
        b.writeRrsInt(60);
        b.writeRrsInt(2);
        b.writeRrsInt(62504);
        b.writeRrsInt(60);
        b.writeRrsInt(3);
        b.writeRrsInt(62504);
        b.writeRrsInt(60);
        b.writeRrsInt(4);
        b.writeRrsInt(14);
        b.writeRrsInt(60);
        b.writeRrsInt(5);
        b.writeRrsInt(14);
        b.writeRrsInt(60);
        b.writeRrsInt(6);
        b.writeRrsInt(14);
        b.writeRrsInt(60);
        b.writeRrsInt(7);
        b.writeRrsInt(83);
        b.writeRrsInt(60);
        b.writeRrsInt(8);
        b.writeRrsInt(83);
        b.writeRrsInt(60);
        b.writeRrsInt(9);
        b.writeRrsInt(83);
        b.writeRrsInt(60);
        b.writeRrsInt(10);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(11);
        b.writeRrsInt(42);
        b.writeRrsInt(60);
        b.writeRrsInt(12);
        b.writeRrsInt(42);
        b.writeRrsInt(60);
        b.writeRrsInt(13);
        b.writeRrsInt(42);
        b.writeRrsInt(60);
        b.writeRrsInt(14);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(15);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(16);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(17);
        b.writeRrsInt(8);
        b.writeRrsInt(60);
        b.writeRrsInt(18);
        b.writeRrsInt(12);
        b.writeRrsInt(60);
        b.writeRrsInt(19);
        b.writeRrsInt(12);
        b.writeRrsInt(60);
        b.writeRrsInt(20);
        b.writeRrsInt(12);
        b.writeRrsInt(60);
        b.writeRrsInt(21);
        b.writeRrsInt(104);
        b.writeRrsInt(60);
        b.writeRrsInt(22);
        b.writeRrsInt(104);
        b.writeRrsInt(60);
        b.writeRrsInt(23);
        b.writeRrsInt(104);
        b.writeRrsInt(60);
        b.writeRrsInt(24);
        b.writeRrsInt(64);
        b.writeRrsInt(60);
        b.writeRrsInt(25);
        b.writeRrsInt(64);
        b.writeRrsInt(60);
        b.writeRrsInt(26);
        b.writeRrsInt(64);
        b.writeRrsInt(60);
        b.writeRrsInt(27);
        b.writeRrsInt(62504);
        b.writeRrsInt(60);
        b.writeRrsInt(28);
        b.writeRrsInt(62504);
        b.writeRrsInt(60);
        b.writeRrsInt(29);
        b.writeRrsInt(62504);

        b.writeRrsInt(25);
        b.writeRrsInt(60);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(1);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(2);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(3);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(3);
        b.writeRrsInt(4);
        b.writeRrsInt(60);
        b.writeRrsInt(5);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(6);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(7);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(8);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(9);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(10);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(14);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(17);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(18);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(19);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(20);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(21);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(22);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(23);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(24);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(25);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(26);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(27);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(28);
        b.writeRrsInt(1);
        b.writeRrsInt(60);
        b.writeRrsInt(29);
        b.writeRrsInt(1);

        b.writeRrsInt(11);
        b.writeRrsInt(5);
        b.writeRrsInt(39);
        b.writeRrsInt(320);
        b.writeRrsInt(5);
        b.writeRrsInt(40);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(6);
        b.writeRrsInt(6500);
        b.writeRrsInt(5);
        b.writeRrsInt(7);
        b.writeRrsInt(1427);
        b.writeRrsInt(5);
        b.writeRrsInt(8);
        b.writeRrsInt(83);
        b.writeRrsInt(5);
        b.writeRrsInt(9);
        b.writeRrsInt(26000027);
        b.writeRrsInt(5);
        b.writeRrsInt(10);
        b.writeRrsInt(62504);
        b.writeRrsInt(5);
        b.writeRrsInt(11);
        b.writeRrsInt(31);
        b.writeRrsInt(5);
        b.writeRrsInt(20);
        b.writeRrsInt(13);
        b.writeRrsInt(5);
        b.writeRrsInt(21);
        b.writeRrsInt(19445);
        b.writeRrsInt(5);
        b.writeRrsInt(27);
        b.writeRrsInt(14);

        b.writeRrsInt(83);
        b.writeRrsInt(26);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(2);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(4);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(5);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(6);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(7);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(8);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(9);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(10);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(11);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(12);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(13);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(14);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(15);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(16);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(17);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(18);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(19);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(20);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(21);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(22);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(23);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(24);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(25);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(26);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(27);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(28);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(29);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(30);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(31);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(32);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(33);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(34);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(35);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(36);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(37);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(38);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(39);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(40);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(41);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(42);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(43);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(44);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(45);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(46);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(48);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(49);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(50);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(52);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(54);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(55);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(56);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(57);
        b.writeRrsInt(0);
        b.writeRrsInt(26);
        b.writeRrsInt(62);
        b.writeRrsInt(0);

        b.writeRrsInt(27);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(27);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(27);
        b.writeRrsInt(2);
        b.writeRrsInt(0);
        b.writeRrsInt(27);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeRrsInt(27);
        b.writeRrsInt(4);
        b.writeRrsInt(0);
        b.writeRrsInt(27);
        b.writeRrsInt(5);
        b.writeRrsInt(0);
        b.writeRrsInt(27);
        b.writeRrsInt(6);
        b.writeRrsInt(0);
        b.writeRrsInt(27);
        b.writeRrsInt(7);
        b.writeRrsInt(0);
        b.writeRrsInt(27);
        b.writeRrsInt(8);
        b.writeRrsInt(0);
        b.writeRrsInt(27);
        b.writeRrsInt(9);
        b.writeRrsInt(0);
        b.writeRrsInt(27);
        b.writeRrsInt(10);
        b.writeRrsInt(0);

        b.writeRrsInt(28);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(2);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(4);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(5);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(6);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(7);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(8);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(9);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(10);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(11);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(12);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(13);
        b.writeRrsInt(0);
        b.writeRrsInt(28);
        b.writeRrsInt(15);
        b.writeRrsInt(65);
        b.writeRrsInt(28);
        b.writeRrsInt(16);
        b.writeRrsInt(0);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(13);
        b.writeRrsInt(0);
        b.writeRrsInt(9);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeString(ServerLogic.getInstance().getCronusClanInfo().getName());

        b.writeRrsInt(153);
        b.writeRrsInt(4);
        b.writeRrsInt(0);
        b.writeRrsInt(8461);
        b.writeRrsInt(33);
        b.writeRrsInt(0);
        b.writeRrsInt(3654);
        b.writeRrsInt(3448);
        b.writeRrsInt(1);
        b.writeRrsInt(14);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(-64);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(48365196);
        b.writeRrsInt(System.currentTimeMillis() / 1000);
        b.writeRrsInt(918244);
        b.writeRrsInt(-64);
        b.writeRrsInt(486244);
        return b.obtain();
    }

    // Write all decks and return first
    private ArrayList<CRUtils.CardInfo> writeDecks(OutBuffer b) {
        ArrayList<CRUtils.CardInfo> selectedDeck = null;
        b.writeRrsInt(5);
        for (int i = 0; i < 5; i++) {
            b.writeRrsInt(8);
            Map<CRUtils.CardInfo, String> cards = new HashMap<>();

            while (cards.size() != 8) {
                CRUtils.CardInfo c = CRUtils.randomCard();
                if (cards.get(c) == null) {
                    cards.put(c, "");
                    b.writeRrsInt(c.getScId());
                }
            }
            if (selectedDeck == null) {
                selectedDeck = new ArrayList<>(cards.keySet());
            }
        }

        return selectedDeck;
    }

    private void writeCard(OutBuffer b, CRUtils.CardInfo cardInfo) {
        b.writeRrsInt(cardInfo.getId());
        b.writeRrsInt(cardInfo.getMaxLevel() - 1);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
    }

    private void writeEvents(OutBuffer b) {
        b.write((byte) 7);

        b.writeRrsInt(1354);
        b.writeString("Triple Elixir Battle Friendly");
        b.writeRrsInt(5);
        b.writeRrsInt(1522569600);
        b.writeRrsInt(1566370800);
        b.writeRrsInt(1522569600);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("Triple Elixir Battle Friendly");
        b.writeString("{\"Title\":\"Triple Elixir Friendly Battle\",\"Subtitle\":\"Play with your Clanmates!\",\"GameMode\":\"TripleElixir_Friendly\",\"FixedArena\":\"All\",\"Background\":{\"Path\":\"/fcea04fe-11b1-43c0-af5d-39b7a60ebbd5_friendly_tripple_elixir.png\",\"Checksum\":\"6aa36c5ebd4d3318ddf08aa232805eb9\",\"File\":\"friendly_tripple_elixir.png\"}}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1385);
        b.writeString("2v2 Touchdown Friendly");
        b.writeRrsInt(5);
        b.writeRrsInt(1522569600);
        b.writeRrsInt(1566370800);
        b.writeRrsInt(1522569600);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("2v2 Touchdown Friendly");
        b.writeString("{\"GameMode\":\"TeamVsTeam_Touchdown_Draft\",\"FixedArena\":\"Arena_TouchdownTest\",\"Title\":\"2v2 Touchdown Friendly\",\"Subtitle\":\"Friendly Mode of the Month!\",\"DraftDeck\":\"Draft_Touchdown_v1\",\"Background\":{\"Path\":\"/266a0453-1d23-4fec-92d7-84b3137076f5_super_touchdown_friendly.png\",\"Checksum\":\"6fcf030ef7b776e37386a92341d144cf\",\"File\":\"super_touchdown_friendly.png\"},\"StartNotification\":\"2v2 Touchdown Friendly Battles available as the Friendly Mode of the Month!\"}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1386);
        b.writeString("Goblin");
        b.writeRrsInt(5);
        b.writeRrsInt(1522569600);
        b.writeRrsInt(1566370800);
        b.writeRrsInt(1522569600);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("Goblin");
        b.writeString("{\"GameMode\":\"BlindDeck\",\"FixedArena\":\"All\",\"Title\":\"GG\",\"Subtitle\":\"Friendly Mode of the rcamdonna!\",\"Background\":{\"Path\":\"/b81dbff6-99fc-46f5-a751-228a70577205_friendly_goblin_challenge.png\",\"Checksum\":\"checksum\",\"File\":\"file.png\"}}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1387);
        b.writeString("Blind");
        b.writeRrsInt(5);
        b.writeRrsInt(1522569600);
        b.writeRrsInt(1566370800);
        b.writeRrsInt(1522569600);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("Blind");
        b.writeString("{\"GameMode\":\"BlindDeck\",\"FixedArena\":\"All\",\"Title\":\"GG\",\"Subtitle\":\"Friendly Mode of the rcamdonna!\",\"Background\":{\"Path\":\"/3a65103b-f8d5-4398-b728-7c71a97d8038_friendly_blind_deck.png\",\"Checksum\":\"checksum\",\"File\":\"file.png\"}}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1388);
        b.writeString("Draft");
        b.writeRrsInt(5);
        b.writeRrsInt(1522569600);
        b.writeRrsInt(1566370800);
        b.writeRrsInt(1522569600);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("Draft");
        b.writeString("{\"GameMode\":\"BlindDeck\",\"FixedArena\":\"All\",\"Title\":\"GG\",\"Subtitle\":\"Friendly Mode of the rcamdonna!\",\"Background\":{\"Path\":\"/62ce7186-d295-4100-9b4e-58d86d3a854c_friendly_draft.png\",\"Checksum\":\"checksum\",\"File\":\"file.png\"}}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1389);
        b.writeString("RampUp");
        b.writeRrsInt(5);
        b.writeRrsInt(1522569600);
        b.writeRrsInt(1566370800);
        b.writeRrsInt(1522569600);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("RampUp");
        b.writeString("{\"GameMode\":\"BlindDeck\",\"FixedArena\":\"All\",\"Title\":\"GG\",\"Subtitle\":\"Friendly Mode of the rcamdonna!\",\"Background\":{\"Path\":\"/d9ec5d1a-d886-4788-8aa4-a37d7ca86d92_friendly_rampup_royale.png\",\"Checksum\":\"checksum\",\"File\":\"file.png\"}}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1390);
        b.writeString("TripleTower");
        b.writeRrsInt(5);
        b.writeRrsInt(1522569600);
        b.writeRrsInt(1566370800);
        b.writeRrsInt(1522569600);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("TripleTower");
        b.writeString("{\"GameMode\":\"Friendly\",\"FixedArena\":\"All\",\"Title\":\"Triple Tower\",\"Subtitle\":\"Fight with 3 towers on each side!\",\"Background\":{\"Path\":\"/8374b90b-3900-4ef2-b28c-f7cf31edaa40_friend_suddendeath_01.png\",\"Checksum\":\"checksum\",\"File\":\"file.png\"}}");
        b.write((byte) 0);
        b.write((byte) 0);
    }

    private void writeCardOffer(OutBuffer b) {
        if (mCardOffersEnabled) {
            // number of cards
            b.writeRrsInt(6);

            b.writeRrsInt(1);
            b.writeRrsInt(0);
            b.writeRrsInt(0);
            b.writeRrsInt(482);
            b.writeRrsInt(200);
            b.writeRrsInt(5);
            b.writeRrsInt(1);
            b.writeRrsInt(26);
            b.writeRrsInt(2);
            b.writeRrsInt(20);
            b.writeRrsInt(0);
            b.writeRrsInt(0);
            b.writeRrsInt(3000);
            b.writeRrsInt(0);
            b.writeRrsInt(0);

            b.writeRrsInt(1);
            b.writeRrsInt(0);
            b.writeRrsInt(1);
            b.writeRrsInt(482);
            b.writeRrsInt(400);
            b.writeRrsInt(5);
            b.writeRrsInt(1);
            b.writeRrsInt(26);
            b.writeRrsInt(8);
            b.writeRrsInt(40);
            b.writeRrsInt(0);
            b.writeRrsInt(0);
            b.writeRrsInt(3000);
            b.writeRrsInt(0);
            b.writeRrsInt(0);

            b.writeRrsInt(1);
            b.writeRrsInt(0);
            b.writeRrsInt(2);
            b.writeRrsInt(482);
            b.writeRrsInt(600);
            b.writeRrsInt(5);
            b.writeRrsInt(1);
            b.writeRrsInt(26);
            b.writeRrsInt(43);
            b.writeRrsInt(60);
            b.writeRrsInt(0);
            b.writeRrsInt(0);
            b.writeRrsInt(3000);
            b.writeRrsInt(0);
            b.writeRrsInt(0);

            b.writeRrsInt(1);
            b.writeRrsInt(0);
            b.writeRrsInt(3);
            b.writeRrsInt(482);
            b.writeRrsInt(1000);
            b.writeRrsInt(5);
            b.writeRrsInt(1);
            b.writeRrsInt(28);
            b.writeRrsInt(3);
            b.writeRrsInt(10);
            b.writeRrsInt(1);
            b.writeRrsInt(0);
            b.writeRrsInt(3000);
            b.writeRrsInt(0);
            b.writeRrsInt(0);

            b.writeRrsInt(1);
            b.writeRrsInt(0);
            b.writeRrsInt(4);
            b.writeRrsInt(482);
            b.writeRrsInt(1500);
            b.writeRrsInt(5);
            b.writeRrsInt(1);
            b.writeRrsInt(26);
            b.writeRrsInt(57);
            b.writeRrsInt(15);
            b.writeRrsInt(1);
            b.writeRrsInt(0);
            b.writeRrsInt(3000);
            b.writeRrsInt(0);
            b.writeRrsInt(0);

            b.writeRrsInt(1);
            b.writeRrsInt(0);
            b.writeRrsInt(5);
            b.writeRrsInt(482);
            b.writeRrsInt(2000);
            b.writeRrsInt(5);
            b.writeRrsInt(1);
            b.writeRrsInt(26);
            b.writeRrsInt(52);
            b.writeRrsInt(20);
            b.writeRrsInt(1);
            b.writeRrsInt(0);
            b.writeRrsInt(3000);
            b.writeRrsInt(0);
            b.writeRrsInt(0);
        } else {
            b.writeRrsInt(0);
        }
    }
}
