package com.spring.base.java.nio;

/**
 * IO 模型
 *
 * @author xuweizhi
 * @since 2020/01/02 17:47
 */
public class IoModel {

    /**
     * <h2>文件描述符</h2>
     * Linux 的内核将所有外部设备都看做一个文件来操作，对一个文件的读写操作会调用内核提供的系统命令(api)，返回一个
     * file descriptor（fd，文件描述符）。而对一个socket的读写也会有响应的描述符，称为socket fd（socket文件描
     * 述符），描述符就是一个数字，指向内核中的一个结构体（文件路径，数据区等一些属性）。
     * <h2>用户空间和内核空间</h2>
     * 为了保证用户进程不能直接操作内核（kernel），保证内核的安全，操心系统将虚拟空间划分为两部分
     * <ul>
     *     <li>一部分为内核空间</li>
     *     <li>一部分为用户空间。</li>
     * </ul>
     * <h2>I/O运行过程</h2>
     *
     * 我们来看看IO在系统中的运行是怎么样的(我们以read为例)
     *
     * <ul>
     *     <li>应用程序调用系统提供的 read 接口 api</li>
     *     <li>此时系统会干两件事：1. 等待数据准备（查看内核空间有没有数据） 2.讲数据从内核拷贝</li>
     *     <li></li>
     * </ul>
     */
    void privateDescription() {

    }

}
