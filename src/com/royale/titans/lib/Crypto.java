package com.royale.titans.lib;

import com.royale.titans.messages.Configs;

import java.util.Arrays;

public class Crypto {

    public static Buffer decryptLogin(Buffer buffer) {
        return Buffer.wrap(
                TweetNaCl.crypto_box_open(
                        Arrays.copyOfRange(buffer.array(), 32, buffer.capacity()),
                        Configs.MAGIC_NONCE,
                        Configs.MAGIC_KEY));
    }
}
