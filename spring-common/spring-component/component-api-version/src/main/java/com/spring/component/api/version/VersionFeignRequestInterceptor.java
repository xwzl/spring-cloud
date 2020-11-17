package com.spring.component.api.version;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class VersionFeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String version = VersionUtil.getCurrentApiVersion();
        if (StringUtils.isNotBlank(version)) {
            requestTemplate.header(VersionContent.VERSION_HEADER_KEY, version);
        }
    }
}
