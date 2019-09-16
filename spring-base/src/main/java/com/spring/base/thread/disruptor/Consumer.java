package com.spring.base.thread.disruptor;

import com.lmax.disruptor.WorkHandler;
import org.jetbrains.annotations.NotNull;

/**
 * 实现 handler 用于消费消息
 *
 * @author xuweizhi
 */
public class Consumer implements WorkHandler<PcData> {

    /**
     * 获取线程缓存中的数据结构
     */
    @Override
    public void onEvent(@NotNull PcData event) throws Exception {
        System.out.println(Thread.currentThread().getId() + ":Event: --" + event.get() * event.get() + "--");
    }

}
