package com.java.prepare.juc;

import com.java.prepare.java.StringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xuweizhi
 * @since 2020/05/15 22:40
 */
@RestController
@RequestMapping("s")
public class SController {

    @Resource
    private StringService stringService;

    @GetMapping
    public void test() {
        stringService.getT("1");
    }

}
