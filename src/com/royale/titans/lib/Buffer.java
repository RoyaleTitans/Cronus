package com.royale.titans.lib;

import java.nio.ByteBuffer;

public class Buffer {
    private final ByteBuffer mBuffer;

    public static Buffer allocate(int size) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        return new Buffer(byteBuffer);
    }

    public static Buffer wrap(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return new Buffer(byteBuffer);
    }

    public Buffer(ByteBuffer byteBuffer) {
        mBuffer = byteBuffer;
    }

    public byte[] array() {
        return mBuffer.array();
    }

    public int capacity() {
        return mBuffer.capacity();
    }

    public void clear() {
        mBuffer.clear();
    }

    public void flip() {
        mBuffer.flip();
    }

    public ByteBuffer getByteBuffer() {
        return mBuffer;
    }

    public int position() {
        return mBuffer.position();
    }

    public void position(int newPosition) {
        mBuffer.position(newPosition);
    }

    public byte read() {
        return mBuffer.get();
    }

    public void read(byte[] dest, int offset, int len) {
        mBuffer.get(dest, offset, len);
    }

    public int readInt() {
        return mBuffer.getInt();
    }

    public int readShort() {
        return mBuffer.getShort();
    }

    public long readLong() {
        return mBuffer.getLong();
    }

    public String readString() {
        int len = readInt();
        byte[] b = new byte[len];
        read(b, 0, len);
        return new String(b);
    }

    public void rewind() {
        mBuffer.rewind();
    }

    public void write(byte b) {
        mBuffer.put(b);
    }

    public void writeShort(int value) {
        mBuffer.putShort((short) value);
    }

    public void writeString(String value) {
        mBuffer.putInt(value.length());
        mBuffer.put(value.getBytes(), 0, value.length());
    }

    public RrsInt readRrsInt() {
        int c = 0;
        int value = 0;
        int seventh;
        int msb;
        int b;
        do {
            b = read();

            if (c == 0) {
                seventh = (b & 0x40) >> 6;
                msb = (b & 0x80) >> 7;
                b = b << 1;
                b = b & ~(0x181);
                b = b | (msb << 7) | (seventh);
            }

            value |= (b & 0x7f) << (7 * c);
            ++c;
        } while ((b & 0x80) != 0);

        value = ((value >>> 1) ^ -(value & 1));
        return new RrsInt(value, c);
    }
}
