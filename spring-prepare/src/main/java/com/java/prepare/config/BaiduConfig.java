package com.java.prepare.config;

import com.baidu.aip.ocr.AipOcr;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 百度图片文字转换
 *
 * @author xuweizhi
 * @since 2019/09/20 14:20
 */
@Configuration
@ConfigurationProperties(prefix = "baidu.picture.client")
public class BaiduConfig {

    /**
     * app id
     */
    private String appId;

    /**
     * app key
     */
    private String appKey;

    /**
     * app secretKey
     */
    private String secretKey;

    /**
     * 连接超时时间
     */
    private final static int CONNECTION_TIMEOUT_IN_MILLIS = 2000;

    /**
     * socket 超时时间
     */
    private final static int SOCKET_TIMEOUT_IN_MILLIS = 6000;

    /**
     * 百度图片文字提取客户端
     */
    @Bean
    public AipOcr aipOcr() {
        AipOcr aipOcr = new AipOcr(appId, appKey, secretKey);
        aipOcr.setConnectionTimeoutInMillis(CONNECTION_TIMEOUT_IN_MILLIS);
        aipOcr.setSocketTimeoutInMillis(SOCKET_TIMEOUT_IN_MILLIS);
        return aipOcr;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
