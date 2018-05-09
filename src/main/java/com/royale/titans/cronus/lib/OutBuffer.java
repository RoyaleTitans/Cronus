package com.royale.titans.cronus.lib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class OutBuffer {
    private final ByteArrayOutputStream mOutStream = new ByteArrayOutputStream();

    public static OutBuffer newBuffer() {
        return new OutBuffer();
    }

    public Buffer obtain() {
        return Buffer.wrap(mOutStream.toByteArray());
    }

    public OutBuffer write(byte ... bytes) {
        try {
            mOutStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public OutBuffer write(byte[] bytes, int off, int len) {
        mOutStream.write(bytes, off, len);
        return this;
    }

    public OutBuffer writeInt(int value) {
        try {
            mOutStream.write(ByteBuffer.allocate(4).putInt(value).array());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public OutBuffer writeIntLe(int value) {
        try {
            mOutStream.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public OutBuffer writeLong(long value) {
        try {
            mOutStream.write(ByteBuffer.allocate(8).putLong(value).array());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public int writeRrsInt(long value) {
        if (value == 0) {
            write((byte) 0);
            return 1;
        }

        int c = 0;
        boolean rotate = true;
        byte b;

        value = (value << 1) ^ (value >> 31);
        value >>>= 0;

        while (value != 0) {
            b = (byte) (value & 0x7f);
            if (value < 0 || value >= 0x80)
                b |= 0x80;
            if (rotate) {
                rotate = false;
                byte lsb = (byte) (b & 0x1);
                byte msb = (byte) ((b & 0x80) >> 7);

                b = (byte) (b >> 1);
                b = (byte) (b & ~(0xC0));
                b = (byte) (b | (msb << 7) | (lsb << 6));
            }
            write(b);
            value >>>= 7;
            c++;
        }
        return c;
    }

    public OutBuffer writeShort(int value) {
        try {
            mOutStream.write(ByteBuffer.allocate(2).putShort((short) value).array());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public OutBuffer writeString(String value) {
        writeInt(value.length());
        if (value.length() > 0) {
            try {
                mOutStream.write(value.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }
}
