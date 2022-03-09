package com.java.elastic.aspect;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author xuweizhi
 */
@Configuration
@SuppressWarnings("all")
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        return RestClients.create(ClientConfiguration.builder()
                .connectedTo("localhost:9200", "localhost:9201", "localhost:9202")
                .withBasicAuth("elastic", "elastic")
                .build()).rest();
    }
}
