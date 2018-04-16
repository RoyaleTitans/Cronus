package com.royale.titans.cronus.messages.client;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.messages.ClientMessage;

public class ClientHello extends ClientMessage {
    private final int mProtocol;
    private final int mKeyVersion;
    private final int mMajorVersion;
    private final int mMinorVersion;
    private final int mBuildVersion;
    private final String mFingerprint;
    private final int mAppStore;
    private final int mDeviceType;

    public ClientHello(Buffer buffer) {
        super(buffer);

        mProtocol = buffer.readInt();
        mKeyVersion = buffer.readInt();
        mMajorVersion = buffer.readInt();
        mBuildVersion = buffer.readInt();
        mMinorVersion = buffer.readInt();
        mFingerprint = buffer.readString();
        mAppStore = buffer.readInt();
        mDeviceType = buffer.readInt();
    }
}
