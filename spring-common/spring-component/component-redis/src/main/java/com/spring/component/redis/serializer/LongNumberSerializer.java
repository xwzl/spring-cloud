package com.spring.component.redis.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class LongNumberSerializer implements RedisSerializer<Long> {

    private Charset charset;

    public LongNumberSerializer() {
        charset = StandardCharsets.UTF_8;
    }

    @Override
    public byte[] serialize(Long number) {
        if (number == null) {
            return null;
        }
        return number.toString().getBytes(charset);
    }

    @Override
    public Long deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return Long.valueOf(new String(bytes, charset));
    }
}
