package com.royale.titans.messages.client;

import com.royale.titans.Utils;
import com.royale.titans.lib.Buffer;
import com.royale.titans.lib.RrsInt;
import com.royale.titans.messages.ClientMessage;
import com.royale.titans.messages.ServerMessage;
import com.royale.titans.messages.server.LoginOk;

public class Login extends ClientMessage {
    private final long mAccountId;
    private final String mToken;
    private final RrsInt mClientMajor;
    private final RrsInt mClientMinor;
    private final RrsInt mClientBuild;
    private final String mFingerprint;
    private final String mUdid;
    private final String mOpenUdid;
    private final String mMacAddress;
    private final String mDevice;
    private final String mAdvertisingGuid;
    private final String mOsVersion;
    private final byte mIsAndroid;
    private final String mUnk2;
    private final String mAndroidId;
    private final String mLanguage;
    private final byte mUnk3;
    private final byte mUnk4;
    private final String mFacebookId;
    private final byte mAdveritisingEnabled;
    private final String mAppleIFV;
    private final RrsInt mAppStore;
    private final String mKunlunSSO;
    private final String mKunlunUID;
    private final String mUnk5;
    private final String mUnk6;
    private final byte mUnk7;

    public Login(Buffer buffer) {
        super(buffer);

        System.out.println(Utils.b2h(buffer.array()));

        mAccountId = buffer.readLong();
        mToken = buffer.readString();
        mClientMajor = buffer.readRrsInt();
        mClientMinor = buffer.readRrsInt();
        mClientBuild = buffer.readRrsInt();
        mFingerprint = buffer.readString();
        mUdid = buffer.readString();
        mOpenUdid = buffer.readString();
        mMacAddress = buffer.readString();
        mDevice = buffer.readString();
        mAdvertisingGuid = buffer.readString();
        mOsVersion = buffer.readString();
        mIsAndroid = buffer.read();
        mUnk2 = buffer.readString();
        mAndroidId = buffer.readString();
        mLanguage = buffer.readString();
        mUnk3 = buffer.read();
        mUnk4 = buffer.read();
        mFacebookId = buffer.readString();
        mAdveritisingEnabled = buffer.read();
        mAppleIFV = buffer.readString();
        mAppStore = buffer.readRrsInt();
        mKunlunSSO = buffer.readString();
        mKunlunUID = buffer.readString();
        mUnk5 = buffer.readString();
        mUnk6 = buffer.readString();
        mUnk7 = buffer.read();
    }

    @Override
    public ServerMessage buildResponse() {
        return new LoginOk(this);
    }

    public long getAccountId() {
        return mAccountId;
    }

    public String getToken() {
        return mToken;
    }
}
