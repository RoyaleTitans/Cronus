package com.royale.titans.cronus;

import com.royale.titans.cronus.lib.Buffer;
import com.royale.titans.cronus.lib.OutBuffer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OutBuffer outBuffer = OutBuffer.newBuffer();
        outBuffer.writeString("quest_item_special_pvp");
        System.out.println(Utils.b2h(outBuffer.obtain().rewind().array()));

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
