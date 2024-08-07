package com.spring.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.spring.common.model.common.ResultVO;
import com.spring.demo.model.dos.User;
import com.spring.demo.model.vos.AuthenticationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @since:knife4j-springdoc-openapi-demo
 * @auth <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/8/6 16:53
 */
@RestController
@RequestMapping("body1")
@Tag(name = "aaa参数")
public class Body1Controller {

    @Operation(summary = "我是表单")
    @ApiOperationSupport(order = 10)
    @PostMapping("/user")
    public ResultVO<User> use1111( User user) {
        return null;
    }


    @Operation(summary = "枚举1")
    @ApiOperationSupport(order = 1)
    @PostMapping("/m1")
    public ResponseEntity<AuthenticationRequest> m1(@RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(request);
    }


    public static void main(String[] args) {
        Class<Body1Controller> emrNurseRecordControllerClass = Body1Controller.class;

        Method[] methods = emrNurseRecordControllerClass.getMethods();
        for (Method method : methods) {
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getName());
            }
        }

    }
}
