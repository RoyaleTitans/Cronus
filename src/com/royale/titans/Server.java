package com.royale.titans;

import com.royale.titans.lib.Buffer;
import com.royale.titans.messages.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
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
                listener.accept( null, this );

                boolean running = true;

                try {
                    Buffer buf = Buffer.allocate(7);
                    int read = channel.read(buf.getByteBuffer()).get(5, TimeUnit.SECONDS);

                    while (read != -1 && running) {
                        System.out.println("start read_ " + read);
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
                            ClientMessage message = Router.route(headers, buf);

                            if (message != null) {
                                Buffer b = message.getBuffer();
                                if (Configs.DEBUG) {
                                    b.rewind();
                                    System.out.println("[SERVER] [IN] msgId: " + Utils.b2h(b.array()));
                                }

                                ServerMessage response = message.buildResponse();
                                if (response != null) {
                                    b = response.getBuffer();

                                    headers = new Headers(response.getId(), b.capacity(), response.getVersion());
                                    channel.write(headers.toBuffer().getByteBuffer());

                                    if (Configs.DEBUG) {
                                        System.out.println("[SERVER] [OUT] msgId: " + headers.getId() + " - len: " + headers.getLength());
                                    }

                                    b.rewind();
                                    channel.write(b.getByteBuffer());

                                    if (Configs.DEBUG) {
                                        b.rewind();
                                        System.out.println("[SERVER] [OUT]: " + Utils.b2h(response.getBuffer().array()));
                                    }
                                }
                            } else {
                                if (Configs.DEBUG) {
                                    System.out.println("[SERVER] [IN] msgId: " + headers.getId() + " not managed.");
                                }
                            }

                            buf.clear();
                            buf = Buffer.allocate(7);
                            System.out.println("start read2_ " + read);
                            read = channel.read(buf.getByteBuffer()).get(5, TimeUnit.SECONDS);
                            System.out.println("start read_3 " + read);
                        } else {
                            running = false;
                        }
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    System.out.println("[SERVER] Connection timed out, closing connection");
                }

                System.out.println("[SERVER] End of connection");
                try {
                    if (channel.isOpen()) {
                        channel.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
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
