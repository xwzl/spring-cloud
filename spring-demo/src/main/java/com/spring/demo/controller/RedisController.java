package com.spring.demo.controller;


import com.spring.demo.model.dos.People;
import com.spring.demo.service.PeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 前端控制器
 *
 * @author xwz
 * @since 2019-04-22
 */
@RestController
@RequestMapping("/user")
@Api(tags = "redis 缓存测试，注解版本")
public class RedisController {

    @Autowired
    private PeopleService peopleService;

    @ApiOperation(value = "Master 插入值", notes = "hello接口")
    @GetMapping("/insertUser")
    public People insertUser() {
        People user = new People(null, "山东", "仁和春天", LocalDateTime.now(), "158262751", "158262751", 2, "王柳");
        return peopleService.insert(user);
    }

    @GetMapping("/update")
    @ApiOperation(value = "update", notes = "更新")
    public People update(String name, Integer id) {
        People byId = peopleService.getById(id);
        byId.setUsername(name);
        return peopleService.update(byId);
    }

    @GetMapping("/getUser")
    @ApiOperation(value = "getUser", notes = "获取")
    public People getUser(Integer id) {
        return peopleService.findById(id);
    }

    @GetMapping("/delete")
    @ApiOperation(value = "delete", notes = "hello接口")
    public void delete(Integer id) {
        People user = new People();
        user.setUId(id);
        peopleService.delete(user);
    }

    @GetMapping("/getPlus")
    @ApiOperation(value = "getPlus", notes = "hello接口")
    public People getPlus(Integer id) {
        return peopleService.getById(id);
    }
}
