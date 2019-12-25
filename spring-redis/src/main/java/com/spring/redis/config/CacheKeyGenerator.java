package com.spring.redis.config;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * key生成器
 *
 * @author xuweizhi
 * @since 2019/03/22
 */
public interface CacheKeyGenerator {

    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}
