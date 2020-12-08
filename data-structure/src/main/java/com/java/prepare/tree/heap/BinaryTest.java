package com.java.prepare.tree.heap;

import org.junit.Test;

import java.util.Arrays;

/**
 * 堆测试
 *
 * @author lx
 */
public class BinaryTest {
    /**
     * 通过数组在构造器中构建大顶堆
     */
    @Test
    public void testMaxBinaryHeap1() {
        Integer[] arr = new Integer[]{49, 38, 65, 97, 76, 13, 27, 49, 78};
        //构建大顶堆
        MaxBinaryHeap<Integer> maxBinaryHeap = new MaxBinaryHeap<>(Arrays.asList(arr));
        //输出大顶堆
        System.out.println(maxBinaryHeap);

        //添加节点,并且重构大顶堆
        maxBinaryHeap.add(11);
        maxBinaryHeap.add(77);
        //输出大顶堆
        System.out.println(maxBinaryHeap);

        //删除节点,并且重构大顶堆
        //删除失败
        System.out.println(maxBinaryHeap.remove(79));
        //删除成功
        System.out.println(maxBinaryHeap.remove(78));
        //输出大顶堆
        System.out.println(maxBinaryHeap);

        //大顶堆排序(顺序排序)
        System.out.println(Arrays.toString(maxBinaryHeap.heapSort()));
        //输出大顶堆
        System.out.println(maxBinaryHeap);
    }

    /**
     * 通过add方法构建大顶堆
     */
    @Test
    public void testMaxBinaryHeap2() {
        MaxBinaryHeap<Integer> maxBinaryHeap = new MaxBinaryHeap<>();
        maxBinaryHeap.add(49);
        maxBinaryHeap.add(38);
        maxBinaryHeap.add(65);
        maxBinaryHeap.add(97);
        maxBinaryHeap.add(76);
        maxBinaryHeap.add(13);
        maxBinaryHeap.add(27);
        maxBinaryHeap.add(49);
        maxBinaryHeap.add(78);
        //输出大顶堆  [97,78,49,76,65,13,27,38,49]
        System.out.println(maxBinaryHeap);

        //添加节点,并且重构大顶堆
        maxBinaryHeap.add(11);
        maxBinaryHeap.add(77);
        //输出大顶堆
        System.out.println(maxBinaryHeap);

        //删除节点,你
        //删除失败
        System.out.println(maxBinaryHeap.remove(79));
        //删除成功
        System.out.println(maxBinaryHeap.remove(78));
        //输出大顶堆
        System.out.println(maxBinaryHeap);

        //大顶堆排序(顺序排序)
        System.out.println(Arrays.toString(maxBinaryHeap.heapSort()));
        //输出大顶堆
        System.out.println(maxBinaryHeap);
    }

    /**
     * 通过数组在构造器中构建小顶堆
     */
    @Test
    public void testMinBinaryHeap1() {
        Integer[] arr = new Integer[]{49, 38, 65, 97, 76, 13, 27, 49, 78};
        //构建小顶堆
        MinBinaryHeap<Integer> minBinaryHeap = new MinBinaryHeap<>(Arrays.asList(arr));
        //输出小顶堆
        System.out.println(minBinaryHeap);

        //添加节点,并且重构小顶堆
        minBinaryHeap.add(11);
        minBinaryHeap.add(77);
        //输出小顶堆
        System.out.println(minBinaryHeap);

        //删除节点,并且重构小顶堆
        //删除失败
        System.out.println(minBinaryHeap.remove(79));
        //删除成功
        System.out.println(minBinaryHeap.remove(78));
        //输出小顶堆
        System.out.println(minBinaryHeap);

        //小顶堆排序(逆序排序)
        System.out.println(Arrays.toString(minBinaryHeap.heapSort()));
        //输出小顶堆
        System.out.println(minBinaryHeap);
    }

    /**
     * 通过add方法构建小顶堆
     */
    @Test
    public void testMinBinaryHeap2() {
        MinBinaryHeap<Integer> minBinaryHeap = new MinBinaryHeap<>();
        minBinaryHeap.add(49);
        minBinaryHeap.add(38);
        minBinaryHeap.add(65);
        minBinaryHeap.add(97);
        minBinaryHeap.add(76);
        minBinaryHeap.add(13);
        minBinaryHeap.add(27);
        minBinaryHeap.add(49);
        minBinaryHeap.add(78);
        //输出小顶堆
        System.out.println(minBinaryHeap);

        //添加节点,并且重构小顶堆
        minBinaryHeap.add(11);
        minBinaryHeap.add(77);
        //输出小顶堆
        System.out.println(minBinaryHeap);

        //删除节点,并且重构小顶堆
        //删除失败
        System.out.println(minBinaryHeap.remove(79));
        //删除成功
        System.out.println(minBinaryHeap.remove(78));
        //输出小顶堆
        System.out.println(minBinaryHeap);

        //小顶堆排序(逆序排序)
        System.out.println(Arrays.toString(minBinaryHeap.heapSort()));
        //输出小顶堆
        System.out.println(minBinaryHeap);
    }


    /**
     * remove交换节点之后发生的节点小于等于子节点 但是也同时小于父结点的情况
     */
    @Test
    public void testMinBinaryHeap3() {
        MinBinaryHeap<Integer> minBinaryHeap = new MinBinaryHeap<>();
        //添加节点,并且重构小顶堆
        minBinaryHeap.add(0);
        minBinaryHeap.add(25);
        minBinaryHeap.add(1);
        minBinaryHeap.add(30);
        minBinaryHeap.add(35);
        minBinaryHeap.add(7);
        minBinaryHeap.add(3);
        minBinaryHeap.add(40);
        minBinaryHeap.add(45);
        minBinaryHeap.add(50);
        minBinaryHeap.add(55);
        minBinaryHeap.add(8);
        minBinaryHeap.add(9);
        minBinaryHeap.add(15);
        minBinaryHeap.add(16);
        System.out.println(minBinaryHeap);
        System.out.println(Arrays.toString(minBinaryHeap.heapSort()));
        //移除30之后，30和16交换位置，此时16小于等于40 45是成立的，因此没有调整位置
        //但是16却也小于父结点25，因此需要向上构建一次构建
        minBinaryHeap.remove(30);
        System.out.println(minBinaryHeap);
        System.out.println(Arrays.toString(minBinaryHeap.heapSort()));
    }
}
