package com.java.prepare.constants;

/**
 * Redis 锁
 *
 * @author xuweizhi
 * @since 2020/05/25 21:27
 */
public interface RedisLockConstants {

    /**
     * Redis 锁测试
     */
    String RED_SESSION_LOCK = "red:session:lock";

    /**
     * 计数
     */
    String RED_SESSION_NUM = "red:session:num";

}
