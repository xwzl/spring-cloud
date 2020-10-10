package com.java.prepare.turing;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thinking
 *
 * @author xuweizhi
 * @since 2020/07/21 20:09
 */
@Slf4j
public class Thinking {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Test
    public void testAtomicInteger() throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    for (; ; ) {
                        int i1 = atomicInteger.get();
                        int i2 = atomicInteger.compareAndExchange(i1, i1 + 1);
                        if (i1 == i2) {
                            break;
                        }
                    }

                }
            }).start();
        }
        System.out.println(atomicInteger);
    }


    @Test
    public void test() {
        int x = 10;
        String x1 = String.valueOf(x < 10 ? 8 : 10);
        String x2 = String.valueOf(x < 10 ? 8 : 10.0);
        System.out.println(x1);
        System.out.println(x2);
    }

    /**
     * 是否是二次方
     */
    @Test
    public void quadratic() {
        int index = 0;
        for (int i = 1; i < 1000; i++) {
            int result = i & (i - 1);
            if (result == 0) {
                log.info("次方:{}\t偶数:{}", index++, i);
            }
        }
    }
}
