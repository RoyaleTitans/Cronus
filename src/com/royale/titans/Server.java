package com.royale.titans;

import com.royale.titans.lib.Buffer;
import com.royale.titans.lib.Crypto;
import com.royale.titans.messages.*;
import com.royale.titans.messages.server.ServerHello;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Server {
    private boolean mRunning = true;

    void connect() throws IOException {
        final AsynchronousServerSocketChannel listener =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9339));

        listener.accept(null, new CompletionHandler<>() {
            @Override
            public void completed(AsynchronousSocketChannel channel, Object attachment) {
                listener.accept(null, this);

                String sessionKey = null;
                boolean running = true;

                try {
                    Buffer buf = Buffer.allocate(7);
                    int read = channel.read(buf.getByteBuffer()).get(5, TimeUnit.SECONDS);

                    while (read != -1 && running) {
                        if (buf.position() > 2) {
                            buf.flip();

                            Headers headers = new Headers(buf);

                            if (Configs.DEBUG) {
                                System.out.println("[SERVER] [IN] msgId: " + headers.getId() + " - len: " + headers.getLength());
                            }

                            buf.clear();
                            buf = Buffer.allocate(headers.getLength());
                            read = 0;
                            while (read != headers.getLength()) {
                                read = channel.read(buf.getByteBuffer()).get(5, TimeUnit.SECONDS);
                            }

                            buf.flip();
                            ClientMessage message = ServerLogic.route(headers, buf);

                            if (message != null) {
                                Buffer b = message.getBuffer();
                                if (Configs.DEBUG) {
                                    b.rewind();
                                    System.out.println("[SERVER] [IN] msgId: " + Utils.b2h(b.array()));
                                }

                                ServerMessage[] handled = ServerLogic.handle(headers, message);

                                if (handled != null) {
                                    for (ServerMessage serverMessage : handled) {
                                        if (serverMessage.getId() == 20100) {
                                            sessionKey = ((ServerHello) serverMessage).getSessioneKey();
                                        }

                                        b = serverMessage.getBuffer();
                                        b.rewind();

                                        Buffer encrypted = Crypto.encrypt(sessionKey, serverMessage.getId(), b);
                                        if (encrypted != null) {
                                            headers = new Headers(serverMessage.getId(),
                                                    encrypted.capacity(),
                                                    serverMessage.getVersion());
                                            channel.write(headers.toBuffer().getByteBuffer());

                                            if (Configs.DEBUG) {
                                                System.out.println("[SERVER] [OUT] msgId: " + headers.getId() + " - len: " + headers.getLength());
                                            }

                                            channel.write(encrypted.getByteBuffer());
                                            encrypted.clear();

                                            if (Configs.DEBUG) {
                                                b.rewind();
                                                System.out.println("[SERVER] [OUT]: " + Utils.b2h(
                                                        serverMessage.getBuffer().array()));
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (Configs.DEBUG) {
                                    System.out.println("[SERVER] [IN] msgId: " + headers.getId() + " not managed.");
                                }
                            }

                            buf.clear();
                            buf = Buffer.allocate(7);
                            read = channel.read(buf.getByteBuffer()).get(5, TimeUnit.SECONDS);
                        } else {
                            running = false;
                        }
                    }

                } catch (TimeoutException | InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

                System.out.println("[SERVER] End of connection");
                try {
                    if (channel.isOpen()) {
                        channel.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                if (sessionKey != null) {
                    Crypto.invalidateSession(sessionKey);
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("[SERVER] err: " + exc.toString());
            }
        });
    }

    boolean isRunning() {
        return mRunning;
    }
}
