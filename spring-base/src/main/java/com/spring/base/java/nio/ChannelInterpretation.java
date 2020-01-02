package com.spring.base.java.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Channel 解读
 *
 * @author xuweizhi
 * @since 2020/01/02 16:26
 */
@Slf4j
public class ChannelInterpretation {

    /**
     * 字节缓冲区要么是直接的,要么是非直接的。如果为直接字节缓冲区,则 Java 虚拟机会尽最大努力直接在
     * 此缓冲区上执行本机操作。也就是说,在每次调用基础操作系统的一个本机操作之前(或之后),虚拟机都会
     * 尽量避免将缓冲区的内容复制到中间缓冲区中(或从中间缓冲区中复制内容)
     *
     * 直接字节缓冲区可以通过调用此类的 allocateDirect()工厂方法来创建。此方法返回的缓冲区进行分配和取消
     * 分配所需成本通常高于非直接缓冲区直接缓冲区的内容可以驻留在常规的垃圾回收堆之外,因此,它们对应用程序的
     * 内存需求量造成的影响可能并不明显所以,建议将直接缓冲区主要分配给那些易受基础系统的本机操作影响的大型、
     * 持久的缓冲区一般情况下,最好仅在直接缓冲区能在程序性能方面带来明显好处时分配它们。
     *
     * 直接字节缓冲区还可以通过 FileChannel 的map(方将文件区域直接映射到内存中来创建该方法返回 MappedByteBuffer.
     * Java平台的实现有助于通过 JNI 从本机代码创建直接字节缓冲区。如果以上这些缓冲区中的某个缓冲区实例指的
     * 是不可访问的内存区域则试图访问该区域不会更改该缓冲区的内容,并且将会在访问期间或稍后的某个时间导致抛出
     * 不确定的异常。
     *
     * 字节缓冲区是直接缓冲区还是非直接缓冲区可通过调用其 isDirect方法来确定提供此方法了能够在性能关键型代
     * 码中执行显式缓冲区管理。
     */
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("D:\\root\\spring-cloud\\spring-base\\src\\main\\java\\com\\spring\\base\\java\\nio\\input.txt");
        FileOutputStream out = new FileOutputStream("D:\\root\\spring-cloud\\spring-base\\src\\main\\java\\com\\spring\\base\\java\\nio\\output.txt");

        FileChannel inputChannel = in.getChannel();
        FileChannel outputChannel = out.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (inputChannel.read(buffer) != -1) {
            buffer.flip();
            outputChannel.write(buffer);
            buffer.clear();
        }

        inputChannel.close();
        outputChannel.close();

    }
}
