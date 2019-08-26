package com.spring.demo.controller;

import com.spring.demo.config.http.HttpApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * httpClient 访问外部资源测试
 * @author xuweizhi
 * @date 2019/08/22 22:21
 */
@RestController
@RequestMapping("httpClient")
public class HttpClientController {

    @Autowired
    private HttpApiService httpApiService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    private void message() {
        String str = null;
        for (int i = 0; i < 2; i++) {
            try {
                str = httpApiService.doGet("http://www.baidu.com");
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }
            System.out.println(i + "; " + str);
        }
    }

    @GetMapping("/exception")
    public String test() throws Exception {

        Map<String, Object> params = new HashMap<>();

        params.put("username", "username");
        params.put("password", "username");
        params.put("age", "7");
        params.put("diy", "username");
        params.put("fix", "username");


        String s1 = httpApiService.doGet("http://localhost:10000/validated", params);

        String s = httpApiService.doGet("http://localhost:10000/exception");

        return "xxx";
    }

}
