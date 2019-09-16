package com.spring.base.thread.disruptor.feeling;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.ThreadFactory;

/**
 * @author xuweizhi
 * @since 2019/09/16 22:03
 */
public class PhoneSample {

    public static void main(String[] args) throws InterruptedException {
        // size 为 2 的 n 次方
        Disruptor<Phone> phoneDisruptor = new Disruptor<Phone>(Phone::new, 1024, (ThreadFactory) Thread::new);
        // 设置消费者
        phoneDisruptor.handleEventsWithWorkerPool(new Consumer("张三"), new Consumer("李四 "), new Consumer("王五"));
        // 开始
        phoneDisruptor.start();

        Product product = new Product(phoneDisruptor.getRingBuffer());
        for (int i = 0; i < 100; i++) {
            // 产生数据
            product.pushData();
        }

    }
}
