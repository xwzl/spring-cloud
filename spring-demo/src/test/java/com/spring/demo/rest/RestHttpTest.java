package com.spring.demo.rest;

import com.spring.demo.DemoApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

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
    public void getTest(){
        String prefix = "http://127.0.0.1:11111/rest/get";
        String forObject = restTemplate.getForObject(prefix, String.class);
        String hello_param = restTemplate.getForObject(prefix + "/param", String.class, "hello param");
        String forObject1 = restTemplate.getForObject("http://127.0.0.1:11111/rest/get/entity/11111", String.class, "2222");
    }

}
