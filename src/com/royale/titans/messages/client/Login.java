package com.royale.titans.messages.client;

import com.royale.titans.Utils;
import com.royale.titans.lib.Buffer;
import com.royale.titans.lib.RrsInt;
import com.royale.titans.messages.ClientMessage;
import com.royale.titans.messages.ServerMessage;

public class Login extends ClientMessage {
    private final long mAccountId;
    private final String mToken;
    private final RrsInt mUnk0;
    private final RrsInt mUnk1;
    private final RrsInt mUnk2;
    private final String mFingerprint;
    private final RrsInt mUnk3;
    private final int mUnk4;
    private final String mUnk5;
    private final String mUnk6;
    private final String mUnk24;
    private final String mUnk7;
    private final String mUnk8;
    private final byte mUnk9;
    private final int mUnk10;
    private final String mUnk11;
    private final String mUnk12;
    private final byte mUnk13;
    private final byte mUnk14;
    private final int mUnk15;
    private final byte mUnk16;
    private final int mUnk17;
    private final RrsInt mUnk18;
    private final int mUnk19;
    private final int mUnk20;
    private final int mUnk21;
    private final int mUnk22;
    private final byte mUnk23;

    public Login(Buffer buffer) {
        super(buffer);

        System.out.println(Utils.b2h(buffer.array()));

        mAccountId = buffer.readLong();
        mToken = buffer.readString();
        mUnk0 = buffer.readRrsInt();
        mUnk1 = buffer.readRrsInt();
        mUnk2 = buffer.readRrsInt();
        mFingerprint = buffer.readString();
        mUnk3 = buffer.readRrsInt();
        mUnk4 = buffer.readInt();
        mUnk5 = buffer.readString();
        mUnk6 = buffer.readString();
        mUnk24 = buffer.readString();
        mUnk7 = buffer.readString();
        mUnk8 = buffer.readString();
        mUnk9 = buffer.read();
        mUnk10 = buffer.readInt();
        mUnk11 = buffer.readString();
        mUnk12 = buffer.readString();
        mUnk13 = buffer.read();
        mUnk14 = buffer.read();
        mUnk15 = buffer.readInt();
        mUnk16 = buffer.read();
        mUnk17 = buffer.readInt();
        mUnk18 = buffer.readRrsInt();
        mUnk19 = buffer.readInt();
        mUnk20 = buffer.readInt();
        mUnk21 = buffer.readInt();
        mUnk22 = buffer.readInt();
        mUnk23 = buffer.read();
    }

    @Override
    public ServerMessage buildResponse() {
        return null;
    }
}
