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

    @Bean("restHighLevelClient")
    @Override
    public RestHighLevelClient elasticsearchClient() {
        return RestClients.create(ClientConfiguration.builder()
                .connectedTo("127.0.0.1:9200", "127.0.0.1:9201", "127.0.0.1:9202")
                .withBasicAuth("elastic", "elastic")
                .build()).rest();
        // HttpHost http = new HttpHost("127.0.0.1", 9200, "http");
        //
        // RestHighLevelClient client = new RestHighLevelClient(
        //         RestClient.builder(
        //                 http,
        //                 new HttpHost("127.0.0.1", 9201, "http"),
        //                 new HttpHost("127.0.0.1", 9202, "http")));
        // // RestClient.builder(
        // //         new HttpHost("192.168.21.133", 9200, "http")));
        // return client;
    }

}
