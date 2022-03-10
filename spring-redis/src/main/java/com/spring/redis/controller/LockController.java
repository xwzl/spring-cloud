package com.spring.redis.controller;

import com.spring.redis.annotation.CacheLock;
import com.spring.redis.annotation.CacheParam;
import com.spring.redis.until.redisson.LockUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 搞
 *
 * @author xuweizhi
 * @since 2019/12/25 15:05
 */
@Api(tags = "锁测试")
@RestController
public class LockController {

    static final String KEY = "LOCK_KEY";

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @ApiOperation("新增 redis key")
    @PostMapping("addRedisKey")
    public void addRedisKey() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();


        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 51, Runtime.getRuntime().availableProcessors() * 10000,
                0, TimeUnit.MINUTES, new ArrayBlockingQueue<>(100));
        for (int j = 0; j < 10000; j++) {
            threadPool.execute(() -> {
                Random random = new Random();
                int nameSpace = random.nextInt(1000000);
                String keyPrefix = "redis:" + nameSpace + ":";
                for (int i = 0; i < 1000; i++) {
                    String key = keyPrefix + i;
                    operations.set(key, random.nextInt(1000000) + "");
                }
            });
        }

    }

    @GetMapping("/test")
    @ApiOperation("锁测试")
    public Object test() {
        //加锁
        LockUtil.lock(KEY, 10);
        try {
            //TODO 处理业务
            System.out.println(" 处理业务。。。");
        } catch (Exception e) {
            //异常处理
        } finally {
            //释放锁
            LockUtil.unlock(KEY);
        }

        return "SUCCESS";
    }

    @PostMapping("lock")
    @ApiOperation("锁测试")
    @CacheLock(prefix = "book")
    public String lock(@RequestBody @CacheParam(name = "lock") String lock) {
        return lock;
    }


    @GetMapping("subscribe")
    @ApiModelProperty("subscribe")
    public String subscribe() {
        redisTemplate.convertAndSend("string-topic", "你好啊");
        return "发送成功";
    }

}
