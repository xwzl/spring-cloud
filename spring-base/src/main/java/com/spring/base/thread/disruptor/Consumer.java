package com.spring.base.thread.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * 实现 handler 用于消费消息
 *
 * @author xuweizhi
 */
public class Consumer implements WorkHandler<PcData> {

    @Override
    public void onEvent(PcData event) throws Exception {
        System.out.println(Thread.currentThread().getId() + ":Event: --" + event.get() * event.get() + "--");
    }

}
