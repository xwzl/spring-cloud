package com.spring.base.java.nio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * NIO 非阻塞聊天服务端
 *
 * @author xuweizhi
 * @since 2020/01/03 14:21
 */
public class NoBlockChatServer {

    private Map<Integer, ServerCache> cache = new HashMap<>();

    private int port;

    private Selector selector;

    private ServerSocketChannel server;

    /**
     * 聊天服务构造函数
     *
     * @param port 服务端口
     */
    public NoBlockChatServer(int port) throws IOException {
        this.port = port;
        init();
        this.start();
    }

    /**
     * 初始化服务配置
     */
    private void init() {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            this.selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void start() throws IOException {
        while (selector.select() > 0) {
            work(selector.selectedKeys().iterator());
        }
    }

    private void work(Iterator<SelectionKey> iterator) throws IOException {
        while (iterator.hasNext()) {
            SelectionKey selectionKey = iterator.next();
            // 读准备
            if (selectionKey.isAcceptable()) {
                channelReady();
                // 读
            } else if (selectionKey.isReadable()) {
                dealClientRequest(selectionKey);
            }
            iterator.remove();
        }
    }

    private void dealClientRequest(SelectionKey selectionKey) throws IOException {
        SocketChannel client = (SocketChannel) selectionKey.channel();
        String body = getRequestBody(client);
        cache.keySet().stream().filter(hashCode -> client.hashCode()!=hashCode).forEach(hashCode -> {
            try {
                responseClient(body, hashCode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //for (int socketChannelCode : cache.keySet()) {
        //    if (client.hashCode() != socketChannelCode) {
        //        responseClient(body, socketChannelCode);
        //    }
        //    } else {
        //        if (cache.get(socketChannelCode).getCode() == 0) {
        //            ByteBuffer writerBuffer = ByteBuffer.allocate(8192);
        //            String name = cache.get(socketChannelCode).getName() + "加入聊天室";
        //            writerBuffer.put(name.getBytes());
        //            writerBuffer.flip();
        //            cache.get(socketChannelCode).getClient().write(writerBuffer);
        //            cache.get(socketChannelCode).setCode(1);
        //        }
        //    }
        //}
    }

    private void responseClient(String body, int socketChannelCode) throws IOException {
        ByteBuffer writerBuffer = ByteBuffer.allocate(8192);
        writerBuffer.put(body.getBytes());
        writerBuffer.flip();
        cache.get(socketChannelCode).getClient().write(writerBuffer);
    }

    private String getRequestBody(SocketChannel client) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        int readBytes = client.read(buffer);
        buffer.flip();
        return new String(buffer.array(), 0, readBytes);
    }

    private void channelReady() throws IOException {
        SocketChannel client = server.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        cache.put(client.hashCode(), new ServerCache(client, client.getRemoteAddress() + "：" + client.socket().getPort(), 0));
    }

}

@Data
@AllArgsConstructor
class ServerCache {
    private SocketChannel client;
    private String name;
    private int code;
}
