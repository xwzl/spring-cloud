package com.dftc.microservice.health.common.component.region.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.constraints.NotNull;

import com.spring.component.json.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.dftc.microservice.health.common.component.region.RegionConfigurationProperties;
import com.dftc.microservice.health.common.component.region.RegionException;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisCache {

    private StringRedisTemplate stringRedisTemplate;
    private RegionConfigurationProperties regionConfigurationProperties;
    private JSON json;
    private Long initTime = 0L;

    public RedisCache(StringRedisTemplate stringRedisTemplate,
        RegionConfigurationProperties regionConfigurationProperties, JSON json) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.regionConfigurationProperties = regionConfigurationProperties;
        this.json = json;
    }

    public String get(String key) {
        if (StringUtils.isBlank(key)) {
            throw new RegionException("meet empty key");
        }
        final String o =
            stringRedisTemplate.<String, String>opsForHash().get(regionConfigurationProperties.getRedisCacheKey(), key);
        if (o == null && System.currentTimeMillis() > initTime
            + regionConfigurationProperties.getRedisCacheRefreshPeriod().toMillis()) {
            try {
                init();
            } catch (IOException e) {
                throw new RegionException("init redis cache error", e);
            }
            return stringRedisTemplate.<String, String>opsForHash()
                .get(regionConfigurationProperties.getRedisCacheKey(), key);
        } else {
            return o;
        }
    }

    private void init() throws IOException {
        URL url = new URL(regionConfigurationProperties.getUrlFormat());
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = url.openStream()) {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(sb::append);
        }
        if (sb.length() == 0) {
            throw new IOException("read empty region data");
        }
        log.warn("read data length {}", sb.length());
        // String jsonStr = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        String jsonStr = sb.toString();
        JsonNode jsonNode = JSON.getMapper().readTree(jsonStr);
        if (!jsonNode.hasNonNull("data") || !jsonNode.get("data").isObject()) {
            throw new IOException("read wrong format region data");
        }
        String key = regionConfigurationProperties.getRedisCacheKey();
        AtomicInteger counter = new AtomicInteger();
        Integer batchSize = regionConfigurationProperties.getRedisPushBatchSize();
        JsonNode data = jsonNode.get("data");
        Iterator<String> names = data.fieldNames();
        while (names.hasNext()) {
            counter.set(0);
            stringRedisTemplate.executePipelined(new SessionCallback<String>() {
                @Override
                public String execute(@NotNull RedisOperations operations) {
                    String next, value;
                    final BoundHashOperations<String, String, String> ops =
                        operations.<String, String>boundHashOps(key);
                    while (names.hasNext() && counter.incrementAndGet() < batchSize) {
                        next = names.next();
                        value = data.get(next).get("name").asText();
                        if (StringUtils.isBlank(value)) {
                            log.warn("meet strange region with id: {}", next);
                            continue;
                        }
                        ops.put(next, value);
                    }
                    return null;
                }
            });
        }
        stringRedisTemplate.expire(key, regionConfigurationProperties.getRedisCacheTTL().toMillis(),
            TimeUnit.MILLISECONDS);
        initTime = System.currentTimeMillis();
    }

}
