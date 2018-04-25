package com.royale.titans.cronus.lib;

public class CronusClanInfo {

    private final HashTag mHashTag;
    private final String mName = "Cronus";

    public CronusClanInfo(HashTag hashTag) {
        mHashTag = hashTag;
    }

    public HashTag getHashTag() {
        return mHashTag;
    }

    public String getName() {
        return mName;
    }
}
