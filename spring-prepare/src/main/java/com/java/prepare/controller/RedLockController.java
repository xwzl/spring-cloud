package com.java.prepare.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.spring.common.model.common.ResultVO;
import com.spring.common.model.exception.ServiceException;
import com.spring.common.model.utils.ServiceCodeEnum;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static com.java.prepare.constants.RedisLockConstants.RED_SESSION_LOCK;
import static com.java.prepare.constants.RedisLockConstants.RED_SESSION_NUM;

/**
 * 分布式锁
 *
 * @author xuweizhi
 * @since 2020/05/25 21:16
 */
@Slf4j
@RestController
@ApiOperation("分布式锁")
@RequestMapping("lock")
public class RedLockController {

    @Resource
    private RedissonClient redissonClient;

    private static int stock = 500;

    /**
     * SynchrousQueue:超过核心线程，直接抛出异常
     * ArrayBlockingQueue: maxSize+array.size 最大数量，超出抛异常
     * LinkedBlockingQueue： 无界队列，最大线程数失效
     */
    private final ThreadPoolExecutor executor =
        new ThreadPoolExecutor(10, 20, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("Red Session %d").build(), new ThreadPoolExecutor.AbortPolicy());

    @GetMapping("lock")
    public ResultVO<String> lock() {
        List<Future<ResultVO<String>>> temp = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            temp.add(executor.submit(this::buySomethings));
        }
        temp.forEach(future -> {
            try {
                ResultVO<String> result = future.get();
                log.info(result.toString());
            } catch (InterruptedException | ExecutionException e) {
                log.error(ExceptionUtils.getStackTrace(e) + "线程中断");
            }
        });
        RAtomicLong atomicLong = redissonClient.getAtomicLong(RED_SESSION_NUM);
        atomicLong.set(0);
        return new ResultVO<>("下单成功");
    }

    private ResultVO<String> buySomethings() {
        RLock lock = redissonClient.getLock(RED_SESSION_LOCK);

        boolean result = false;
        // 堆栈内的变量并不影响共享变量的值，共享变量值更新也不会同步到方法栈内的值
        //int temp = RedLockController.stock;
        //log.info("获取锁前的库存值{}", temp);
        try {
            // 尝试加锁,如果被其他线程加锁则等待100s,如果加锁成功10s后自动解锁
            result = lock.tryLock(100, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error(ExceptionUtils.getStackTrace(e) + "线程中断");
        }
        RAtomicLong atomicLong = redissonClient.getAtomicLong(RED_SESSION_NUM);
        if (result) {
            //log.info("获取锁后的库存值{}", temp);
            //log.info("获取锁后静态变量的值{}", RedLockController.stock);
            try {
                if (stock > 0) {
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    int i = random.nextInt(10);
                    Thread.sleep(1000);
                    if (stock < i && stock > 0) {
                        throw new ServiceException(ServiceCodeEnum.FAIL.getCode(), "库存不足,请调整数量后重新购买");
                    }
                    long l = atomicLong.incrementAndGet();
                    stock -= i;
                    log.info("计数：{};扣除{}件库存成功,剩余库存：{}", l, i, stock);
                    return new ResultVO<>("下单成功");
                }
            } catch (InterruptedException e) {
                log.error(ExceptionUtils.getStackTrace(e) + "线程中断");
            } finally {
                // 释放锁
                lock.unlock();
            }
            return new ResultVO(ServiceCodeEnum.FAIL.getCode(), "库存不足,请调整数量后重新购买");
        }
        return new ResultVO(ServiceCodeEnum.FAIL.getCode(), "服务器正在忙碌中，请稍后再试");
    }

}
