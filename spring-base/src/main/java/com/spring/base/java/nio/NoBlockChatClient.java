package com.spring.base.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * NIO 非阻塞聊天服务端
 *
 * @author xuweizhi
 * @since 2020/01/03 14:21
 */
public class NoBlockChatClient {

    private void client(int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", port));
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.print("欢迎来到聊天室：");
        new Thread(() -> {
            try {
                hello(selector);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            Scanner scanner = new Scanner(System.in);
            String next = scanner.next();
            buffer.put(next.getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            // 读完切换成写模式，能让管道继续读取文件的数据
            buffer.clear();
        }
    }

    private static void hello(Selector selector) throws IOException {
        // 5. 轮训地获取选择器上已“就绪”的事件--->只要select()>0，说明已就绪
        while (selector.select() > 0) {
            // 6. 获取当前选择器所有注册的“选择键”(已就绪的监听事件)
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            // 7. 获取已“就绪”的事件，(不同的事件做不同的事)
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 8. 读事件就绪
                if (selectionKey.isReadable()) {
                    // 8.1得到对应的通道
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer responseBuffer = ByteBuffer.allocate(8192);
                    // 9. 知道服务端要返回响应的数据给客户端，客户端在这里接收
                    int readBytes = channel.read(responseBuffer);
                    if (readBytes > 0) {
                        // 切换读模式
                        responseBuffer.flip();
                        System.out.println(new String(responseBuffer.array(), 0, readBytes));
                    }
                }
                // 10. 取消选择键(已经处理过的事件，就应该取消掉了)
                iterator.remove();
            }
        }
    }
}
