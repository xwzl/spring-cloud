package com.spring.netty.server;

import com.spring.netty.protocol.message.HeartbeatResponsePacket;
import com.spring.netty.protocol.protobuf.MessageBase;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @author xuweizhi
 * @since 2019-09-22
 */
@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<MessageBase.Message> {

    /**
     * 设置空闲检测时间为 120s，如果空间则关闭连接
     */
    private static final int READER_IDLE_TIME = 120;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("CLIENT" + getRemoteAddress(ctx) + " 接入连接");
        //往channel map中添加channel信息
        NettyServer.getChannels().put(getIpString(ctx), ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("{} 秒内没有读取到数据,关闭连接", READER_IDLE_TIME);
        //删除Channel Map中的失效Client
        NettyServer.getChannels().remove(getIpString(ctx));
        // 这里不做关闭，由指定的空闲检测处理器处理
        //ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBase.Message msg) throws Exception {
        if (msg.getCmd().equals(MessageBase.Message.CommandType.HEARTBEAT_REQUEST)) {
            log.info("收到客户端发来的心跳消息：{}", msg.toString());
            //回应pong
            ctx.writeAndFlush(new HeartbeatResponsePacket());
        } else if (msg.getCmd().equals(MessageBase.Message.CommandType.NORMAL)) {
            log.info("收到客户端收到消息：{}, requestId: {}", msg.getContent(), msg.getRequestId());
            //MessageBase.Message message = new MessageBase.Message()
            //        .toBuilder().setCmd(MessageBase.Message.CommandType.NORMAL)
            //        .setContent("服务器返回的消息")
            //        .setRequestId(UUID.randomUUID().toString()).build();
            //// 返回给客户端的消息
            //ctx.writeAndFlush(message);
        }
    }

    @NotNull
    private static String getIpString(ChannelHandlerContext ctx) {
        String ipString = "";
        String socketString = ctx.channel().remoteAddress().toString();
        int colonAt = socketString.indexOf(":");
        ipString = socketString.substring(1, colonAt);
        return ipString;
    }


    private static String getRemoteAddress(@NotNull ChannelHandlerContext ctx) {
        String socketString = "";
        socketString = ctx.channel().remoteAddress().toString();
        return socketString;
    }

}



