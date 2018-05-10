package com.royale.titans.cronus;

import java.util.LinkedHashMap;
import java.util.Random;

public class CRUtils {
    public static final CardInfo[] CARDS = new CardInfo[] {
            new CardInfo("Knight", 1, 26000000, 3, 1, 0),
            new CardInfo("Archers", 2, 26000001, 3, 2, 1),
            new CardInfo("Goblins", 3, 26000002, 2, 3, 2),
            new CardInfo("Giant", 4, 26000003, 5, 1, 3),
            new CardInfo("P.E.K.K.A", 5, 26000004, 7, 1, 4),
            new CardInfo("Minions", 6, 26000005, 3, 3, 5),
            new CardInfo("Balloon", 7, 26000006, 5, 1, 6),
            new CardInfo("Witch", 8, 26000007, 5, 1, 7),
            new CardInfo("Barbarians", 9, 26000008, 5, 4, 9),
            new CardInfo("Golem", 10, 26000009, 8, 1, 10),
            new CardInfo("Skeletons", 11, 26000010, 1, 3, 8),
            new CardInfo("Valkyrie", 12, 26000011, 4, 1, 12),
            new CardInfo("Skeleton Army", 13, 26000012, 3, 1, 27),
            new CardInfo("Bomber", 14, 26000013, 3, 1, 13),
            new CardInfo("Musketeer", 15, 26000014, 4, 1, 14),
            new CardInfo("Baby Dragon", 16, 26000015, 4, 1, 15),
            new CardInfo("Prince", 17, 26000016, 5, 1, 18),
            new CardInfo("Wizard", 18, 26000017, 5, 1, 17),
            new CardInfo("Mini P.E.K.K.A", 19, 26000018, 4, 1, 16),
            new CardInfo("Spear Goblins", 20, 26000019, 2, 3, 19),
            new CardInfo("Giant Skeleton", 21, 26000020, 6, 1, 20),
            new CardInfo("Hog Rider", 22, 26000021, 4, 1, 21),
            new CardInfo("Minion Horde", 23, 26000022, 5, 6, 5),
            new CardInfo("Ice Wizard", 24, 26000023, 3, 1, 23),
            new CardInfo("Royal Giant", 25, 26000024, 6, 1, 24),
            new CardInfo("Guards", 26, 26000025, 3, 3, 27),
            new CardInfo("Princess", 27, 26000026, 3, 1, 25),
            new CardInfo("Dark Prince", 28, 26000027, 4, 1, 26),
            new CardInfo("Three Musketeers", 29, 26000028, 9, 3, 14),
            new CardInfo("Lava Hound", 30, 26000029, 7, 1, 28),
            new CardInfo("Ice Spirit", 31, 26000030, 1, 1, 31),
            new CardInfo("Fire Spirits", 32, 26000031, 2, 1, 32),
            new CardInfo("Miner", 33, 26000032, 3, 1, 33),
            new CardInfo("Sparky", 34, 26000033, 6, 1, 34),
            new CardInfo("Bowler", 35, 26000034, 5, 1, 35),
            new CardInfo("Lumberjack", 36, 26000035, 4, 1, 30),
            new CardInfo("Battle Ram", 37, 26000036, 4, 1, 39),
            new CardInfo("Inferno Dragon", 38, 26000037, 4, 1, 38),
            new CardInfo("Ice Golem", 39, 26000038, 2, 1, 36),
            new CardInfo("Mega Minion", 40, 26000039, 3, 1, 37),
            new CardInfo("Dart Goblin", 41, 26000040, 3, 1, 40),
            new CardInfo("Goblin Gang", 42, 26000041, 3, 5, 2),
            new CardInfo("Electric Wizard", 43, 26000042, 4, 1, 41),
            new CardInfo("Elite Barbarians", 44, 26000043, 6, 2, 42),
            new CardInfo("Hunter", 45, 26000044, 4, 1, 48),
            new CardInfo("Executioner", 46, 26000045, 5, 1, 43),
            new CardInfo("Bandit", 47, 26000046, 3, 1, 44),
            new CardInfo("Night Witch", 49, 26000048, 4, 1, 49),
            new CardInfo("Bats", 50, 26000049, 2, 6, 50),
            new CardInfo("Ghost", 51, 26000050, 3, 1, 46),
            new CardInfo("Zappies", 53, 26000052, 4, 3, 47),
            new CardInfo("Rascals", 54, 26000053, 5, 3, 48),
            new CardInfo("Cannon Cart", 55, 26000054, 5, 1, 53),
            new CardInfo("Mega Knight", 56, 26000055, 7, 1, 55),
            new CardInfo("Skeleton Barrel", 57, 26000056, 3, 1, 57),
            new CardInfo("Flying Machine", 58, 26000057, 4, 1, 58),
            new CardInfo("Magic Archer", 63, 26000062, 4, 1, 63),
            new CardInfo("Cannon", 64, 27000000, 3, 1, 0),
            new CardInfo("Goblin Hut", 65, 27000001, 5, 1, 0),
            new CardInfo("Mortar", 66, 27000002, 4, 1, 0),
            new CardInfo("Inferno Tower", 67, 27000003, 5, 1, 0),
            new CardInfo("Bomb Tower", 68, 27000004, 5, 1, 0),
            new CardInfo("Barbarian Hut", 69, 27000005, 7, 1, 0),
            new CardInfo("Tesla", 70, 27000006, 4, 1, 0),
            new CardInfo("Elixir Collector", 71, 27000007, 6, 1, 0),
            new CardInfo("X-Bow", 72, 27000008, 6, 1, 0),
            new CardInfo("Tombstone", 73, 27000009, 3, 1, 0),
            new CardInfo("Furnace", 74, 27000010, 4, 1, 0),
            new CardInfo("Fireball", 78, 28000000, 4, 0, 0),
            new CardInfo("Arrows", 79, 28000001, 3, 0, 0),
            new CardInfo("Rage", 80, 28000002, 2, 0, 0),
            new CardInfo("Rocket", 81, 28000003, 6, 0, 0),
            new CardInfo("Goblin Barrel", 82, 28000004, 3, 3, 0),
            new CardInfo("Freeze", 83, 28000005, 4, 0, 0),
            new CardInfo("Mirror", 84, 28000006, 0, 0, 0),
            new CardInfo("Lightning", 85, 28000007, 6, 0, 0),
            new CardInfo("Zap", 86, 28000008, 2, 0, 0),
            new CardInfo("Poison", 87, 28000009, 4, 0, 0),
            new CardInfo("Graveyard", 88, 28000010, 5, 1, 0),
            new CardInfo("The Log", 89, 28000011, 2, 0, 0),
            new CardInfo("Tornado", 90, 28000012, 3, 0, 0),
            new CardInfo("Clone", 91, 28000013, 3, 0, 0),
            new CardInfo("Barb Barrel", 93, 28000015, 4, 1, 0),
            new CardInfo("Heal", 94, 28000016, 3, 0, 0),
    };

    public static LinkedHashMap<Integer, CardInfo> sCardsScIdMap = new LinkedHashMap<>();

    static {
        for (CardInfo cardInfo : CARDS) {
            sCardsScIdMap.putIfAbsent(cardInfo.getScId(), cardInfo);
        }
    }

    public static CardInfo randomCard() {
        return CARDS[new Random().nextInt(CARDS.length)];
    }

    public static int arenaToId(int arena) {
        if (arena < 7) {
            return arena + 1;
        }

        switch (arena) {
            case 7:
                return 9;
            case 8:
                return 10;
            case 9:
                return 11;
            case 10:
                return 8;
            case 11:
                return 25;
            case 12:
                return 12;
        }

        return 1;
    }

    public static class CardInfo {
        // Knight
        private final String mName;
        // 1
        private final int mId;
        // 26000000
        private final int mScId;

        private final int mMaxLevel;

        private final int mElixirCost;

        private final int mUnitCount;

        private final int mMaskId;

        public CardInfo(String name, int id, int scId, int elixirCost, int unitCount, int maskId) {
            mName = name;
            mId = id;
            mScId = scId;

            mMaxLevel = maxLevelFromId(scId);
            mElixirCost = elixirCost;
            mUnitCount = unitCount;
            mMaskId = maskId;
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

        public int getElixirCost() {
            return mElixirCost;
        }

        public int getUnitCount() {
            return mUnitCount;
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
