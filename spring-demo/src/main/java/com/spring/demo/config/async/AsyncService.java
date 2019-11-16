package com.spring.demo.config.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author xuweizhi
 */
@Service
public class AsyncService {

    @Async("simpleExecutor")
    public Future<Integer> methodFirst() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(1);
    }

    @Async("simpleExecutor")
    public Future<Integer> methodSecond() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(2);
    }

    @Async("simpleExecutor")
    public Future<Integer> methodThree() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(3);
    }
}
