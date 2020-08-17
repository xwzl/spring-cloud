package com.java.prepare.container;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 双端队列
 *
 * @author xuweizhi
 * @since 2020/08/14 17:45
 */
@Slf4j
public class ArrayDoubleQueueTest {
    int[] arrays = new int[10];

    @Before
    public void before() {
        arrays[0] = 1;
        arrays[1] = 2;
        arrays[2] = 3;
        arrays[3] = 4;
        arrays[6] = 5;
        arrays[7] = 6;
        arrays[8] = 7;
        arrays[9] = 8;
    }


    /**
     * jdk 1.8 ArrayDeque 无参构造器，寻找 2 的 n 次方
     */
    @Test
    public void arrayQueueTest() {
        int start = 10;
        for (int i = 0; i < 10; i++) {
            int temp = start;
            // java8 找出 2 的次方
            // 10   1010
            // 1010 0101  => 1111
            temp |= (temp >>> 1);
            log.info("第一次向右位移 1 位，高 2 位变 1:{}", temp);
            temp |= (temp >>> 2);
            log.info("第二次向右位移 2 位，高 4 位变 1:{}", temp);
            temp |= (temp >>> 4);
            log.info("第三次向右位移 4 位，高 8 位变 1:{}", temp);
            temp |= (temp >>> 8);
            log.info("第四次向右位移 8 位，高 16 位变 1:{}", temp);
            temp |= (temp >>> 16);
            log.info("第五次向右位移 16 位，高 32 位变 1:{}", temp);
            start += 100;
            log.info("========================================");
            ClassLoader.getSystemClassLoader();
        }
    }

    /**
     * 扩容条件
     */
    @Test
    public void dequeOverflow() {
        // jdk 1.8
        // if ( (tail = (tail + 1) & (elements.length - 1)) == head)
        // head 始终为 0 , length 始终为 2 的 n 次方
        for (int i = 0; i < 32; i++) {
            int temp = 2 << i;
            log.info("temp = {} :{}", temp, temp & (temp - 1));
        }
        // jdk 14
        // i = tail; modules = length;
        // if (++i >= modulus) i = 0; return i;
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(3);
        for (int i = 0; i < 20; i++) {
            // 每次扩容后 tail 变为 head 值， head 值得增长值为 newCapacity -oldCapacity
            // 每次赋值后，判断当前容量是否到达最大值，并且 tail 值 + 1;
            // 如果到达最大容量后，把容器中 head 后的数据向后偏移 System.arraycopy(es, head,es, head + newSpace,oldCapacity - head)
            // 扩容长度，tail = head,head  = head + newSpace
            // 其实容量：capacity = 4;
            // head =0;tail=0;size=0;
            // 第一次扩容 head=0;tail=3;++tail=4,tail==0; tail == head => true; 进行扩容 capacity < 64 => true ,jump =
            // capacity +=2 => newSpace=capacity=6; head +=newSpace=6;System.arraycopy(es, head,es, head + newSpace,
            // oldCapacity - head)；数组进行偏移；
            // 第二次扩容 head=6;tail=5;++tail=6,tail==6; tail == head = 6 => true; 进行扩容 capacity < 64 => true ,jump =
            // capacity +=2 => newSpace=capacity=12; head +=newSpace=12;System.arraycopy(es, head,es, head + newSpace,
            // oldCapacity - head)；数组进行偏移；
            arrayDeque.addLast(i);
        }
        arrayDeque.remove(1);
    }

    @Test
    public void dequeSearchTest() {
        log.info("{}", doDequeSearchTest(1, 5, 3));
    }

    public boolean doDequeSearchTest(int head, int tail, int input) {
        for (int i = head, to = i <= tail ? tail : arrays.length; ; i = 0, to = tail) {
            for (; i < to; i++) {
                if (arrays[i] == input) {
                    log.info("index：{}", i);
                    return true;
                }
            }
            if (to == tail) break;
        }
        return false;
    }

    @Test
    public void indexOverflow() {
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (isIndexOverIndex(i)) {
                log.info("{}", i);
            }
        }
    }

    public boolean isIndexOverIndex(int oldCapacity) {
        int needed = 1;
        int jump = (oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1);
        return jump < needed || (oldCapacity + jump) - (Integer.MAX_VALUE - 8) > 0;
    }

    @Test
    public void bubble() {
        int[] ints = new int[]{11, 13, 12, 22, 41, 15, 444, 33, 44, 135};
        for (int i = 0; i < 9; i++) {
            for (int j = i; j < 10; j++) {
                if (ints[i] > ints[j]) {
                    //int temp = ints[j];
                    //ints[j] = ints[i];
                    //ints[i] = temp;
                    ints[i] ^= ints[j];
                    ints[j] ^= ints[i];
                    ints[i] ^= ints[j];
                }
            }
        }
        for (int anInt : ints) {
            log.info("{}", anInt);
        }
    }

}
