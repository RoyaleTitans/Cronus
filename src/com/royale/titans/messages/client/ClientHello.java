package com.royale.titans.messages.client;

import com.royale.titans.lib.Buffer;
import com.royale.titans.messages.ClientMessage;
import com.royale.titans.messages.ServerMessage;
import com.royale.titans.messages.server.ServerHello;

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

    @Override
    public ServerMessage buildResponse() {
        return new ServerHello();
    }
}
