package com.spring.redis.config;


import com.spring.redis.annotation.CacheLock;
import com.spring.redis.exception.ServiceException;
import com.spring.redis.until.ContextHolderUtils;
import com.spring.redis.until.know.RedisLockUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.Contract;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * redis aop 拦截
 *
 * @author xuweizhi
 * @since 2018/6/12 0012
 */
@Aspect
@Order(-1)
@Configuration
public class NoRepeatConfig {

    private final RedisLockHelper redisLockHelper;

    private final CacheKeyGenerator cacheKeyGenerator;

    @Contract(pure = true)
    public NoRepeatConfig(RedisLockHelper redisLockHelper, CacheKeyGenerator cacheKeyGenerator) {
        this.redisLockHelper = redisLockHelper;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    @Around("execution(public * *(..)) && @annotation(com.spring.redis.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        HttpServletRequest request = ContextHolderUtils.getRequest();
        // 不拦截 get 请求，幂等操作
        if (request.getMethod().equals(HttpMethod.GET.name())) {
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key don't null...");
        }
        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
        //String value = UUID.randomUUID().toString();
        try {

            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
            //final boolean success = redisLockHelper.lock(lockKey, value, lock.expire(), lock.timeUnit());
            //LockUtil.tryLock(lockKey, lock.expire(), lock.expire(), lock.timeUnit());
            boolean success = RedisLockUtil.tryLock(lockKey, lock.expire(), lock.expire());
            if (!success) {
                throw new ServiceException("重复提交");
            }
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new ServiceException("系统异常");
            }
        } finally {
            // TODO 如果演示的话需要注释该代码;实际应该放开
            //redisLockHelper.unlock(lockKey, value);
            RedisLockUtil.unlock(lockKey);
        }
    }
}
