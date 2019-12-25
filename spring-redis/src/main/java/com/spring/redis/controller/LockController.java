package com.spring.redis.controller;

import com.spring.redis.annotation.CacheLock;
import com.spring.redis.annotation.CacheParam;
import com.spring.redis.until.redisson.LockUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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

}
