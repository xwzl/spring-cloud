package com.spring.netty.protocol.message;

import lombok.Data;

import static com.spring.netty.protocol.message.command.Command.HEARTBEAT_REQUEST;

/**
 * @author xuweizhi
 * @create 2018-10-25 16:12
 */
@Data
public class HeartbeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
