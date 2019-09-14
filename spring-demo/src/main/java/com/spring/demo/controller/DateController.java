package com.spring.demo.controller;

import com.spring.demo.model.dos.Computer;
import com.spring.demo.model.vos.ApiResult;
import com.spring.demo.model.vos.DateVO;
import com.spring.demo.service.ComputerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 测试时间类型传参
 *
 * @author xuweizhi
 * @since 2019/09/03 11:19
 */
@RestController
@RequestMapping("/date")
@Slf4j
public class DateController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ComputerService computerService;

    @Autowired
    private ValueOperations<String, Object> opsForValue;

    @PostMapping("/mvc")
    public void mvc(@Valid DateVO dateVO) {

        dateVO.setValue("我们是一个好孩子!");

        redisTemplate.opsForValue().set("haha11", dateVO);

        log.info(dateVO.toString());

        DateVO dateVO1 = (DateVO) redisTemplate.opsForValue().get("haha11");

        log.info(Objects.requireNonNull(dateVO1).toString());
    }

    /**
     * redis 中 key 加 ： 相当于创建一层目录
     */
    @GetMapping("/list")
    public ApiResult<List<Computer>> list() {
        List<Computer> list = (List<Computer>) opsForValue.get("fuck_you");
        if (list == null) {
            list = computerService.list();
            //opsForValue.set("fuck_you", list, 100, TimeUnit.SECONDS);
            opsForValue.set("fuck:you:god", list);
        }
        return new ApiResult<>(list);
    }

    /**
     * 巨坑，这个转换器要哪个啥？如果不传参数，必须保证 required 为 false
     */
    @GetMapping("/check")
    public LocalDateTime check(@RequestParam(required = false) LocalDateTime localDateTime) {
        return localDateTime;
    }

}
