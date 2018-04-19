package com.royale.titans.cronus;

public class BattleLogic {

    private static BattleLogic sInstance;

    public synchronized BattleLogic getInstance() {
        if (sInstance == null) {
            sInstance = new BattleLogic();
        }

        return sInstance;
    }

    public void enqueueBattle() {}
}
