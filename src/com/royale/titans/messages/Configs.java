package com.royale.titans.messages;

import com.royale.titans.Utils;

public class Configs {
    public static final boolean DEBUG = true;

    public static final byte[] MAGIC_NONCE =
            Utils.h2b("2EF30B5892E0AB07442EF1CFACDFECA015ACF827A7334C6A");
    public static final byte[] MAGIC_KEY =
            Utils.h2b("1988C0E53348E75487339216F09E7A350B75A07EE174C87BAD59C35C512288F4");
    public static final byte[] PUBLIC_SERVER_KEY =
            Utils.h2b("99B61876F3FF18CAECA0AEC1F326D9981BBCAF64E7DAA317A7F10966867AF968");
}
