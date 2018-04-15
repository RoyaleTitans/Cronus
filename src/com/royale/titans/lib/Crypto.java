package com.royale.titans.lib;

import com.royale.titans.Utils;
import com.royale.titans.messages.Configs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class Crypto {

    private static final ConcurrentHashMap<String, Nonce> sSessions =
            new ConcurrentHashMap<>();

    public static Buffer decryptLogin(Buffer buffer) {
        byte[] loginDecrypted = TweetNaCl.crypto_box_open(
                Arrays.copyOfRange(buffer.array(), 32, buffer.capacity()),
                Configs.MAGIC_NONCE,
                Configs.MAGIC_KEY);
        byte[] sessionKey = Arrays.copyOfRange(loginDecrypted, 0, 24);
        byte[] sNonce = Arrays.copyOfRange(loginDecrypted, 24, 48);
        sSessions.put(new String(sessionKey), new Nonce(sNonce));
        return Buffer.wrap(Arrays.copyOfRange(loginDecrypted, 48, loginDecrypted.length));
    }

    public static void invalidateSession(String sessionKey) {
        sSessions.remove(sessionKey);
    }

    public static Buffer encrypt(String session, int messageId, Buffer buffer) {
        if (messageId == 20100) {
            return buffer;
        } else if (messageId == 22280) {
            try {
                Nonce nonce = new Nonce(new byte[32], Configs.PUBLIC_SERVER_KEY,
                        sSessions.get(session).getBytes());
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(new byte[24]);
                byteArrayOutputStream.write(new byte[32]);
                byteArrayOutputStream.write(buffer.array());
                byte[] p = byteArrayOutputStream.toByteArray();
                return Buffer.wrap(TweetNaCl.crypto_box(p, nonce.getBytes(),
                        Configs.MAGIC_KEY));
            } catch (IOException ignored) {}
        }

        return null;
    }
}
