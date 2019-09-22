package com.spring.netty.utils;

import com.spring.netty.protocol.protobuf.MessageBase;

import java.util.UUID;

/**
 * @author xuweizhi
 */
public class MessageBaseTest {
    public static void main(String[] args) {
        MessageBase.Message message = MessageBase.Message.newBuilder()
                .setCmd(MessageBase.Message.CommandType.NORMAL)
                .setRequestId(UUID.randomUUID().toString())
                .setContent("我们").build();
        System.out.println("message: "+message.getContent());
    }
}
