package com.spring.demo.config.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncService {

    @Async("asyncExecutor")
    public Future<Integer> methodB(){
        try{
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(1);
    }

    @Async("asyncExecutor")
    public Future<Integer> methodC(){
        try{
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(2);
    }

    @Async("asyncExecutor")
    public Future<Integer> methodD(){
        try{
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(3);
    }
}