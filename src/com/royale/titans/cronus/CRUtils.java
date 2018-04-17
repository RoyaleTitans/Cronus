package com.royale.titans.cronus;

import java.util.Random;

public class CRUtils {
    public static final CardInfo[] CARDS = new CardInfo[] {
            new CardInfo("Knight", 1, 26000000),
            new CardInfo("Archers", 2, 26000001),
    };

    public static CardInfo randomCard() {
        return CARDS[new Random().nextInt(CARDS.length)];
    }

    public static class CardInfo {
        // Knight
        private final String mName;
        // 1
        private final int mId;
        // 26000000
        private final int mScId;

        CardInfo(String name, int id, int scId) {
            mName = name;
            mId = id;
            mScId = scId;
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
    }
}
