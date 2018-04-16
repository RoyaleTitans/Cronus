package com.royale.titans.cronus.messages.server;

import com.royale.titans.cronus.Utils;
import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ServerMessage;
import com.royale.titans.cronus.messages.client.Login;
import jdk.jfr.Unsigned;

import java.util.Arrays;

public class OwnHomeData extends ServerMessage {
    private final Login mLogin;

    public OwnHomeData(Login login) {
        mLogin = login;
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
        Buffer b = Buffer.allocate(9909);

        b.writeLong(mLogin.getAccountId());
        b.writeRrsInt(0);
        b.writeRrsInt(1501);
        b.writeRrsInt(257080);
        b.writeRrsInt(1705500);
        b.writeInt(1523840711);
        b.writeRrsInt(116);
        b.writeRrsInt(5);

        for (int i = 0; i < 5; i++) {
            b.writeRrsInt(8);
            b.writeRrsInt(26000009);
            b.writeRrsInt(27000007);
            b.writeRrsInt(28000012);
            b.writeRrsInt(28000006);
            b.writeRrsInt(26000026);
            b.writeRrsInt(26000027);
            b.writeRrsInt(26000039);
            b.writeRrsInt(26000032);
        }

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
        b.writeString("{\"GameMode\":\"BlindDeck\",\"Casual\":true,\"Title\":\"Practice\",\"FreePass\":1,\"Rewards\":[{},{},{},{\"Milestone\":{\"Type\":\"Gold\",\"Amount\":250}},{},{},{\"Milestone\":{\"Type\":\"Gold\",\"Amount\":500}},{},{},{\"Milestone\":{\"IsHighlighted\":true,\"Type\":\"Gold\",\"Amount\":750}}],\"IconExportName\":\"icon_tournament_blind_deck\",\"WinIconExportName\":\"tournament_open_wins_badge_blind\",\"Arena\":\"All\",\"Subtitle\":\"This is how it works\",\"Subtitle_Short\":\"Play with decks created by the community\",\"Description\":\"Play in the Practice round with decks created and chosen by Clash Royale Reddit Community! Get 9 crowns to unlock the Challenge!\",\"UnlockedForXP\":\"Everyone\",\"Casual_CrownsInsteadOfWins\":true,\"IsChainedEvent\":true,\"IsDailyRefresh\":false,\"LocationOverride\":\"None\",\"Background\":{\"Path\":\"/ce07d77b-5b5b-4ae4-bcbc-173e6d48900e_reddit_challenge_1.png\",\"Checksum\":\"114cb1306a4e425c7b4d1ade4265e803\",\"File\":\"reddit_challenge_1.png\"},\"Background_Complete\":{\"Path\":\"/90e64a7f-d7b1-4ef6-8650-d5f60cd1b26b_reddit_challenge_complete_1.png\",\"Checksum\":\"5efe8bf3c7be20be41ec8994a303738c\",\"File\":\"reddit_challenge_complete_1.png\"}}");
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
        b.writeString("{\"GameMode\":\"BlindDeck\",\"Casual\":false,\"Title\":\"Challenge\",\"FreePass\":1,\"Rewards\":[{\"Gold\":130,\"Cards\":2},{\"Gold\":180,\"Cards\":3},{\"Gold\":240,\"Cards\":5},{\"Gold\":310,\"Milestone\":{\"Type\":\"Gold\",\"Amount\":1000},\"Cards\":8},{\"Gold\":390,\"Cards\":12},{\"Gold\":480,\"Cards\":17},{\"Gold\":590,\"Milestone\":{\"Type\":\"RandomSpell\",\"Amount\":3,\"RandomSpell\":\"Epic\"},\"Cards\":23},{\"Gold\":720,\"Cards\":30},{\"Gold\":880,\"Cards\":38},{\"Gold\":1100,\"Milestone\":{\"IsHighlighted\":true,\"Chest\":\"Magic_<max_arena>\",\"Type\":\"Chest\"},\"Cards\":50}],\"IconExportName\":\"icon_tournament_blind_deck\",\"WinIconExportName\":\"tournament_open_wins_badge_blind\",\"Arena\":\"All\",\"Subtitle\":\"This is how it works\",\"Subtitle_Short\":\"Play with decks created by the community\",\"Description\":\"Play with decks created and chosen by Clash Royale Reddit Community! Get 9 wins to complete the Reddit Decks Challenge!\",\"UnlockedForXP\":\"Experienced\",\"IsChainedEvent\":true,\"IsDailyRefresh\":false,\"LocationOverride\":\"None\",\"Background\":{\"Path\":\"/bd0b0603-b38d-4951-9576-ed384e26c222_reddit_challenge_2.png\",\"Checksum\":\"830945552646c95221750c65cef585f3\",\"File\":\"reddit_challenge_2.png\"},\"Background_Complete\":{\"Path\":\"/e4698e1e-ba72-4528-97ed-3f8d0ef3ac8e_reddit_challenge_complete_2.png\",\"Checksum\":\"64e3c1ad92a4903cc07a8fdf3723bb88\",\"File\":\"reddit_challenge_complete_2.png\"},\"JoinCost\":10,\"JoinCostResource\":\"Gems\",\"MaxLosses\":3}");
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
        b.writeString("{\"Title\":\"Reddit Decks Challenge Quest\",\"Info\":\"Get 9 Crowns in Reddit Decks Challenge\",\"Count\":9,\"Target_MinXPLevel\":8,\"Points\":20,\"ChronosQuestRewards\":[{\"Type\":\"Rare\",\"Count\":10}],\"QuestType\":\"Win\",\"Win\":{\"Type\":\"Crowns\",\"Events\":[1357,1358]}}");
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
        b.writeString("{\"Image\":{\"Path\":\"/759c963f-1440-4a62-a553-d1cf7db80122_reddit_challenge_header.png\",\"Checksum\":\"9dd329cf5898a7b3cea7bacf99e30753\",\"File\":\"reddit_challenge_header.png\"},\"Title\":\"Special Events\",\"Icon\":\"icon_tournament_blind_deck\",\"TitleGlow\":true}");
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
        b.writeString("{\"GameMode\":\"TeamVsTeam_Touchdown_Draft\",\"JoinCost\":50,\"Title\":\"2v2 Touchdown Challenge\",\"FreePass\":1,\"JoinCostResource\":\"Gems\",\"MaxLosses\":3,\"Rewards\":[{\"Gold\":350,\"Cards\":5},{\"Gold\":475,\"Cards\":8},{\"Gold\":625,\"Cards\":13},{\"Gold\":800,\"Milestone\":{\"Type\":\"Gold\",\"Amount\":2000},\"Cards\":21},{\"Gold\":1000,\"Cards\":32},{\"Gold\":1250,\"Milestone\":{\"Type\":\"Gold\",\"Amount\":4000},\"Cards\":46},{\"Gold\":1550,\"Cards\":63},{\"Gold\":1900,\"Milestone\":{\"Chest\":\"Giant_<max_arena>\",\"Type\":\"Chest\"},\"Cards\":83},{\"Gold\":2350,\"Cards\":108},{\"Gold\":3000,\"Milestone\":{\"IsHighlighted\":true,\"Type\":\"Gold\",\"Amount\":20000},\"Cards\":150}],\"IconExportName\":\"icon_tournament_touchdown\",\"WinIconExportName\":\"tournament_open_wins_badge_bronze\",\"Arena\":\"Arena_TouchdownTest\",\"Description\":\"Get a troop into your opponents end zone to score a Crown. Win by getting three Crowns! Collect all rewards at 9 wins. 3 losses and you are out.\",\"EndNotification\":\"2v2 Touchdown Challenge ends in two hours!\",\"Background\":{\"Path\":\"/d581bea7-2761-456a-b01e-a8e9f18fa507_touchdown_challenge_02_6666_alpha_premul.png\",\"Checksum\":\"95abfeb72f54176a55d411b20215843f\",\"File\":\"touchdown_challenge_02_6666_alpha_premul.png\"},\"Background_Complete\":{\"Path\":\"/375cede0-ce6c-407e-885b-dd0dde0993d3_touchdown_challenge_02_6666_alpha_premul.png\",\"Checksum\":\"95abfeb72f54176a55d411b20215843f\",\"File\":\"touchdown_challenge_02_6666_alpha_premul.png\"},\"UnlockedForXP\":\"Experienced\",\"DraftDeck\":\"Draft_Touchdown_v1\",\"Subtitle\":\"This is how it works\",\"StartNotification\":\"2v2 Touchdown Challenge has started! Play with your Friend or a random person!\",\"Subtitle_Short\":\"Get 9 Wins to Unlock All the Rewards!\"}");
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
        b.writeString("{\"Title\":\"Touchdown Weekend!\",\"TitleGlow\":true,\"Icon\":\"icon_tournament_touchdown\",\"Image\":{\"Path\":\"/0469b4db-5951-4ae3-b72a-64317e82eb06_touchdown_header_iphonex.png\",\"Checksum\":\"6b876ef81d673e56ebdb32b2345558b5\",\"File\":\"touchdown_header_iphonex.png\"}}");
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

        b.write(Utils.h2b("00007f00007f0113a603029b4d007f000000000000000000000a000000a8b559a4f3ca01a3cea2ad0bb8b01f9c98d00187db9fad0b00a4be1780c33da6a89fad0b0f00000000000000020c360c0197070198a00498a0049fad9ead0b0000007f00007f00007f1504bd8201218e1f03be0c0e00011b07010a002a0000f80710059ee6c31733000000001b0292b5dd1701000000001c05aab4c8170b000000009201048aa7c3173400000000280a8afcc61703000000001f0b97f0c817b51b0000000096010bba96c41788120000000097010581b9c4172503000000021a321c0f0a1a2e1c101a301a311a361a371a391a381a321c0f081a311a361a371a391a381a321a3e1c0f0103dfd24100010c000a8ad2f83e8bd2f83e8cd2f83e8dd2f83e8ed2f83e9cd2f83e89d2f83e8fd2f83eb1d2f83eb4d2f83e0a89d2f83e8ad2f83e8bd2f83e8cd2f83e8dd2f83e8ed2f83e9cd2f83e8fd2f83eb1d2f83eb4d2f83e00049fd2f83e9ed2f83ea2d2f83ea1d2f83e019081a1fe0b01a80100018ae6bf33020287d80480195ad0575c5ad44bdc92cab60f000000000b208a43be4000002197418e3d0000228f40873f000023b040a93f00002490439043000025b844ab3f0000268c43af3f000027ae42b041000028b9459b45000029ad43934100002a80458644000000000001010302850400000000185449445f4c41444445525f51554553545f3256325f57494e000000000501000000001c506c61795f51756573745f57696e5f3276324c61646465725f5076500000001d5449445f4c41444445525f51554553545f57494e5f3256325f494e464f0000000873632f75692e73630000000e71756573745f6974656d5f70767014010e010a010001020004900400000000175449445f434153545f51554553545f4255494c44494e47000000001e130000000013436173745f51756573745f4275696c64696e670000001c5449445f434153545f51554553545f4255494c44494e475f494e464f0000000873632f75692e73630000000e71756573745f6974656d5f70767014010e003200001b000001010005920400000000175449445f434f4c4c4543545f51554553545f4348455354000000000a080000000014436f6c6c6563745f51756573745f4368657374730000001c5449445f434f4c4c4543545f51554553545f43484553545f494e464f0000000873632f75692e73630000000e71756573745f6974656d5f70767014010e02011300000000a406b4070700009707930401000805000000008c02072c000205860100110009000e162300b214080606000097070005011a2c0102010100019707900605011a16280000b82e00000100029707900605011a18280000b82e00000100039707a80f05011a260a0100b82e00000100049707901f05011b04140100b82e0000010005970780f10405011a32010300b82e00000313bd04980100000000094c696768746e696e67970700007f007f13890598010200000007466f7274756e65970701041a131b071a1b1c0681aaea55007f139505980104000000054b696e6773970702007f007f000301020000000000007f951100000d81c3d4070d81c3d4070d81c3d40700000002472d010daf3f8805bf2500af4428009b450e2a0086448045000e08170501a2a9050502b5160503000504000505a2a905050cb11e050d00050e01050fa717051093080511a00805129b0805139d0605168d1f0519b4a1b28504051a04051c00051d8688d544052100052200052300052400052600001e3c00083c01b9b9073c02b9b9073c03b9b9073c040e3c050e3c060e3c0792013c0892013c0992013c0a013c0b2a3c0c2a3c0d2a3c0e013c0f013c10013c11083c120c3c130c3c140c3c15a8013c16a8013c17a8013c1880013c1980013c1a80013c1bb9b9073c1cb9b9073c1db9b907193c00013c01013c02013c03013c04013c05013c06013c07013c08013c09013c0a013c0e013c11013c12013c13013c14013c15013c16013c17013c18013c19013c1a013c1b013c1c013c1d01090506b94505078a160508920105099beae518050ab9b907050b1f05140c0515b2af02051b0e93011a00001a01001a02001a03001a04001a05001a06001a07001a08001a09001a0a001a0b001a0c001a0d001a0e001a0f001a10001a11001a12001a13001a14001a15001a16001a17001a18001a19001a1a001a1b001a1c001a1d001a1e001a1f001a20001a21001a22001a23001a24001a25001a26001a27001a28001a29001a2a001a2b001a2c001a2d001a2e001a30001a31001a32001a34001a36001a37001a38001a39001a3e001b00001b01001b02001b03001b04001b05001b06001b07001b08001b09001b0a001c00001c01001c02001c03001c04001c05001c06001c07001c08001c09001c0a001c0b001c0c001c0d001c0f81011c100000009d029d0295b8060cbcb406091da4e0240000000e4261666669414d616e756272696f990204bd8201218e1fa03896357d0e00000103dfd24100010c000000f1e1beb30991929ead0bae8be201"));
        System.out.println(b.position());
        return b;
    }
}
