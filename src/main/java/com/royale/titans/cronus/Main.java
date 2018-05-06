package com.royale.titans.cronus;

import java.io.IOException;

public class Main {
    private static final int[] WEED_TABLE = new int[] {
            0x431bde83, 0x3d09, 0x154ac8ce, 0xde68b267
    };

    public static void main(String[] args) throws IOException {
        System.out.println(sc_x(0x4c4b40));
        Server server = new Server();
        server.connect();

        while (server.isRunning()) {
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Server died");
    }

    private static long sc_x(long x) {
        int a = (int) ((((long) WEED_TABLE[0]) * x & 0xffffffff00000000L) >> 32);
        a = (a >> 18) + (a >> 31);
        return x - ((a * WEED_TABLE[1]) << 6) + 1;
    }
}
