package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.Utils;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.CRUtils;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OwnHomeData extends ServerMessage {
    private final ServerLogic.ClientInfo mInfo;

    public OwnHomeData(ServerLogic.ClientInfo info) {
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
        b.write((byte) 9);

        b.writeRrsInt(1109);
        b.writeString("2v2 Button");
        b.writeRrsInt(8);
        b.writeRrsInt(1503298800);
        b.writeRrsInt(1566370800);
        b.writeRrsInt(1502694000);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("2v2 Button");
        b.writeString("{\"HideTimer\":true,\"HidePopupTimer\":true,\"ExtraGameModeChance\":0,\"GameMode\":\"TeamVsTeamLadder\",\"ExtraGameMode\":\"None\"}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1354);
        b.writeString("Triple Elixir Battle Friendly");
        b.writeRrsInt(5);
        b.writeRrsInt(1522569600);
        b.writeRrsInt(1525154400);
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

        b.writeRrsInt(1364);
        b.writeString("Teaser Pop Up");
        b.writeRrsInt(15);
        b.writeRrsInt(1524139200);
        b.writeRrsInt(1524301200);
        b.writeRrsInt(1524139200);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("Teaser Pop Up");
        b.writeString("{\"Title\":\"Clans, prepare\",\"MainText\":\"Stay tuned for more information.\",\"ButtonText\":\"Nice!\",\"ShowOnEachStartup\":false,\"Background\":{\"Path\":\"/60a9e2d4-26fa-4921-9860-011207574cf2_clan_wars_popup_01.png\",\"Checksum\":\"655756c9ed8e2b55cc062373d6107c70\",\"File\":\"clan_wars_popup_01.png\"},\"Subtitle\":\"New update is arriving soon!\"}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1365);
        b.writeString("Gold Rush");
        b.writeRrsInt(8);
        b.writeRrsInt(1524204000);
        b.writeRrsInt(1524463200);
        b.writeRrsInt(1524204000);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("Gold Rush");
        b.writeString("{\"GameMode\":\"Ladder\",\"ExtraGameMode\":\"Ladder_GoldRush\",\"ExtraGameModeChance\":100,\"ExtraGameModeRewards\":[{\"MinArena\":\"TrainingCamp\",\"RewardTower1\":40,\"MaxReward\":1200,\"RewardTower3\":120,\"RewardTower2\":80},{\"MinArena\":\"Arena1\",\"RewardTower1\":40,\"MaxReward\":1200,\"RewardTower3\":120,\"RewardTower2\":80},{\"MinArena\":\"Arena2\",\"RewardTower1\":50,\"MaxReward\":1500,\"RewardTower3\":150,\"RewardTower2\":100},{\"MinArena\":\"Arena3\",\"RewardTower1\":60,\"MaxReward\":1800,\"RewardTower3\":180,\"RewardTower2\":120},{\"MinArena\":\"Arena4\",\"RewardTower1\":70,\"MaxReward\":2100,\"RewardTower3\":210,\"RewardTower2\":140},{\"MinArena\":\"Arena5\",\"RewardTower1\":80,\"MaxReward\":2400,\"RewardTower3\":240,\"RewardTower2\":160},{\"MinArena\":\"Arena6\",\"RewardTower1\":90,\"MaxReward\":2700,\"RewardTower3\":270,\"RewardTower2\":180},{\"MinArena\":\"Arena7\",\"RewardTower1\":100,\"MaxReward\":3000,\"RewardTower3\":300,\"RewardTower2\":200},{\"MinArena\":\"Arena8\",\"RewardTower1\":120,\"MaxReward\":3600,\"RewardTower3\":360,\"RewardTower2\":240},{\"MinArena\":\"Arena9\",\"RewardTower1\":140,\"MaxReward\":4200,\"RewardTower3\":420,\"RewardTower2\":280},{\"MinArena\":\"Arena_L\",\"RewardTower1\":160,\"MaxReward\":4800,\"RewardTower3\":480,\"RewardTower2\":320},{\"MinArena\":\"Arena_Electric\",\"RewardTower1\":180,\"MaxReward\":5400,\"RewardTower3\":540,\"RewardTower2\":360},{\"MinArena\":\"Arena_T\",\"RewardTower1\":200,\"MaxReward\":6000,\"RewardTower3\":600,\"RewardTower2\":400}],\"StartNotification\":\"Play Gold Rush Event! Available this weekend!\",\"EndNotification\":\"2 hours left to play Gold Rush!\"}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1366);
        b.writeString("Weekend Boosts");
        b.writeRrsInt(10);
        b.writeRrsInt(1524204000);
        b.writeRrsInt(1524463200);
        b.writeRrsInt(1524204000);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("Weekend Boosts");
        b.writeString("{\"Title\":\"Special Weekend Boosts\",\"Boosts\":[{\"Type\":\"VictoryGold\",\"durationInHours\":72,\"Cost\":100},{\"Type\":\"CrownChestCards\",\"durationInHours\":72,\"Cost\":100},{\"Type\":\"ChestSpeedUp\",\"durationInHours\":72,\"Cost\":100}]}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1367);
        b.writeString("Reveal Pop Up");
        b.writeRrsInt(15);
        b.writeRrsInt(1524301200);
        b.writeRrsInt(1524556800);
        b.writeRrsInt(1524301200);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("Reveal Pop Up");
        b.writeString("{\"Title\":\"Clan Wars Is Coming!\",\"MainText\":\"a\",\"ButtonText\":\"Watch!\",\"ShowOnEachStartup\":false,\"Background\":{\"Path\":\"/e8bd9cf3-21b0-489b-9ecd-cd6d4e728197_clan_wars_popup_02.png\",\"Checksum\":\"74b9fd26cb184e6821db8a8dc869bb02\",\"File\":\"clan_wars_popup_02.png\"},\"Subtitle\":\"Next Update Is Almost Here!\",\"ButtonLink\":\"clashroyale://newsroyale\"}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1368);
        b.writeString("Gem Rush");
        b.writeRrsInt(8);
        b.writeRrsInt(1524463200);
        b.writeRrsInt(1524549600);
        b.writeRrsInt(1524463200);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("Gem Rush");
        b.writeString("{\"GameMode\":\"Ladder\",\"ExtraGameMode\":\"Ladder_GemRush\",\"ExtraGameModeChance\":100,\"StartNotification\":\"Gem Rush just started! Play and earn 250 Gems!\",\"EndNotification\":\"2 hours left to play Gem Rush!\",\"ExtraGameModeRewards\":[{\"MinArena\":\"TrainingCamp\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10},{\"MinArena\":\"Arena1\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10},{\"MinArena\":\"Arena2\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10},{\"MinArena\":\"Arena3\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10},{\"MinArena\":\"Arena4\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10},{\"MinArena\":\"Arena5\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10},{\"MinArena\":\"Arena6\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10},{\"MinArena\":\"Arena7\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10},{\"MinArena\":\"Arena8\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10},{\"MinArena\":\"Arena9\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10},{\"MinArena\":\"Arena_L\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10},{\"MinArena\":\"Arena_Electric\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10},{\"MinArena\":\"Arena_T\",\"RewardTower1\":5,\"MaxReward\":250,\"RewardTower3\":15,\"RewardTower2\":10}]}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1369);
        b.writeString("Clan War Quest");
        b.writeRrsInt(7);
        b.writeRrsInt(1524571200);
        b.writeRrsInt(1532520000);
        b.writeRrsInt(1524301200);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("Clan War Quest");
        b.writeString("{\"QuestType\":\"Win\",\"Title\":\"Clan Wars Quest\",\"Info\":\"Play 20 battles in Clan Wars\",\"Count\":20,\"Target_MinXPLevel\":7,\"Points\":40,\"ChronosQuestRewards\":[{\"Type\":\"Gold\",\"Count\":20000}],\"Win\":{\"Type\":\"Games\",\"GameType\":[{\"GameType\":\"ClanWar\"}]},\"HideEndTime\":true}");
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1370);
        b.writeString("CW Release Pop Up");
        b.writeRrsInt(15);
        b.writeRrsInt(1524571200);
        b.writeRrsInt(1525262400);
        b.writeRrsInt(1524301200);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);
        b.writeString("CW Release Pop Up");
        b.writeString("{\"Title\":\"Clan Wars Is Here!\",\"MainText\":\"Clans! Get ready to battle against other Clans for glory and rewards! Wait for your Clan Leaders to start a CLAN WAR!\",\"ButtonText\":\"Nice!\",\"ShowOnEachStartup\":false,\"Background\":{\"Path\":\"/38607f72-eecd-47d0-93a1-6940c98e308f_crl_teams_popup_03.png\",\"Checksum\":\"676b18e1600e958f89634a35529f5f1e\",\"File\":\"crl_teams_popup_03.png\"},\"Subtitle\":\"Play Now with Your Clan!\",\"ButtonLink\":\"clashroyale://clan\"}");
        b.write((byte) 0);
        b.write((byte) 0);

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
        b.writeString("{\"ID\":\"PERMANENT_GAME_MODES\",\"Params\":{\"FixedDeckOrderOptionEnabled\":true}}");

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

        b.write((byte) 7);

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

        b.writeRrsInt(12);
        b.writeRrsInt(54);
        b.writeRrsInt(12);
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

        b.writeInt(11);

        b.writeRrsInt(32);
        b.writeRrsInt(4298);
        b.writeRrsInt(4158);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(33);
        b.writeRrsInt(4183);
        b.writeRrsInt(3918);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(34);
        b.writeRrsInt(4111);
        b.writeRrsInt(4039);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(35);
        b.writeRrsInt(4144);
        b.writeRrsInt(4073);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(36);
        b.writeRrsInt(4304);
        b.writeRrsInt(4304);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(37);
        b.writeRrsInt(4408);
        b.writeRrsInt(4075);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(38);
        b.writeRrsInt(4300);
        b.writeRrsInt(4079);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(39);
        b.writeRrsInt(4270);
        b.writeRrsInt(4208);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(40);
        b.writeRrsInt(4473);
        b.writeRrsInt(4473);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(41);
        b.writeRrsInt(4333);
        b.writeRrsInt(4179);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(42);
        b.writeRrsInt(4416);
        b.writeRrsInt(4358);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeInt(1);
        b.writeRrsInt(1);
        b.writeRrsInt(3);
        b.writeRrsInt(2);

        b.writeRrsInt(261);
        b.writeRrsInt(0);
        b.writeString("TID_LADDER_QUEST_2V2_WIN");
        b.writeString("");
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeString("Play_Quest_Win_2v2Ladder_PvP");
        b.writeString("TID_LADDER_QUEST_WIN_2V2_INFO");
        b.writeString("sc/ui.sc");
        b.writeString("quest_item_pvp");
        b.writeRrsInt(20);
        b.writeRrsInt(1);
        b.writeRrsInt(14);
        b.writeRrsInt(1);
        b.writeRrsInt(10);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(2);
        b.writeRrsInt(0);
        b.writeRrsInt(2);

        b.writeRrsInt(288);
        b.writeRrsInt(0);
        b.writeString("TID_LADDER_QUEST_PLAY");
        b.writeString("");
        b.writeRrsInt(0);
        b.writeRrsInt(10);
        b.writeRrsInt(5);
        b.writeRrsInt(0);
        b.writeString("Play_Quest_Play_Ladder_PvP");
        b.writeString("TID_LADDER_QUEST_PLAY_INFO");
        b.writeString("sc/ui.sc");
        b.writeString("quest_item_pvp");
        b.writeRrsInt(20);
        b.writeRrsInt(1);
        b.writeRrsInt(14);
        b.writeRrsInt(0);
        b.writeRrsInt(50);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(4);

        b.writeRrsInt(289);
        b.writeRrsInt(0);
        b.writeString("TID_CAST_QUEST_SPELL");
        b.writeString("");
        b.writeRrsInt(0);
        b.writeRrsInt(50);
        b.writeRrsInt(4);
        b.writeRrsInt(0);
        b.writeString("Cast_Quest_Spell");
        b.writeString("TID_CAST_QUEST_SPELL_INFO");
        b.writeString("sc/ui.sc");
        b.writeString("quest_item_pvp");
        b.writeRrsInt(20);
        b.writeRrsInt(1);
        b.writeRrsInt(14);
        b.writeRrsInt(1);
        b.writeRrsInt(10);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(28);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(2);
        b.writeRrsInt(1369);
        b.writeRrsInt(1);
        b.writeString("Clan Wars Quest");
        b.writeString("icon_quest_type_specialevent");
        b.writeRrsInt(1);
        b.writeRrsInt(20);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeString("");
        b.writeString("Play 20 battles in Clan Wars");
        b.writeString("sc/ui.sc");
        b.writeString("quest_item_special_pvp");

        b.writeRrsInt(40);
        b.writeRrsInt(1);
        b.writeRrsInt(5);
        b.writeRrsInt(1);
        b.writeRrsInt(20000);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(64);
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
        b.writeRrsInt(4237);
        b.writeRrsInt(328);
        b.writeRrsInt(2431);
        b.writeRrsInt(0);
        b.writeRrsInt(4339);
        b.writeRrsInt(40);
        b.writeRrsInt(0);
        b.writeRrsInt(4443);
        b.writeRrsInt(14);
        b.writeRrsInt(42);
        b.writeRrsInt(0);
        b.writeRrsInt(4358);
        b.writeRrsInt(4416);
        b.writeRrsInt(0);
        b.writeRrsInt(14);
        b.writeRrsInt(8);
        b.writeRrsInt(23);
        b.writeRrsInt(5);
        b.writeRrsInt(1);
        b.writeRrsInt(20743);
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
        b.writeRrsInt(20743);
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
        b.writeRrsInt(4473);
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
        b.writeRrsInt(12);
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
        b.writeRrsInt(273);
        b.writeRrsInt(273);
        b.writeRrsInt(55012);
        b.writeRrsInt(12);
        b.writeRrsInt(52540);
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
        b.writeRrsInt(1524761755);
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
}
