package com.spring.demo.rest;

import com.spring.demo.DemoApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuweizhi
 * @since 2019/09/17 22:32
 */
public class RestHttpTest extends DemoApplicationTest {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 服务必须打开
     */
    @Test
    public void getTest() {
        String prefix = "http://127.0.0.1:11111/rest/get";
        String forObject = restTemplate.getForObject(prefix, String.class);
        String hello_param = restTemplate.getForObject(prefix + "/param", String.class);
        String forObject1 = restTemplate.getForObject("http://127.0.0.1:11111/rest/get/entity/11111", String.class, "2222");
    }

    @Test
    public void getHeaders() {
        String url = "http://127.0.0.1:11111/rest/header";
        HttpHeaders headers = new HttpHeaders();
        headers.add("test", "这是一个test value");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String body = exchange.getBody();
        System.out.println(body);
    }

    @Test
    public void rangLetterTest() {
        String url = "http://a1.easemob.com/easemob-demo/testapp/audio/IM_X364829524644331520C14";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer YWMte3bGuOukEeiTkNP4grL7iwAAAAAAAAAAAAAAAAAAAAGL4CTw6XgR6LaXXVmNX4QCAgMAAAFnKdc-ZgBPGgBFTrLhhyK8woMEI005emtrLJFJV6aoxsZSioSIZkr5kw");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
        Object body = exchange.getBody();
        System.out.println(body.toString());
    }

}
