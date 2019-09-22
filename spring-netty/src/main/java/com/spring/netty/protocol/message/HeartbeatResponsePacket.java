package com.spring.netty.protocol.message;

import com.spring.netty.protocol.message.command.Command;

/**
 * @author xuweizhi
 * @since 2019-09-22
 */
public class HeartbeatResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
