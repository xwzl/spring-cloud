package com.java.prepare.controller;

import com.java.prepare.model.User;
import com.java.prepare.service.UserService;
import com.spring.common.model.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理
 *
 * @author xuweizhi
 * @since 2020-05-25
 */
@Api("用户管理")
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 初始化数据
     */
    @PostMapping("initData")
    @ApiOperation("初始化数据")
    public ApiResult<String> initData() {
        return userService.initData();
    }

    @GetMapping("search")
    @ApiOperation("单个参数测试")
    public ApiResult<List<User>> search(String keyWord){
        return userService.search(keyWord);
    }

    @GetMapping("searchUser")
    @ApiOperation("单个参数测试")
    public ApiResult<List<User>> searchUser(User user){
        return userService.searchUser(user);
    }

}
