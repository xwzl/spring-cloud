package com.spring.base.thread.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author xuweizhi
 */
public class PcDataHandler implements EventHandler<PcData> {

    @Override
    public void onEvent(PcData event, long sequence, boolean endOfBatch) {
        System.out.println(Thread.currentThread().getId() + ":Event: **" + event.get() * event.get() + "**");
    }
}
