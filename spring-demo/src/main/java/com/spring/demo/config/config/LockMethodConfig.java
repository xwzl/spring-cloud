package com.spring.demo.config.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.spring.demo.annotation.CacheLock;
import com.spring.demo.config.redis.CacheKeyGenerator;
import com.spring.demo.config.redis.RedisLockHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.Contract;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * redis aop 拦截
 *
 * @author xuweizhi
 * @since 2018/6/12 0012
 */
@Aspect
@Configuration
public class LockMethodConfig {

    private final RedisLockHelper redisLockHelper;

    private final CacheKeyGenerator cacheKeyGenerator;

    @Contract(pure = true)
    public LockMethodConfig(RedisLockHelper redisLockHelper, CacheKeyGenerator cacheKeyGenerator) {
        this.redisLockHelper = redisLockHelper;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }


    @Around("execution(public * *(..)) && @annotation(com.spring.demo.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key don't null...");
        }
        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
        String value = UUID.randomUUID().toString();
        try {
            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
            final boolean success = redisLockHelper.lock(lockKey, value, lock.expire(), lock.timeUnit());
            if (!success) {
                throw new RuntimeException("重复提交");
            }
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
            }
        } finally {
            // TODO 如果演示的话需要注释该代码;实际应该放开
            redisLockHelper.unlock(lockKey, value);
        }
    }
}
