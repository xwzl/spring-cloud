package com.spring.demo.controller;

import com.google.gson.Gson;
import com.spring.demo.model.vos.DataVO;
import com.spring.demo.untils.ContextHolderUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Rest 请求
 *
 * @author xuweizhi
 * @since 2019/09/17 22:33
 */
@RestController
@RequestMapping("/rest")
@Api(tags = "mock 测试接口模板")
public class RestHttpController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 无参请求
     */
    @GetMapping("/get")
    @ApiOperation("无参请求")
    public String noParam() {
        System.out.println("请求成功");
        return "success";
    }

    /**
     * 单个参数请求
     */
    @GetMapping("/get/param")
    @ApiOperation("单个参数请求")
    public String param(String param) {
        return param;
    }

    /**
     * 实体对象
     */
    @GetMapping("/get/pojo")
    @ApiOperation("实体对象")
    public DataVO pojo(Integer noticeId, String noticeTitle) {
        return DataVO.builder().noticeId(noticeId).noticeTitle(noticeTitle).build();
    }


    /**
     * 返回参数实体
     */
    @GetMapping("/get/entity/{noticeId}")
    @ApiOperation("返回参数实体")
    public DataVO pathEntity(@PathVariable("noticeId") Integer noticeId, String noticeTitle) {
        return DataVO.builder().noticeId(noticeId).noticeTitle(noticeTitle).build();
    }

    /**
     * post 请求,rest 风格放入 body 中
     */
    @PostMapping("/post/entity")
    @ApiOperation("post 请求,rest 风格放入 body 中")
    public DataVO postEntity(@RequestBody DataVO dataVO) {
        return dataVO;
    }

    @GetMapping("/get/test")
    public void getTest() {
        String url = "http://localhost:11111/rest/get";
        Object forObject = restTemplate.getForObject(url, Object.class);
        System.out.println(forObject.toString());
    }

    @GetMapping("/header")
    @ApiOperation("获取 header 信息")
    public String headerEntity() {
        HttpServletRequest request = ContextHolderUtils.getRequest();
        return request.getHeader("test");
    }

    /**
     * curl -X GET -i "http://a1.easemob.com/1106190731040398/tchealth/audio/213H05522QATBNFYAAX400C12186" -H 'Authorization: Bearer YWMtM_jast3DEemvrsmtQvOdFgAAAAAAAAAAAAAAAAAAAAFGU3WrG2ZOMqYyTTK4hrhlAgMAAAFtXJkzzQBPGgDPRrC5uffahOx9_ksLBwOACPZSwWgVLE-yX6rweUoJgg' -H 'Content-Type: application/json'
     * <p>
     * http://docs-im.easemob.com/im/server/ready/user 获取 token
     * <p>
     * http://docs-im.easemob.com/im/server/basics/recordfiledownload 接口调用
     */
    @GetMapping("/rangLetter")
    @ApiOperation("环信接口调用测试")
    public void rangLetterTest() {
        String url = "http://a1.easemob.com/1106190731040398/tchealth/audio/213H05522QATBNFYAAX400C12186";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer YWMtM_jast3DEemvrsmtQvOdFgAAAAAAAAAAAAAAAAAAAAFGU3WrG2ZOMqYyTTK4hrhlAgMAAAFtXJkzzQBPGgDPRrC5uffahOx9_ksLBwOACPZSwWgVLE-yX6rweUoJgg");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
        Object body = exchange.getBody();
        assert body != null;
        Gson gson = new Gson();
        System.out.println(gson.toJson(body));
    }

}
