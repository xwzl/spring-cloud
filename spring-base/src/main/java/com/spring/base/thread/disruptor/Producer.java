package com.spring.base.thread.disruptor;


import com.lmax.disruptor.RingBuffer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

/**
 * 生产者需要一个 RingBuffer 的引用，也就是环形缓冲区。它有一个重要的方法 pushData()将产生的数据推入缓冲区。
 * <p>
 * 方法 pushData() 接收一个 ByteBuffer 对象。在ByteBuffer 对象中可以用来包装任何数据类型。这里用来存储 long
 * 整数，pushData() 方法的功能就是将传入的 ByteBuffer 对象中的数据提出出来，并装载到环信缓冲区中。
 *
 * @author xuweizhi
 */
class Producer {

    private final RingBuffer<PcData> ringBuffer;

    @Contract(pure = true)
    Producer(RingBuffer<PcData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * next() 方法的得到下一个可用的序列号。通过序列号，取得下一个空闲可用的 PcData 对象，并且将 PcData 对象的数据
     * 设为期望值，这个值最终会传递给消费者。
     */
    void pushData(@NotNull ByteBuffer bb) {
        long sequence = ringBuffer.next();
        try {
            PcData event = ringBuffer.get(sequence);

            event.set(bb.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
