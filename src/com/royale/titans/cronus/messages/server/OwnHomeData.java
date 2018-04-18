package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.CRUtils;
import com.royale.titans.cronus.lib.OutBuffer;
import com.royale.titans.cronus.messages.ServerMessage;

public class OwnHomeData extends ServerMessage {
    private final long mClientId;

    public OwnHomeData(long clientId) {
        mClientId = clientId;
    }

    @Override
    public int getId() {
        return 28502;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Buffer getBuffer() {
        OutBuffer b = OutBuffer.newBuffer();

        b.writeLong(mClientId);
        b.writeRrsInt(0);
        b.writeRrsInt(1501);
        b.writeRrsInt(257080);
        b.writeRrsInt(1705500);
        b.writeRrsInt(System.currentTimeMillis() / 1000);
        b.writeRrsInt(116);

        writeDecks(b);

        b.write((byte) -1);

        b.writeRrsInt(16);
        b.writeRrsInt(5);
        b.writeRrsInt(0);
        b.writeRrsInt(51);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(27);
        b.writeRrsInt(2);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(28);
        b.writeRrsInt(5);
        b.writeRrsInt(0);
        b.writeRrsInt(11);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(82);
        b.writeRrsInt(4);
        b.writeRrsInt(0);
        b.writeRrsInt(52);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(40);
        b.writeRrsInt(10);
        b.writeRrsInt(0);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(31);
        b.writeRrsInt(11);
        b.writeRrsInt(0);
        b.writeRrsInt(1794);
        b.writeRrsInt(13);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(86);
        b.writeRrsInt(11);
        b.writeRrsInt(0);
        b.writeRrsInt(1160);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(87);
        b.writeRrsInt(5);
        b.writeRrsInt(0);
        b.writeRrsInt(37);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(0);

        b.writeRrsInt(0);
        b.write((byte) -1);

        byte[] compBytes = new byte[] { 39, 24, 47, 88, 29, 31, 42, 36 };
        for (int i=0;i<8;i++) {
            b.write(compBytes[i]);
            b.write((byte) 0);
            b.write((byte) 127);
            b.write((byte) 0);
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

        b.write((byte) 9);

        b.writeRrsInt(1109);
        b.writeString("2v2 Button");
        b.writeRrsInt(8);
        b.writeRrsInt(1503298800);
        b.writeRrsInt(1534834800);
        b.writeRrsInt(1502694000);
        b.writeString("");
        b.writeString("");
        b.writeString("2v2 Button");
        b.writeString("{\"HideTimer\":true,\"HidePopupTimer\":true,\"ExtraGameModeChance\":0,\"GameMode\":\"TeamVsTeamLadder\",\"ExtraGameMode\":\"None\"}");
        b.write((byte) 0);

        b.writeRrsInt(1354);
        b.writeString("Triple Elixir Battle Friendly");
        b.writeRrsInt(5);
        b.writeRrsInt(1522569600);
        b.writeRrsInt(1525154400);
        b.writeRrsInt(1522569600);
        b.writeString("");
        b.writeString("");
        b.writeString("Triple Elixir Battle Friendly");
        b.writeString("{\"Title\":\"Triple Elixir Friendly Battle\",\"Subtitle\":\"Play with your Clanmates!\",\"GameMode\":\"TripleElixir_Friendly\",\"FixedArena\":\"All\",\"Background\":{\"Path\":\"/fcea04fe-11b1-43c0-af5d-39b7a60ebbd5_friendly_tripple_elixir.png\",\"Checksum\":\"6aa36c5ebd4d3318ddf08aa232805eb9\",\"File\":\"friendly_tripple_elixir.png\"}}");
        b.write((byte) 0);

        b.writeRrsInt(1357);
        b.writeString("Reddit Decks Challenge Practice");
        b.writeRrsInt(2);
        b.writeRrsInt(1522994400);
        b.writeRrsInt(1523253600);
        b.writeRrsInt(1522908000);
        b.writeString("");
        b.writeString("");
        b.writeString("Reddit Decks Challenge Practice");
        b.writeString("{\"GameMode\":\"BlindDeck\",\"Casual\":true,\"Title\":\"Practice\",\"FreePass\":1,\"Rewards\":[{},{},{}," +
                "{\"Milestone\":{\"Type\":\"Gold\",\"Amount\":250}},{},{},{\"Milestone\":{\"Type\":\"Gold\",\"Amount\":500}},{},{}," +
                "{\"Milestone\":{\"IsHighlighted\":true,\"Type\":\"Gold\",\"Amount\":750}}]," +
                "\"IconExportName\":\"icon_tournament_blind_deck\",\"WinIconExportName\":\"tournament_open_wins_badge_blind\"," +
                "\"Arena\":\"All\",\"Subtitle\":\"This is how it works\",\"Subtitle_Short\":\"Play with decks created by the community\"," +
                "\"Description\":\"Play in the Practice round with decks created and chosen by Clash Royale Reddit Community! Get 9 crowns to unlock the Challenge!\"," +
                "\"UnlockedForXP\":\"Everyone\",\"Casual_CrownsInsteadOfWins\":true,\"IsChainedEvent\":true," +
                "\"IsDailyRefresh\":false,\"LocationOverride\":\"None\"," +
                "\"Background\":{\"Path\":\"/ce07d77b-5b5b-4ae4-bcbc-173e6d48900e_reddit_challenge_1.png\",\"Checksum\":\"114cb1306a4e425c7b4d1ade4265e803\"" +
                ",\"File\":\"reddit_challenge_1.png\"},\"Background_Complete\":{\"Path\":\"/90e64a7f-d7b1-4ef6-8650-d5f60cd1b26b_reddit_challenge_complete_1.png\"" +
                ",\"Checksum\":\"5efe8bf3c7be20be41ec8994a303738c\",\"File\":\"reddit_challenge_complete_1.png\"}}");
        b.write((byte) 0);

        b.writeRrsInt(1358);
        b.writeString("Reddit Decks Challenge");
        b.writeRrsInt(2);
        b.writeRrsInt(1522994400);
        b.writeRrsInt(1523253600);
        b.writeRrsInt(1522908000);
        b.writeString("");
        b.writeString("");
        b.writeString("Reddit Decks Challenge");
        b.writeString("{\"GameMode\":\"BlindDeck\",\"Casual\":false,\"Title\":\"Challenge\",\"FreePass\":1,\"Rewards\":" +
                "[{\"Gold\":130,\"Cards\":2},{\"Gold\":180,\"Cards\":3},{\"Gold\":240,\"Cards\":5},{\"Gold\":310,\"Milestone\":" +
                "{\"Type\":\"Gold\",\"Amount\":1000},\"Cards\":8},{\"Gold\":390,\"Cards\":12},{\"Gold\":480,\"Cards\":17},{\"Gold\":590,\"Milestone\"" +
                ":{\"Type\":\"RandomSpell\",\"Amount\":3,\"RandomSpell\":\"Epic\"},\"Cards\":23},{\"Gold\":720,\"Cards\":30},{\"Gold\":880,\"Cards\":38}," +
                "{\"Gold\":1100,\"Milestone\":{\"IsHighlighted\":true,\"Chest\":\"Magic_<max_arena>\",\"Type\":\"Chest\"},\"Cards\":50}],\"IconExportName\"" +
                ":\"icon_tournament_blind_deck\",\"WinIconExportName\":\"tournament_open_wins_badge_blind\",\"Arena\":\"All\",\"Subtitle\":\"This is how it works\"," +
                "\"Subtitle_Short\":\"Play with decks created by the community\"," +
                "\"Description\":\"Play with decks created and chosen by Clash Royale Reddit Community! Get 9 wins to complete the Reddit Decks Challenge!\"," +
                "\"UnlockedForXP\":\"Experienced\",\"IsChainedEvent\":true,\"IsDailyRefresh\":false,\"LocationOverride\":\"None\"," +
                "\"Background\":{\"Path\":\"/bd0b0603-b38d-4951-9576-ed384e26c222_reddit_challenge_2.png\",\"Checksum\":\"830945552646c95221750c65cef585f3\"," +
                "\"File\":\"reddit_challenge_2.png\"},\"Background_Complete\":{\"Path\":\"/e4698e1e-ba72-4528-97ed-3f8d0ef3ac8e_reddit_challenge_complete_2.png\"," +
                "\"Checksum\":\"64e3c1ad92a4903cc07a8fdf3723bb88\",\"File\":\"reddit_challenge_complete_2.png\"}," +
                "\"JoinCost\":10,\"JoinCostResource\":\"Gems\",\"MaxLosses\":3}");
        b.write((byte) 0);

        b.writeRrsInt(1359);
        b.writeString("Reddit Decks Challenge Chain");
        b.writeRrsInt(12);
        b.writeRrsInt(1522994400);
        b.writeRrsInt(1523253600);
        b.writeRrsInt(1522908000);
        b.writeString("");
        b.writeString("");
        b.writeString("Reddit Decks Challenge Chain");
        b.writeString("{\"Title\":\"Reddit Decks Challenge\",\"EventIds\":[{\"Id\":1357},{\"Id\":1358}]}");
        b.write((byte) 0);

        b.writeRrsInt(1360);
        b.writeString("Reddit Decks Challenge Quest");
        b.writeRrsInt(7);
        b.writeRrsInt(1522994400);
        b.writeRrsInt(1523253600);
        b.writeRrsInt(1522908000);
        b.writeString("");
        b.writeString("");
        b.writeString("Reddit Decks Challenge Quest");
        b.writeString("{\"Title\":\"Reddit Decks Challenge Quest\",\"Info\":\"Get 9 Crowns in Reddit Decks Challenge\"," +
                "\"Count\":9,\"Target_MinXPLevel\":8,\"Points\":20,\"ChronosQuestRewards\":[{\"Type\":\"Rare\",\"Count\":10}]," +
                "\"QuestType\":\"Win\",\"Win\":{\"Type\":\"Crowns\",\"Events\":[1357,1358]}}");
        b.write((byte) 0);

        b.writeRrsInt(1361);
        b.writeString("Reddit Decks Challenge Page");
        b.writeRrsInt(13);
        b.writeRrsInt(1522994400);
        b.writeRrsInt(1523253600);
        b.writeRrsInt(1522908000);
        b.writeString("");
        b.writeString("");
        b.writeString("Reddit Decks Challenge Page");
        b.writeString("{\"Image\":{\"Path\":\"/759c963f-1440-4a62-a553-d1cf7db80122_reddit_challenge_header.png\"," +
                "\"Checksum\":\"9dd329cf5898a7b3cea7bacf99e30753\",\"File\":\"reddit_challenge_header.png\"}," +
                "\"Title\":\"Special Events\",\"Icon\":\"icon_tournament_blind_deck\",\"TitleGlow\":true}");
        b.write((byte) 0);

        b.writeRrsInt(1362);
        b.writeString("2v2 Touchdown Challenge");
        b.writeRrsInt(2);
        b.writeRrsInt(1523599200);
        b.writeRrsInt(1523858400);
        b.writeRrsInt(1523512800);
        b.writeString("");
        b.writeString("");
        b.writeString("2v2 Touchdown Challenge");
        b.writeString("{\"GameMode\":\"TeamVsTeam_Touchdown_Draft\",\"JoinCost\":50,\"Title\":\"2v2 Touchdown Challenge\"," +
                "\"FreePass\":1,\"JoinCostResource\":\"Gems\",\"MaxLosses\":3,\"Rewards\":[{\"Gold\":350,\"Cards\":5}," +
                "{\"Gold\":475,\"Cards\":8},{\"Gold\":625,\"Cards\":13},{\"Gold\":800,\"Milestone\":{" +
                "\"Type\":\"Gold\",\"Amount\":2000},\"Cards\":21},{\"Gold\":1000,\"Cards\":32},{\"Gold\":1250,\"Milestone\":{" +
                "\"Type\":\"Gold\",\"Amount\":4000},\"Cards\":46},{\"Gold\":1550,\"Cards\":63},{\"Gold\":1900,\"Milestone\":{" +
                "\"Chest\":\"Giant_<max_arena>\",\"Type\":\"Chest\"},\"Cards\":83},{\"Gold\":2350,\"Cards\":108},{\"Gold\":3000," +
                "\"Milestone\":{\"IsHighlighted\":true,\"Type\":\"Gold\",\"Amount\":20000},\"Cards\":150}],\"IconExportName\":\"icon_tournament_touchdown\"," +
                "\"WinIconExportName\":\"tournament_open_wins_badge_bronze\",\"Arena\":\"Arena_TouchdownTest\",\"Description\":" +
                "\"Get a troop into your opponents end zone to score a Crown. Win by getting three Crowns! Collect all rewards at 9 wins. 3 losses and you are out.\"," +
                "\"EndNotification\":\"2v2 Touchdown Challenge ends in two hours!\",\"Background\":{\"Path\":" +
                "\"/d581bea7-2761-456a-b01e-a8e9f18fa507_touchdown_challenge_02_6666_alpha_premul.png\",\"Checksum\":\"95abfeb72f54176a55d411b20215843f\"," +
                "\"File\":\"touchdown_challenge_02_6666_alpha_premul.png\"},\"Background_Complete\":{\"Path\":\"" +
                "/375cede0-ce6c-407e-885b-dd0dde0993d3_touchdown_challenge_02_6666_alpha_premul.png\",\"Checksum\":\"95abfeb72f54176a55d411b20215843f\"," +
                "\"File\":\"touchdown_challenge_02_6666_alpha_premul.png\"},\"UnlockedForXP\":\"Experienced\"," +
                "\"DraftDeck\":\"Draft_Touchdown_v1\",\"Subtitle\":\"This is how it works\"," +
                "\"StartNotification\":\"2v2 Touchdown Challenge has started! Play with your Friend or a random person!\"," +
                "\"Subtitle_Short\":\"Get 9 Wins to Unlock All the Rewards!\"}");
        b.write((byte) 0);

        b.writeRrsInt(1363);
        b.writeString("2v2 Touchdown Event Icon and Header");
        b.writeRrsInt(13);
        b.writeRrsInt(1523599200);
        b.writeRrsInt(1523858400);
        b.writeRrsInt(1523599200);
        b.writeString("");
        b.writeString("");
        b.writeString("2v2 Touchdown Event Icon and Header");
        b.writeString("{\"Title\":\"Touchdown Weekend!\",\"TitleGlow\":true,\"Icon\":\"icon_tournament_touchdown\"," +
                "\"Image\":{\"Path\":\"/0469b4db-5951-4ae3-b72a-64317e82eb06_touchdown_header_iphonex.png\"," +
                "\"Checksum\":\"6b876ef81d673e56ebdb32b2345558b5\",\"File\":\"touchdown_header_iphonex.png\"}}");
        b.write((byte) 0);

        b.write((byte) 0);
        b.write((byte) 0);

        b.write((byte) 3);

        b.writeRrsInt(1357);
        b.write((byte) 1);
        b.write((byte) 2);
        b.write((byte) 0);
        b.write((byte) 1);

        b.writeRrsInt(1358);
        b.write((byte) 1);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(1362);
        b.write((byte) 1);
        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);

        b.write((byte) 5);

        b.writeRrsInt(73001357);
        b.write((byte) 3);
        b.write((byte) 1);

        b.writeRrsInt(73001357);
        b.write((byte) 6);
        b.write((byte) 1);

        b.writeRrsInt(73001357);
        b.write((byte) 9);
        b.write((byte) 1);

        b.writeRrsInt(73001362);
        b.write((byte) 3);
        b.write((byte) 1);

        b.writeRrsInt(73001362);
        b.write((byte) 5);
        b.write((byte) 1);

        b.writeRrsInt(64);
        b.writeRrsInt(0);
        b.writeRrsInt(1522569600);
        b.writeRrsInt(-64);
        b.writeRrsInt(1356);

        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(9);

        b.writeRrsInt(1109);
        b.writeRrsInt(1);
        b.writeRrsInt(1354);
        b.writeRrsInt(1);
        b.writeRrsInt(1357);
        b.writeRrsInt(1);
        b.writeRrsInt(1358);
        b.writeRrsInt(1);
        b.writeRrsInt(1359);
        b.writeRrsInt(1);
        b.writeRrsInt(1360);
        b.writeRrsInt(1);
        b.writeRrsInt(1361);
        b.writeRrsInt(1);
        b.writeRrsInt(1362);
        b.writeRrsInt(1);
        b.writeRrsInt(1363);
        b.writeRrsInt(1);

        b.writeRrsInt(9);

        b.writeRrsInt(1109);
        b.writeRrsInt(2);
        b.writeRrsInt(0);
        b.writeRrsInt(1354);
        b.writeRrsInt(2);
        b.writeRrsInt(0);
        b.writeRrsInt(1357);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeRrsInt(1358);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeRrsInt(1359);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeRrsInt(1360);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeRrsInt(1361);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeRrsInt(1362);
        b.writeRrsInt(2);
        b.writeRrsInt(0);
        b.writeRrsInt(1363);
        b.writeRrsInt(2);
        b.writeRrsInt(0);

        b.write((byte) 1);
        b.writeString("{\"ID\":\"SHOP_CYCLE_MANAGEMENT\",\"Params\":{\"LegendaryChestCycleDuration\":14}}");
        b.write((byte) 1);
        b.writeString("{\"ID\":\"CARD_RELEASE_V2\",\"Params\":{\"Cards\":[{\"ShowAsSoon\":false,\"Spell\":\"Ghost\",\"Date\":\"20180104\"},{\"ShowAsSoon\":false,\"Spell\":\"EliteArcher\",\"Date\":\"20180302\"},{\"ShowAsSoon\":false,\"Spell\":\"BarbLog\",\"Date\":\"20180406\"}");
        b.write((byte) 4);
        b.writeString("{\"ID\":\"CLAN_CHEST\",\"Params\":{\"StartTime\":\"20170317T070000.000Z\",\"ActiveDuration\":\"P3dT0h\",\"InactiveDuration\":\"P4dT0h\",\"ChestType\":[\"ClanCrowns\"]}}");
        b.write((byte) 1);
        b.writeString("{\"ID\":\"PERMANENT_GAME_MODES\",\"Params\":{\"FixedDeckOrderOptionEnabled\":true}}");
        b.write((byte) 4);
        b.write((byte) 0);

        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.write((byte) 0);
        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(1);
        b.writeRrsInt(19);
        b.writeRrsInt(230);
        b.writeRrsInt(2);
        b.writeRrsInt(4955);

        b.write((byte) 0);
        b.writeRrsInt((byte) -64);

        b.writeInt(0);
        b.writeInt(0);

        b.write((byte) 0);
        b.write((byte) 10);

        b.write((byte) 0);
        b.write((byte) 0);
        b.write((byte) 0);

        b.writeRrsInt(732520);
        b.writeRrsInt(1662180);
        b.writeRrsInt(1523864483);

        b.writeRrsInt(257080);
        b.writeRrsInt(1705500);
        b.writeRrsInt(1523840711);

        b.write((byte) 0);

        b.writeRrsInt(192420);
        b.writeRrsInt(504000);
        b.writeRrsInt(1523837478);

        b.writeRrsInt(15);
        b.writeInt(0);
        b.writeInt(2);

        b.writeRrsInt(12);
        b.writeRrsInt(54);
        b.writeRrsInt(12);
        b.writeRrsInt(1);
        b.writeRrsInt(471);
        b.writeRrsInt(1);

        b.writeRrsInt(34840);
        b.writeRrsInt(34840);
        b.writeRrsInt(1523829599);

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

        b.writeRrsInt(21);
        b.writeRrsInt(4);
        b.writeRrsInt(8381);
        b.writeRrsInt(33);
        b.writeRrsInt(1998);
        b.writeRrsInt(3);
        b.writeRrsInt(830);
        b.writeRrsInt(14);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(27);
        b.writeRrsInt(7);
        b.writeRrsInt(1);
        b.writeRrsInt(10);
        b.writeRrsInt(0);
        b.writeRrsInt(42);

        b.writeInt(63495);

        b.writeRrsInt(16);
        b.writeRrsInt(5);
        b.writeRrsInt(24672670);
        b.writeRrsInt(51);
        b.writeRrsInt(0);

        b.writeInt(27);
        b.writeRrsInt(2);
        b.writeRrsInt(24882514);
        b.writeRrsInt(1);
        b.writeRrsInt(0);

        b.writeInt(28);
        b.writeRrsInt(5);
        b.writeRrsInt(24710442);
        b.writeRrsInt(11);
        b.writeRrsInt(0);

        b.writeInt(146);
        b.writeRrsInt(1);
        b.writeRrsInt(4);
        b.writeRrsInt(24668618);
        b.writeRrsInt(52);
        b.writeRrsInt(0);

        b.writeInt(40);
        b.writeRrsInt(10);
        b.writeRrsInt(24698634);
        b.writeRrsInt(3);
        b.writeRrsInt(0);

        b.writeInt(31);
        b.writeRrsInt(11);
        b.writeRrsInt(24714263);
        b.writeRrsInt(1781);
        b.writeRrsInt(0);

        b.writeInt(150);
        b.writeRrsInt(1);
        b.writeRrsInt(11);
        b.writeRrsInt(24675770);
        b.writeRrsInt(1160);
        b.writeRrsInt(0);

        b.writeInt(151);
        b.writeRrsInt(1);
        b.writeRrsInt(5);
        b.writeRrsInt(24677953);
        b.writeRrsInt(37);
        b.writeRrsInt(3);

        b.writeInt(2);
        b.writeRrsInt(26);
        b.writeRrsInt(50);
        b.writeRrsInt(28);
        b.writeRrsInt(15);
        b.writeRrsInt(10);
        b.writeRrsInt(26);
        b.writeRrsInt(46);
        b.writeRrsInt(28);
        b.writeRrsInt(16);
        b.writeRrsInt(26);
        b.writeRrsInt(48);
        b.writeRrsInt(26);
        b.writeRrsInt(49);
        b.writeRrsInt(26);
        b.writeRrsInt(54);
        b.writeRrsInt(26);
        b.writeRrsInt(55);
        b.writeRrsInt(26);
        b.writeRrsInt(57);
        b.writeRrsInt(26);
        b.writeRrsInt(56);
        b.writeRrsInt(26);
        b.writeRrsInt(50);
        b.writeRrsInt(28);
        b.writeRrsInt(15);
        b.writeRrsInt(8);
        b.writeRrsInt(26);
        b.writeRrsInt(49);
        b.writeRrsInt(26);
        b.writeRrsInt(54);
        b.writeRrsInt(26);
        b.writeRrsInt(55);
        b.writeRrsInt(26);
        b.writeRrsInt(57);
        b.writeRrsInt(26);
        b.writeRrsInt(56);
        b.writeRrsInt(26);
        b.writeRrsInt(50);
        b.writeRrsInt(26);
        b.writeRrsInt(62);
        b.writeRrsInt(28);
        b.writeRrsInt(15);

        b.writeRrsInt(1);
        b.writeRrsInt(3);
        b.writeRrsInt(-537760);

        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(12);
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

        b.writeRrsInt(0);

        b.writeRrsInt(4);
        b.writeRrsInt(66000031);
        b.writeRrsInt(66000030);
        b.writeRrsInt(66000034);
        b.writeRrsInt(66000033);

        b.writeRrsInt(1);
        b.writeRrsInt(1608786000);

        b.writeRrsInt(1);
        b.writeRrsInt(104);
        b.writeRrsInt(0);

        b.writeRrsInt(1);
        b.writeRrsInt(54000010);

        b.writeRrsInt(2);

        b.writeRrsInt(2);
        b.writeRrsInt(38407);
        b.writeRrsInt(1600);

        b.writeInt(1523603292);
        b.writeInt(1523862492);

        b.writeRrsInt(16175762);
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
        b.writeRrsInt(4);

        b.writeRrsInt(272);
        b.writeRrsInt(0);
        b.writeString("TID_CAST_QUEST_BUILDING");
        b.writeString("");
        b.writeRrsInt(30);
        b.writeRrsInt(19);
        b.writeRrsInt(0);
        b.writeString("Cast_Quest_Building");
        b.writeString("TID_CAST_QUEST_BUILDING_INFO");
        b.writeString("sc/ui.sc");
        b.writeString("quest_item_pvp");
        b.writeRrsInt(20);
        b.writeRrsInt(1);
        b.writeRrsInt(14);
        b.writeRrsInt(0);
        b.writeRrsInt(50);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(27);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(5);

        b.writeRrsInt(274);
        b.writeRrsInt(0);
        b.writeString("TID_CAST_QUEST_BUILDING");
        b.writeString("");
        b.writeRrsInt(10);
        b.writeRrsInt(8);
        b.writeRrsInt(0);
        b.writeString("Collect_Quest_Chests");
        b.writeString("TID_COLLECT_QUEST_CHEST_INFO");
        b.writeString("sc/ui.sc");
        b.writeString("quest_item_pvp");
        b.writeRrsInt(20);
        b.writeRrsInt(1);
        b.writeRrsInt(14);
        b.writeRrsInt(2);
        b.writeRrsInt(1);
        b.writeRrsInt(19);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);

        b.writeRrsInt(420);
        b.writeRrsInt(500);
        b.writeRrsInt(7);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(471);
        b.writeRrsInt(275);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(8);
        b.writeRrsInt(5);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(140);
        b.writeRrsInt(7);
        b.writeRrsInt(44);
        b.writeRrsInt(0);
        b.writeRrsInt(2);
        b.writeRrsInt(5);
        b.writeRrsInt(70);
        b.writeRrsInt(0);
        b.writeRrsInt(17);
        b.writeRrsInt(0);
        b.writeRrsInt(9);
        b.writeRrsInt(0);
        b.writeRrsInt(14);
        b.writeRrsInt(22);
        b.writeRrsInt(35);
        b.writeRrsInt(0);
        b.writeRrsInt(1330);
        b.writeRrsInt(8);
        b.writeRrsInt(6);
        b.writeRrsInt(6);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(471);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(1);
        b.writeRrsInt(26);
        b.writeRrsInt(44);
        b.writeRrsInt(1);
        b.writeRrsInt(2);
        b.writeRrsInt(1);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(471);
        b.writeRrsInt(400);
        b.writeRrsInt(5);
        b.writeRrsInt(1);
        b.writeRrsInt(26);
        b.writeRrsInt(22);
        b.writeRrsInt(40);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(3000);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(2);
        b.writeRrsInt(471);
        b.writeRrsInt(400);
        b.writeRrsInt(5);
        b.writeRrsInt(1);
        b.writeRrsInt(26);
        b.writeRrsInt(24);
        b.writeRrsInt(40);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(3000);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(3);
        b.writeRrsInt(471);
        b.writeRrsInt(1000);
        b.writeRrsInt(5);
        b.writeRrsInt(1);
        b.writeRrsInt(26);
        b.writeRrsInt(38);
        b.writeRrsInt(10);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(3000);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(4);
        b.writeRrsInt(471);
        b.writeRrsInt(2000);
        b.writeRrsInt(5);
        b.writeRrsInt(1);
        b.writeRrsInt(27);
        b.writeRrsInt(4);
        b.writeRrsInt(20);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(3000);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(471);
        b.writeRrsInt(40000);
        b.writeRrsInt(5);
        b.writeRrsInt(1);
        b.writeRrsInt(26);
        b.writeRrsInt(50);
        b.writeRrsInt(1);
        b.writeRrsInt(3);
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
        b.writeRrsInt(471);
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
        b.writeRrsInt(471);
        b.writeRrsInt(1);
        b.writeRrsInt(4);
        b.writeRrsInt(26);
        b.writeRrsInt(19);
        b.writeRrsInt(27);
        b.writeRrsInt(7);
        b.writeRrsInt(26);
        b.writeRrsInt(27);
        b.writeRrsInt(28);
        b.writeRrsInt(6);
        b.writeRrsInt(90000001);

        b.write((byte) 0);
        b.writeRrsInt(-64);

        b.writeRrsInt(19);
        b.writeRrsInt(341);
        b.writeRrsInt(88);
        b.writeRrsInt(4);
        b.writeString("Kings");
        b.writeRrsInt(471);
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
        b.writeRrsInt(0);
        b.writeRrsInt(13);
        b.writeRrsInt(8032449);
        b.writeRrsInt(13);
        b.writeRrsInt(8032449);
        b.writeRrsInt(13);
        b.writeRrsInt(8032449);

        b.writeString("G-");

        b.writeRrsInt(1);
        b.writeRrsInt(13);
        b.writeRrsInt(4079);
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
        b.writeRrsInt(43618);
        b.writeRrsInt(5);
        b.writeRrsInt(2);
        b.writeRrsInt(1461);
        b.writeRrsInt(5);
        b.writeRrsInt(3);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(4);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(5);
        b.writeRrsInt(43618);
        b.writeRrsInt(5);
        b.writeRrsInt(12);
        b.writeRrsInt(1969);
        b.writeRrsInt(5);
        b.writeRrsInt(13);
        b.writeRrsInt(0);
        b.writeRrsInt(5);
        b.writeRrsInt(14);
        b.writeRrsInt(1);
        b.writeRrsInt(5);
        b.writeRrsInt(15);
        b.writeRrsInt(1511);
        b.writeRrsInt(5);
        b.writeRrsInt(16);
        b.writeRrsInt(531);
        b.writeRrsInt(5);
        b.writeRrsInt(16);
        b.writeRrsInt(544);
        b.writeRrsInt(5);
        b.writeRrsInt(18);
        b.writeRrsInt(539);
        b.writeRrsInt(5);
        b.writeRrsInt(19);
        b.writeRrsInt(413);
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
        b.writeRrsInt(61049);
        b.writeRrsInt(60);
        b.writeRrsInt(2);
        b.writeRrsInt(61049);
        b.writeRrsInt(60);
        b.writeRrsInt(3);
        b.writeRrsInt(61049);
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
        b.writeRrsInt(82);
        b.writeRrsInt(60);
        b.writeRrsInt(8);
        b.writeRrsInt(82);
        b.writeRrsInt(60);
        b.writeRrsInt(9);
        b.writeRrsInt(82);
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
        b.writeRrsInt(61049);
        b.writeRrsInt(60);
        b.writeRrsInt(28);
        b.writeRrsInt(61049);
        b.writeRrsInt(60);
        b.writeRrsInt(29);
        b.writeRrsInt(61049);

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

        b.writeRrsInt(9);
        b.writeRrsInt(5);
        b.writeRrsInt(6);
        b.writeRrsInt(4473);
        b.writeRrsInt(5);
        b.writeRrsInt(7);
        b.writeRrsInt(1418);
        b.writeRrsInt(5);
        b.writeRrsInt(8);
        b.writeRrsInt(82);
        b.writeRrsInt(5);
        b.writeRrsInt(9);
        b.writeRrsInt(26000027);
        b.writeRrsInt(5);
        b.writeRrsInt(10);
        b.writeRrsInt(61049);
        b.writeRrsInt(5);
        b.writeRrsInt(11);
        b.writeRrsInt(31);
        b.writeRrsInt(5);
        b.writeRrsInt(20);
        b.writeRrsInt(12);
        b.writeRrsInt(5);
        b.writeRrsInt(21);
        b.writeRrsInt(19442);
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
        b.writeRrsInt(157);
        b.writeRrsInt(157);
        b.writeRrsInt(52757);
        b.writeRrsInt(12);
        b.writeRrsInt(52540);
        b.writeRrsInt(9);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeString("Cronos");
        b.writeRrsInt(153);
        b.writeRrsInt(4);
        b.writeRrsInt(8381);
        b.writeRrsInt(33);
        b.writeRrsInt(1998);
        b.writeRrsInt(3616);
        b.writeRrsInt(3414);

        b.writeRrsInt(-62);
        b.writeRrsInt(14);
        b.writeRrsInt(0);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeInt(65000001);
        b.writeRrsInt(0);
        b.writeRrsInt(1);
        b.writeRrsInt(12);
        b.writeRrsInt(0);

        b.writeInt(61921);
        b.writeRrsInt(77054);
        b.writeRrsInt(1523827857);
        b.writeRrsInt(1852142);
        return b.obtain();
    }

    private void writeDecks(OutBuffer b) {
        b.writeRrsInt(5);
        for (int i = 0; i < 5; i++) {
            b.writeRrsInt(8);
            b.writeRrsInt(CRUtils.randomCard().getScId());
            b.writeRrsInt(CRUtils.randomCard().getScId());
            b.writeRrsInt(CRUtils.randomCard().getScId());
            b.writeRrsInt(CRUtils.randomCard().getScId());
            b.writeRrsInt(CRUtils.randomCard().getScId());
            b.writeRrsInt(CRUtils.randomCard().getScId());
            b.writeRrsInt(CRUtils.randomCard().getScId());
            b.writeRrsInt(CRUtils.randomCard().getScId());
        }
    }
}
