package com.royale.titans.cronus;

import com.royale.titans.cronus.lib.Buffer;
import jdk.jfr.Unsigned;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Buffer v = Buffer.allocate(10);
        v.writeRrsInt(1503298800);
        v.rewind();
        System.out.println(v.readRrsInt().getValue());
        System.out.println(Utils.b2h(v.array()));

        v.clear();
        v.writeRrsInt(257080);
        v.rewind();
        System.out.println(v.readRrsInt().getValue());

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
}
