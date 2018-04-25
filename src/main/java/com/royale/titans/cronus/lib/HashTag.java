package com.royale.titans.cronus.lib;

import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class HashTag {
    private static final String TAG_CHARS = "0289PYLQGRJCUV";
    private static final int TAG_CHARS_LEN = 14;

    private final String mTag;
    private final long mLong;
    private final int mHigh;
    private final int mLow;

    public static HashTag randomHashTag() {
        StringBuilder s = new StringBuilder();
        String[] p = TAG_CHARS.split("");
        for (int i=0;i<5;i++) {
            s.append(p[new SecureRandom().nextInt(i == 0 ? 4 : TAG_CHARS_LEN)]);
        }

        return new HashTag(s.toString());
    }

    public static long tagToLong(String tag) {
        long id = 0;
        String[] t = tag.split("");
        for (String character : t) {
            int charIndex = TAG_CHARS.indexOf(character);

            id *= TAG_CHARS_LEN;
            id += charIndex;
        }
        int high = (int) (id % 256);
        int low = (int) ((id - high) >>> 8);
        ByteBuffer b = ByteBuffer.allocate(8);
        b.putInt(high);
        b.putInt(low);
        b.rewind();
        return b.getLong();
    }

    private static String longToTag(int high, int low) {
        long id = (low << 8) + high;
        StringBuilder res = new StringBuilder();
        while (id > 0) {
            int rem = (int) Math.floor(id % TAG_CHARS_LEN);
            res.insert(0, TAG_CHARS.charAt(rem));
            id -= rem;
            id /= TAG_CHARS_LEN;
        }
        return res.toString();
    }

    public HashTag(String tag) {
        mTag = tag;
        mLong = tagToLong(mTag);
        mHigh = (int) (mLong % 256);
        mLow = (int) ((mLong - mHigh) >> 8);
    }

    public HashTag(int high, int low) {
        mHigh = high;
        mLow = low;
        mLong = ByteBuffer.allocate(8).putInt(high).putInt(low).rewind().getLong();
        mTag = longToTag(mHigh, mLow);
    }

    @Override
    public String toString() {
        return mTag;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof HashTag) {
            return ((HashTag) o).lon() == lon();
        }
        return super.equals(o);
    }

    public int low() {
        return mLow;
    }

    public int high() {
        return mHigh;
    }

    public long lon() {
        return mLong;
    }
}

