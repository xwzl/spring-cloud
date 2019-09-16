package com.spring.base.thread.disruptor.feeling;

import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 手机制造者
 *
 * @author xuweizhi
 * @since 2019/09/16 21:49
 */
@Slf4j
public class Product {

    /**
     * 缓冲队列
     */
    private RingBuffer<Phone> ringBuffer;

    /**
     * 厂商
     */
    private List<String> brands = Arrays.asList("小米", "华为", "苹果", "三星", "2000", "1000", "5000", "5000");

    Product(RingBuffer<Phone> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    void pushData() {
        // 获取缓冲区中一个可用的序列
        long sequence = ringBuffer.next();
        Phone phone = null;
        try {
            phone = ringBuffer.get(sequence);
            phone.setBrand(brands.get(getKey()));
            phone.setPrice(Double.parseDouble(brands.get(getKey() + 4)));
            phone.setCreateTime(LocalDateTime.now());
            phone.setId(UUID.randomUUID().toString());
        } finally {
            // 数据推送到缓冲区中
            ringBuffer.publish(sequence);
            log.info("生产手机：" + phone.toString() + "一部");
        }
    }

    private int getKey() {
        return (int) Math.floor(Math.random() * 4);
    }
}
