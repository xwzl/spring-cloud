package com.java.elastic.config;

import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

/**
 * -- ES搜索引擎 Config
 *
 * @author xuweizhi
 * @since 2019-11-29
 */
@Configuration
public class ElasticsearchConfig {
    /**
     * TransportClient
     *
     * @return TransportClient
     * @throws UnknownHostException 未知主机异常
     */
    /*@Bean
    public TransportClient transportClient() throws UnknownHostException {
        return new PreBuiltXPackTransportClient(Settings.builder()
            .put("cluster.name", "es-cluster").put("xpack.security.user", "elastic:123456").build())
            .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.0.10"), 9300))
            .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.0.10"), 9301))
            .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.0.10"), 9302));
    }*/
}
