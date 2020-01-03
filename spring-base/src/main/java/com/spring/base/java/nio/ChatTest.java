package com.spring.base.java.nio;

import java.io.IOException;

/**
 * 聊天示例
 *
 * @author xuweizhi
 * @since 2020/01/03 16:47
 */
public class ChatTest {

    public static void main(String[] args) throws IOException {
        NoBlockChatServer server = new NoBlockChatServer(7777);
    }
}


