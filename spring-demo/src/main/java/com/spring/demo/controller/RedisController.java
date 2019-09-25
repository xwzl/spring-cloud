package com.spring.demo.controller;


import com.spring.demo.model.dos.People;
import com.spring.demo.model.vos.ApiResult;
import com.spring.demo.service.PeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * redis 测试
 *
 * @author xwz
 * @since 2019-04-22
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "redis 缓存测试，注解版本")
public class RedisController {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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

    @GetMapping("/string")
    public ApiResult<Object> addString(String key) {
        ValueOperations<String, Object> value = redisTemplate.opsForValue();
        value.set(key, "键值对测试");
        if (value.get(key) != null) {
            log.info(Objects.requireNonNull(value.get(key)).toString());
        }

        // 如果存在这个建，就需要改，不存在就不添加
        value.setIfAbsent(key + 1, "测试");
        log.info(Objects.requireNonNull(value.get(key + 1)).toString());

        value.setIfAbsent(key, "测试");
        log.info(Objects.requireNonNull(value.get(key)).toString());

        value.set(key, 1);
        log.info(Objects.requireNonNull(value.get(key)).toString());

        value.increment(key);
        log.info(Objects.requireNonNull(value.get(key)).toString());
        return null;
    }

    /**
     * setNx 如果存在这个建，就不设置值，并且返回 0
     * <p>
     * setIfPresent:如果 key 对应的值存在，则设置值，并返回 true
     */
    @GetMapping("/setNx")
    public ApiResult<String> setNx(String key) {
        ValueOperations<String, Object> value = redisTemplate.opsForValue();
        Boolean aBoolean1 = value.setIfPresent(key, key);
        log.info("" + aBoolean1);
        Boolean aBoolean = value.setIfPresent(key, key + "1");
        log.info("" + aBoolean);
        Object andSet = value.getAndSet(key, key + "2");
        return new ApiResult<>((String) andSet);
    }
}
