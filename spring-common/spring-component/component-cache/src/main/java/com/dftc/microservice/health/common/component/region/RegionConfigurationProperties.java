package com.dftc.microservice.health.common.component.region;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "region")
public class RegionConfigurationProperties {
    private String urlFormat = "http://apph5.dftchealth.com/health-paint/jportal/region.json";
    // private String urlFormat = "http://apph5.dftchealth.com/portal/region/area/region-%s-%s.js";
    private String redisCacheKey = "REGION";
    private Duration redisCacheTTL = Duration.ofDays(2L);
    private Duration redisCacheRefreshPeriod = Duration.ofMinutes(30L);
    private Integer redisPushBatchSize = 1000;
    private Duration memCacheTTL = Duration.ofMinutes(30L);
    private Long menCacheMaxSize = 1000L;
}
