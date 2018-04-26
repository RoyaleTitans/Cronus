package com.royale.titans.cronus;

public class Configs {
    static final boolean DEBUG = true;

    public static final String FINGERPRINT = "bfc0f440c73556544ede89bfef2b1e5245190993";

    public static final byte[] MAGIC_NONCE =
            Utils.h2b("8907a714cd1042e96daf7b9ad910c4cb2e34b2414fd5819f");
    public static final byte[] MAGIC_KEY =
            Utils.h2b("fb523187d9e4dcdb0a136e6a77a677de7a7983b9166eb1604f8f24aeecd750b3");
    public static final byte[] PUBLIC_SERVER_KEY =
            Utils.h2b("8cfd11687a20d616f0b7dc0ceed00ce12f5f95e2e100c9ff561b0c4117e6e44d");
}
