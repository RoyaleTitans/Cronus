package com.royale.titans.cronus;

public class Utils {
    private static final String sRandomChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final char[] sHexArray = "0123456789ABCDEF".toCharArray();

    public static String b2h(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = sHexArray[v >>> 4];
            hexChars[j * 2 + 1] = sHexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] h2b(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(sRandomChars.charAt(
                    ServerLogic.getInstance().getRandom()
                            .nextInt(sRandomChars.length())));
        }
        return sb.toString();
    }
}
