package com.royale.titans.cronus.lib;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.messages.Configs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Crypto {
    private static final byte[] NULL = new byte[32];

    public static Buffer decryptLogin(Buffer buffer) {
        Buffer loginDecrypted = Buffer.wrap(TweetNaCl.crypto_box_open(
                Arrays.copyOfRange(buffer.array(), 32, buffer.capacity()),
                Configs.MAGIC_NONCE,
                Configs.MAGIC_KEY));
        byte[] sessionKey = loginDecrypted.read(24);
        byte[] sNonce = loginDecrypted.read(24);

        ServerLogic.ClientInfo info = ServerLogic.getInstance().findSession(new String(sessionKey));
        if (info == null) {
            return null;
        }

        info.setNonce(new Nonce(sNonce));

        return loginDecrypted;
    }

    public static Buffer encrypt(ServerLogic.ClientInfo info, int messageId, Buffer buffer) {
        if (buffer.capacity() == 0) {
            return buffer;
        }

        if (messageId == 20100 || messageId == 20103) {
            return buffer;
        } else if (messageId == 22280) {
            try {
                Nonce nonce = new Nonce(NULL, Configs.PUBLIC_SERVER_KEY,
                        info.sNonce().getBytes());
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(new byte[24]);
                byteArrayOutputStream.write(NULL);
                byteArrayOutputStream.write(buffer.array());
                byte[] p = byteArrayOutputStream.toByteArray();
                return Buffer.wrap(TweetNaCl.crypto_box(p, nonce.getBytes(),
                        Configs.MAGIC_KEY));
            } catch (IOException ignored) {
                return null;
            }
        } else {
            info.rNonce().increment();
            return Buffer.wrap(TweetNaCl.crypto_box(buffer.array(),
                    info.rNonce().getBytes(), NULL));
        }
    }
}
