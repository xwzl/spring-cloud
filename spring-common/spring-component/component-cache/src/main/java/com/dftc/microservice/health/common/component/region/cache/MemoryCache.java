package com.dftc.microservice.health.common.component.region.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.dftc.microservice.health.common.component.region.RegionConfigurationProperties;
import com.dftc.microservice.health.common.component.region.RegionException;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class MemoryCache {

    private RegionConfigurationProperties conf;
    private LoadingCache<String, String> cache;
    private RedisCache redisCache;

    public MemoryCache(RegionConfigurationProperties regionConfigurationProperties, RedisCache redisCache) {
        this.conf = regionConfigurationProperties;
        this.redisCache = redisCache;
        cache = CacheBuilder.newBuilder().expireAfterWrite(conf.getMemCacheTTL().toMillis(), TimeUnit.MILLISECONDS)
            .maximumSize(conf.getMenCacheMaxSize()).weakKeys().weakValues().build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) {
                    return redisCache.get(key);
                }
            });
    }

    public String get(String key) {
        if (StringUtils.isBlank(key)) {
            throw new RegionException("meet empty key");
        }
        try {
            String value = cache.get(key);
            // Weak Reference Fix
            if (StringUtils.isBlank(value)) {
                value = redisCache.get(key);
                cache.put(key, value);
            }
            return value;
        } catch (ExecutionException e) {
            throw new RegionException("meet exception while try get key", e);
        }
    }
}
