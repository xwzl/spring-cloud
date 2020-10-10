package com.spring.component.redis.lock;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotNull;

import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.types.Expiration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuweizhi
 */
@Slf4j
@Getter
public class RedisLock implements Lock {

    private static final String LOCK_PREFIX = "lock:";
    private static final SecureRandom sr = new SecureRandom();
    private static final DefaultRedisScript<Boolean> script = new DefaultRedisScript<>(
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end",
            Boolean.class);

    private final long lockExpireTime;
    private final RedisTemplate<String, String> redisTemplate;
    private final String key;
    private final String value = String.valueOf(sr.nextLong());

    public RedisLock(@NotNull String key, @NotNull RedisTemplate<String, String> redisTemplate) {
        this.key = Objects.requireNonNull(key);
        this.redisTemplate = Objects.requireNonNull(redisTemplate);
        this.lockExpireTime = TimeUnit.SECONDS.toMillis(60L);
    }

    public RedisLock(@NotNull String key, @NotNull RedisTemplate<String, String> redisTemplate, long expireSecond) {
        this.key = Objects.requireNonNull(key);
        this.redisTemplate = Objects.requireNonNull(redisTemplate);
        this.lockExpireTime = TimeUnit.SECONDS.toMillis(expireSecond);
    }

    public RedisLock(@NotNull String key, @NotNull RedisTemplate<String, String> redisTemplate, long expireTime,
                     TimeUnit timeUnit) {
        this.key = Objects.requireNonNull(key);
        this.redisTemplate = Objects.requireNonNull(redisTemplate);
        this.lockExpireTime = timeUnit.toMillis(expireTime);
    }

    @Override
    public boolean tryLock() {
        Boolean b =
                redisTemplate
                        .execute(
                                conn -> conn.set(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8),
                                        Expiration.milliseconds(lockExpireTime), RedisStringCommands.SetOption.SET_IF_ABSENT),
                                true, false);
        return b != null && b;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long end = System.currentTimeMillis() + unit.toMillis(time);
        while (!tryLock()) {
            if (System.currentTimeMillis() > end) {
                return false;
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }
        return true;
    }

    @Override
    public boolean unlock() {
        Boolean b = redisTemplate.execute(script, Collections.singletonList("LOCK_PREFIX + key"), value);
        return b != null && b;
    }

    @Getter
    public enum LockScope {
        CAS_LOGIN("cas:login:");

        private String prefix;

        LockScope(String prefix) {
            this.prefix = prefix;
        }
    }
}
