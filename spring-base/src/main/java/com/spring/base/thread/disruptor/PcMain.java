package com.spring.base.thread.disruptor;


import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;

/**
 * @author xuweizhi
 */
public class PcMain {

    public static void main(String[] args) throws Exception {
        // 初始化 disruptor 实例
        //PcDataFactory factory = new PcDataFactory();
        int bufferSize = 1024;
        // 构建 disruptor 实例需要创建一个数据实体，缓冲区大小，线程工厂
        Disruptor<PcData> disruptor = new Disruptor<>(PcData::new, bufferSize, (ThreadFactory) Thread::new);
        // 设置消费者线程
        disruptor.handleEventsWithWorkerPool(new Consumer(), new Consumer(), new Consumer(), new Consumer());
        disruptor.start();

        // 获取环形队列
        RingBuffer<PcData> ringBuffer = disruptor.getRingBuffer();
        // 初始化生产者
        Producer producer = new Producer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);

        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            producer.pushData(bb);
            Thread.sleep(100);
            System.out.println("add data " + l);
        }
    }
}
