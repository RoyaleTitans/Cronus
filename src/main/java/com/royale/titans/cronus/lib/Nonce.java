package com.royale.titans.cronus.lib;

import ove.crypto.digest.Blake2b;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Nonce {
    private byte[] mBytes;

    public Nonce(byte[] nonce) {
        mBytes = nonce;
    }

    Nonce(byte[] clientKey, byte[] serverKey, byte[] nonce) {
        final Blake2b hash = Blake2b.Digest.newInstance(24);
        if (nonce != null) {
            hash.update(nonce);
        }

        hash.update(clientKey);
        hash.update(serverKey);

        mBytes = hash.digest();
    }

    void increment() {
        ByteBuffer buffer = ByteBuffer.wrap(mBytes, 0, 2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        short val = buffer.getShort(0);
        val += 2;
        buffer.putShort(0, val);

        mBytes[0] = buffer.get(0);
        mBytes[1] = buffer.get(1);
    }

    byte[] getBytes() {
        return mBytes;
    }
}