package com.royale.titans.messages;

import com.royale.titans.Utils;
import com.royale.titans.lib.Buffer;

public class Headers {
    private final int mId;
    private final int mLength;
    private final int mVersion;

    public Headers(Buffer headers) {
        mId = headers.readShort();

        byte[] len = new byte[3];
        headers.read(len, 0, 3);
        mLength = (len[2] & 0xFF) | ((len[1] & 0xFF) << 8) | ((len[0] & 0x0F) << 16);

        mVersion = headers.readShort();
    }

    public Headers(int id, int length, int version) {
        mId = id;
        mLength = length;
        mVersion = version;
    }

    public int getId() {
        return mId;
    }

    public int getLength() {
        return mLength;
    }

    public Buffer toBuffer() {
        Buffer buffer = Buffer.allocate(7);
        buffer.writeShort(mId);
        buffer.write((byte) (mLength >>> 16));
        buffer.write((byte) (mLength >>> 8));
        buffer.write((byte) mLength);
        buffer.writeShort(mVersion);
        buffer.rewind();
        return buffer;
    }
}
