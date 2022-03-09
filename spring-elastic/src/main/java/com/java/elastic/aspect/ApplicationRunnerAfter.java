package com.java.elastic.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.impl.client.BasicAuthCache;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RestHighLevelClient ElasticSearch Sniff 会用 docker 容器内部的 ip 地址替换掉
 *
 * @author xuweizhi
 * @date 2019/09/25 22:44
 */
@Slf4j
@Order(100)
@Component
public class ApplicationRunnerAfter implements ApplicationRunner {

    @Resource
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RestClient lowLevelClient = client.getLowLevelClient();
        AuthCache authCache = new BasicAuthCache();
        HttpHost http1 = new HttpHost("127.0.0.1", 9200, "http");
        HttpHost http2 = new HttpHost("127.0.0.1", 9201, "http");
        HttpHost http3 = new HttpHost("127.0.0.1", 9202, "http");
        List<Node> http = Arrays.asList(http1, http2, http3).stream().map(Node::new).collect(Collectors.toList());
        lowLevelClient.setNodes(http);
    }
}
