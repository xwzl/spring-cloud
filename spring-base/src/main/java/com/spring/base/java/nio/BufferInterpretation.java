package com.spring.base.java.nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * buffer 解读
 *
 * @author xuweizhi
 * @since 2020/01/02 14:46
 */
@Slf4j
public class BufferInterpretation {

    public static void main(String[] args) {
        //check();

        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 看一下初始时4个核心变量的值
        init(byteBuffer);

        // 添加一些数据到缓冲区中
        putValue(byteBuffer, "Hello World");

        // 看一下初始时4个核心变量的值
        readAfter(byteBuffer);

        putValue(byteBuffer, "title");

        readAfter(byteBuffer);

        // 数据没有真正被清空，只是被遗忘掉了,重置 limit 和 position
        byteBuffer.clear();
        byte[] bytes = new byte[11];
        // 将读取的数据装进我们的字节数组中
        byteBuffer.get(bytes);
        // 输出数据
        log.info(new String(bytes));
    }

    private static void readAfter(ByteBuffer byteBuffer) {
        // 创建一个limit()大小的字节数组(因为就只有limit这么多个数据可读)
        byte[] bytes = new byte[byteBuffer.limit()];
        // 将读取的数据装进我们的字节数组中
        byteBuffer.get(bytes);
        // 输出数据
        log.info(new String(bytes));

        log.info("get完之后-->limit--->" + byteBuffer.limit());
        log.info("get完之后-->position--->" + byteBuffer.position());
        log.info("get完之后-->capacity--->" + byteBuffer.capacity());
        log.info("get完之后-->mark--->" + byteBuffer.mark() + "\n");
        log.info("--------------------------------------");
        // flip 方法切换读写模式，但是 limit 不会改变
        byteBuffer.flip();
    }

    private static void putValue(ByteBuffer byteBuffer, String s) {
        byteBuffer.put(s.getBytes());

        // 看一下初始时4个核心变量的值
        log.info("put完之后-->limit--->" + byteBuffer.limit());
        log.info("put完之后-->position--->" + byteBuffer.position());
        log.info("put完之后-->capacity--->" + byteBuffer.capacity());
        log.info("put完之后-->mark--->" + byteBuffer.mark() + "\n");
        // flip 方法切换读写模式，但是 limit 不会改变
        byteBuffer.flip();
    }

    private static void init(ByteBuffer byteBuffer) {
        log.info("初始时-->limit--->" + byteBuffer.limit());
        log.info("初始时-->position--->" + byteBuffer.position());
        log.info("初始时-->capacity--->" + byteBuffer.capacity());
        log.info("初始时-->mark--->" + byteBuffer.mark());

        log.info("--------------------------------------\n");
    }

    private static void check() {
        //初始化intBuffer变量，缓冲区的大小为10
        //buffer本身就是一块内存，底层实现上，他实际上就是一个数组。数据的读写都是通过buffer来实现的的。
        IntBuffer intBuffer = IntBuffer.allocate(10);

        //Buffer最重要的三个属性
        //Capacity 表示Buffer的容量，长度固定且永远不能为负
        log.info("Capacity:" + intBuffer.capacity());
        //Limit指的是第一个无法被读或者写的元素的索引，永远不可能为负数或者超过Capacity
        log.info("Limit:" + intBuffer.limit());
        //Position 下一个将要被读或者写的元素的索引，永远不会超过limit或者为负
        log.info("Position:" + intBuffer.limit());

        //写数据
        for (int i = 0; i < 5; i++) {
            int data = new SecureRandom().nextInt(20);
            intBuffer.put(data);
        }

        log.info("Before flip  buffer" + intBuffer.mark());
        //状态的反转 从写变成读
        intBuffer.flip();
        log.info("After flip buffer：" + intBuffer.mark());

        while (intBuffer.hasRemaining()) {
            log.info("Buffer" + intBuffer.mark());
            log.info("" + intBuffer.get());
        }
        log.info("" + intBuffer.mark());
    }

}
