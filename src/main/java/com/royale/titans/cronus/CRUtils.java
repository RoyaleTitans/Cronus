package com.royale.titans.cronus;

import java.util.Random;

public class CRUtils {
    public static final CardInfo[] CARDS = new CardInfo[] {
            new CardInfo("Knight", 1, 26000000),
            new CardInfo("Archers", 2, 26000001),
            new CardInfo("Goblins", 3, 26000002),
            new CardInfo("Giant", 4, 26000003),
            new CardInfo("P.E.K.K.A", 5, 26000004),
            new CardInfo("Minions", 6, 26000005),
            new CardInfo("Balloon", 7, 26000006),
            new CardInfo("Witch", 8, 26000007),
            new CardInfo("Barbarians", 9, 26000008),
            new CardInfo("Golem", 10, 26000009),
            new CardInfo("Skeletons", 11, 26000010),
            new CardInfo("Valkyrie", 12, 26000011),
            new CardInfo("Skeleton Army", 13, 26000012),
            new CardInfo("Bomber", 14, 26000013),
            new CardInfo("Musketeer", 15, 26000014),
            new CardInfo("Baby Dragon", 16, 26000015),
            new CardInfo("Prince", 17, 26000016),
            new CardInfo("Wizard", 18, 26000017),
            new CardInfo("Mini P.E.K.K.A", 19, 26000018),
            new CardInfo("Spear Goblins", 20, 26000019),
            new CardInfo("Giant Skeleton", 21, 26000020),
            new CardInfo("Hog Rider", 22, 26000021),
            new CardInfo("Minion Horde", 23, 26000022),
            new CardInfo("Ice Wizard", 24, 26000023),
            new CardInfo("Royal Giant", 25, 26000024),
            new CardInfo("Guards", 26, 26000025),
            new CardInfo("Princess", 27, 26000026),
            new CardInfo("Dark Prince", 28, 26000027),
            new CardInfo("Three Musketeers", 29, 26000028),
            new CardInfo("Lava Hound", 30, 26000029),
            new CardInfo("Ice Spirit", 31, 26000030),
            new CardInfo("Fire Spirits", 32, 26000031),
            new CardInfo("Miner", 33, 26000032),
            new CardInfo("Sparky", 34, 26000033),
            new CardInfo("Bowler", 35, 26000034),
            new CardInfo("Lumberjack", 36, 26000035),
            new CardInfo("Battle Ram", 37, 26000036),
            new CardInfo("Inferno Dragon", 38, 26000037),
            new CardInfo("Ice Golem", 39, 26000038),
            new CardInfo("Mega Minion", 40, 26000039),
            new CardInfo("Dart Goblin", 41, 26000040),
            new CardInfo("Goblin Gang", 42, 26000041),
            new CardInfo("Electric Wizard", 43, 26000042),
            new CardInfo("Elite Barbarians", 44, 26000043),
            new CardInfo("Hunter", 45, 26000044),
            new CardInfo("Executioner", 46, 26000045),
            new CardInfo("Bandit", 47, 26000046),
            new CardInfo("Night Witch", 49, 26000048),
            new CardInfo("Bats", 50, 26000049),
            new CardInfo("Ghost", 51, 26000050),
            new CardInfo("Zappies", 53, 26000052),
            new CardInfo("Cannon Cart", 55, 26000054),
            new CardInfo("Mega Knight", 56, 26000055),
            new CardInfo("Skeleton Barrel", 57, 26000056),
            new CardInfo("Flying Machine", 58, 26000057),
            new CardInfo("Magic Archer", 63, 26000062),
            new CardInfo("Cannon", 64, 27000000),
            new CardInfo("Goblin Hut", 65, 27000001),
            new CardInfo("Mortar", 66, 27000002),
            new CardInfo("Inferno Tower", 67, 27000003),
            new CardInfo("Bomb Tower", 68, 27000004),
            new CardInfo("Barbarian Hut", 69, 27000005),
            new CardInfo("Tesla", 70, 27000006),
            new CardInfo("Elixir Collector", 71, 27000007),
            new CardInfo("X-Bow", 72, 27000008),
            new CardInfo("Tombstone", 73, 27000009),
            new CardInfo("Furnace", 74, 27000010),
            new CardInfo("Fireball", 78, 28000000),
            new CardInfo("Arrows", 79, 28000001),
            new CardInfo("Rage", 80, 28000002),
            new CardInfo("Rocket", 81, 28000003),
            new CardInfo("Goblin Barrel", 82, 28000004),
            new CardInfo("Freeze", 83, 28000005),
            new CardInfo("Mirror", 84, 28000006),
            new CardInfo("Lightning", 85, 28000007),
            new CardInfo("Zap", 86, 28000008),
            new CardInfo("Poison", 87, 28000009),
            new CardInfo("Graveyard", 88, 28000010),
            new CardInfo("The Log", 89, 28000011),
            new CardInfo("Tornado", 90, 28000012),
            new CardInfo("Clone", 91, 28000013),
            new CardInfo("Barb Barrel", 93, 28000015),
            new CardInfo("Heal", 94, 28000016),
    };

    public static CardInfo randomCard() {
        //return CARDS[new Random().nextInt(CARDS.length)];
        while (true) {
            CardInfo cardInfo = CARDS[new Random().nextInt(CARDS.length)];
            if (cardInfo.getScId() >= 27000000) {
                return cardInfo;
            }
        }
    }

    public static class CardInfo {
        // Knight
        private final String mName;
        // 1
        private final int mId;
        // 26000000
        private final int mScId;

        private final int mMaxLevel;

        public CardInfo(String name, int id, int scId) {
            mName = name;
            mId = id;
            mScId = scId;

            mMaxLevel = maxLevelFromId(scId);
        }

        public String getName() {
            return mName;
        }

        public int getId() {
            return mId;
        }

        public int getScId() {
            return mScId;
        }

        public int getMaxLevel() {
            return mMaxLevel;
        }

        private int maxLevelFromId(int scId) {
            switch (scId) {
                case 26000003:
                case 26000011:
                case 26000014:
                case 26000017:
                case 26000018:
                case 26000021:
                case 26000028:
                case 26000036:
                case 26000038:
                case 26000039:
                case 26000040:
                case 27000001:
                case 27000003:
                case 27000004:
                case 27000005:
                case 27000007:
                case 27000009:
                case 27000010:
                case 28000000:
                case 28000003:
                case 28000016:
                    return 11;
                case 26000004:
                case 26000006:
                case 26000007:
                case 26000009:
                case 26000012:
                case 26000015:
                case 26000016:
                case 26000020:
                case 26000025:
                case 26000027:
                case 26000034:
                case 26000045:
                case 27000008:
                case 28000002:
                case 28000004:
                case 28000005:
                case 28000006:
                case 28000007:
                case 28000009:
                case 28000012:
                case 28000013:
                    return 8;
                case 26000023:
                case 26000026:
                case 26000029:
                case 26000032:
                case 26000033:
                case 26000035:
                case 26000037:
                case 26000042:
                case 26000046:
                case 26000048:
                case 28000010:
                case 28000011:
                    return 5;
            }

            return 13;
        }
    }
}
