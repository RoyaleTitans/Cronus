package com.royale.titans.cronus;

import com.royale.titans.cronus.lib.OutBuffer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OutBuffer outBuffer = OutBuffer.newBuffer();
        outBuffer.writeInt(1254674151);
        outBuffer.writeRrsInt(-12374346);
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
