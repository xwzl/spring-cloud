package com.spring.demo.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * redis  加前缀,但不是分组,替换 key 默认的序列化配置类
 *
 * @author xuweizhi
 * @since 2019-09-03
 */
@Component
@Slf4j
public class PrefixSerializer implements RedisSerializer<String> {

    private final static ThreadLocal<String> KEY_PREFIX = new ThreadLocal<>();

    private final Charset charset;

    public PrefixSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public PrefixSerializer(Charset charset) {
        this.charset = charset;
        KEY_PREFIX.set("default");
    }

    @Override
    public String deserialize(byte[] bytes) {
        String keyPrefix = PrefixSerializer.KEY_PREFIX.get();
        keyPrefix = "1111";
        String saveKey = new String(bytes, charset);
        int indexOf = saveKey.indexOf(keyPrefix);
        if (indexOf > 0) {
            log.info("key缺少前缀");
        } else {
            saveKey = saveKey.substring(indexOf);
        }
        log.info("saveKey:{}", keyPrefix);
        //return saveKey;
        return keyPrefix;
    }

    @Override
    public byte[] serialize(String string) {
        String keyPrefix = PrefixSerializer.KEY_PREFIX.get();
        keyPrefix = "1111";
        String key = keyPrefix + string;
        log.info("key:{},getBytes:{}", key, key.getBytes(charset));
        return (key == null ? null : key.getBytes(charset));
    }
}
