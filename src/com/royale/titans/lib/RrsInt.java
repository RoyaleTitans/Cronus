package com.royale.titans.lib;

public class RrsInt {
    private final int mValue;
    private final int mLength;

    public RrsInt(int value, int length) {
        mValue = value;
        mLength = length;
    }

    public int getValue() {
        return mValue;
    }

    public int getLength() {
        return mLength;
    }
}
