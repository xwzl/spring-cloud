package com.spring.netty.protocol.message.command;

/**
 * @author xuweizhi
 */
public interface Command {

    Byte HEARTBEAT_REQUEST = 1;

    Byte HEARTBEAT_RESPONSE = 2;

}
