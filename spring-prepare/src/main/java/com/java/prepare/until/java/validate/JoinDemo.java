package com.java.prepare.until.java.validate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 * @since 2020/07/16 16:15
 */
@Slf4j
public class JoinDemo {

    @Test
    public void testJoin() throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                synchronized ("1") {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("哈罗");
            }, "" + i);
            thread.start();
            list.add(thread);
        }
        for (Thread thread : list) {
            thread.join();
        }

        log.info("结束");
    }
}
