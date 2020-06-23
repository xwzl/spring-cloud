package com.java.prepare.until.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import com.spring.common.model.common.ApiResult;
import com.spring.common.model.exception.ServiceException;
import com.spring.common.model.utils.ServiceCodeEnum;

/**
 * @author xuweizhi
 * @since 2020/05/26 9:49
 */
@Slf4j
public class JdkLock {

    static int stock = 500;

    private Object lockObj = new Object();

    private AtomicInteger atomicInteger = new AtomicInteger();

    private ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("Red Session %d").build(), new ThreadPoolExecutor.AbortPolicy());
        JdkLock jdkLock = new JdkLock();

        List<Future<ApiResult<String>>> temp = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            temp.add(executor.submit(jdkLock::jdkTryLock));
        }
        temp.forEach(future -> {
            try {
                ApiResult<String> result = future.get();
                log.info(result.toString());
            } catch (InterruptedException | ExecutionException e) {
                log.error("获取锁失败，当前线程执行的任务将被丢弃");
            }
        });
        executor.shutdown();
    }

    private ApiResult<String> jdkLock() {
        synchronized (lockObj) {
            try {
                if (stock > 0) {
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    int i = random.nextInt(10);
                    Thread.sleep(1000);
                    if (stock < i && stock > 0) {
                        throw new ServiceException(ServiceCodeEnum.FAIL.getCode(), "库存不足,请调整数量后重新购买");
                    }
                    stock -= i;
                    log.info("计数：{};扣除{}件库存成功,剩余库存：{}", atomicInteger.incrementAndGet(), i, stock);
                    return new ApiResult<>("下单成功");
                }
            } catch (InterruptedException e) {
                log.error(ExceptionUtils.getStackTrace(e) + "线程中断");
            }

        }
        log.info("获取锁失败,请稍后重试");
        return new ApiResult(ServiceCodeEnum.FAIL.getCode(), "服务器正在忙碌中，请稍后再试");
    }

    private ApiResult<String> jdkReenLock() {
        boolean b = false;
        try {
            // 10 钟内尝试获取锁，如果失败则抛出中断异常
            b = lock.tryLock(10, TimeUnit.SECONDS);
            // b = lock.tryLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (b) {
            try {
                if (stock > 0) {
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    int i = random.nextInt(10);
                    Thread.sleep(1000);
                    if (stock < i && stock > 0) {
                        throw new ServiceException(ServiceCodeEnum.FAIL.getCode(), "库存不足,请调整数量后重新购买");
                    }
                    stock -= i;
                    log.info("计数：{};扣除{}件库存成功,剩余库存：{}", atomicInteger.incrementAndGet(), i, stock);
                    return new ApiResult<>("下单成功");
                }
            } catch (InterruptedException e) {
                log.error(ExceptionUtils.getStackTrace(e) + "线程中断");
            } finally {
                lock.unlock();
            }

        }
        log.info("获取锁失败,请稍后重试");
        return new ApiResult(ServiceCodeEnum.FAIL.getCode(), "服务器正在忙碌中，请稍后再试");
    }

    private ApiResult<String> jdkTryLock() {
        int index = 0;
        // 自旋锁
        for (;;) {
            // 由于该锁的特性,没有获取锁获取会立即执行后面的代码？
            boolean b = lock.tryLock();
            log.info("获取锁的状态{}", b);
            if (b) {
                try {
                    if (stock > 0) {
                        ThreadLocalRandom random = ThreadLocalRandom.current();
                        int i = random.nextInt(10);
                        Thread.sleep(100);
                        if (stock < i && stock > 0) {
                            throw new ServiceException(ServiceCodeEnum.FAIL.getCode(), "库存不足,请调整数量后重新购买");
                        }
                        stock -= i;
                        log.info("计数：{};扣除{}件库存成功,剩余库存：{}", atomicInteger.incrementAndGet(), i, stock);
                        return new ApiResult<>("下单成功");
                    }
                } catch (InterruptedException e) {
                    log.error(ExceptionUtils.getStackTrace(e) + "线程中断");
                } finally {
                    lock.unlock();
                }

            }
            log.info("获取锁失败,请重试{}次", index);
            if (++index > 20) {
                return new ApiResult(ServiceCodeEnum.FAIL.getCode(), "下单成功");
            }
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
