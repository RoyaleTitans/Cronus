package com.royale.titans.lib;

import java.nio.ByteBuffer;

public class Buffer {
    private final ByteBuffer mBuffer;

    public static Buffer allocate(int size) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
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

    public int position() {
        return mBuffer.position();
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

    public ByteBuffer getByteBuffer() {
        return mBuffer;
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
}
