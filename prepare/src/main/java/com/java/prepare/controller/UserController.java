package com.java.prepare.controller;

import com.java.prepare.model.User;
import com.java.prepare.service.UserService;
import com.spring.common.model.common.ApiResult;
import com.spring.common.model.prepare.vos.ClassScheduleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 搜索用户
     */
    @GetMapping("search")
    @ApiOperation("单个参数测试")
    public ApiResult<List<User>> search(String keyWord){
        return userService.search(keyWord);
    }

    /**
     * 搜索用户
     */
    @GetMapping("searchUser")
    @ApiOperation("对象参数测试")
    public ApiResult<List<User>> searchUser(User user){
        return userService.searchUser(user);
    }

    /**
     * 选择课程
     */
    @PostMapping("selectClass")
    @ApiOperation("选择")
    public ApiResult<String> selectClass(@RequestBody @Validated ClassScheduleVO scheduleVO){
        return userService.selectClass(scheduleVO);
    }

    ///**
    // * 添加课程
    // */
    //@PostMapping("")
    //public ApiResult<String>
}
