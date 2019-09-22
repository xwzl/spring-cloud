package com.spring.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

/**
 * 空闲检测
 *
 * @author xuweizhi
 * @since 2019-09-22
 */
@Slf4j
public class ServerIdleStateHandler extends IdleStateHandler {

    /**
     * 设置空闲检测时间为 30s，没有则关闭连接
     */
    private static final int READER_IDLE_TIME = 30;

    public ServerIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(@NotNull ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        log.info("{} 秒内没有读取到数据,关闭连接", READER_IDLE_TIME);
        ctx.channel().close();
    }
}
