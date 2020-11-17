package com.dftc.microservice.health.common.component.region;

import com.dftc.microservice.health.common.component.region.cache.MemoryCache;

public class Region {

    private MemoryCache memoryCache;

    public Region(MemoryCache memoryCache) {
        this.memoryCache = memoryCache;
    }

    public String getName(String id) {
        return memoryCache.get(id);
    }
}
