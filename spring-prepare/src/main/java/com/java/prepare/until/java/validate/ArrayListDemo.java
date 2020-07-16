package com.java.prepare.until.java.validate;

import org.junit.Test;

import java.util.*;

/**
 * 线程不安全
 *
 * @author xuweizhi
 * @since 2020/07/08 17:38
 */
public class ArrayListDemo {

    /**
     * 1、 出现异常，并发异常
     * java.util.ConcurrentModificationException
     * <p>
     * 2. 解决
     * <ul>
     *     <li>1. ArrayList => Vector</li>
     *     <li>2. ArrayList => Collections.synchronizedList(list)</li>
     *     <li>3. CopyOnWriteArrayList：写时复制</li>
     * </ul>
     * <p>
     * 3. 产生原因
     * <p>
     * 一个线程正在写，另一个线程过来抢占资源，会造成数据不一致，进而报并发修改异常。
     * <p>
     * 4. 读写分离
     * <p>
     * CopyOnWriteArrayList容器即写时复制容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前
     * 容器Object []进行copy，复制出一个新的容器object[] newElements，然后往新的容器Object [] newElements里添加
     * 元素，添加元素之后，再将原容器的引用指向新的容器setArray(newElements)；这样做的好处是可以对CopyOnWrite容器进
     * 行并发读，而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写是不同的
     * 容器。
     */
    @Test
    public void arrayList() throws InterruptedException {
        List<String> list = new ArrayList<>();
        Collections.synchronizedList(list);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add("" + System.currentTimeMillis());
                System.out.println(list.toString());
            }).start();
        }
        Thread.currentThread().join();
    }

    /**
     * HashSet 底层是 HashMap
     */
    @Test
    public void testHashSet() {
        HashSet<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");

        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
    }

}
