package com.spring.base.thread.disruptor;

/**
 * 基础数据结构，用于生产者消费者
 *
 * @author xuweizhi
 */
public class PcData {
    private long value;

    void set(long value) {
        this.value = value;
    }

    public long get() {
        return value;
    }
}
