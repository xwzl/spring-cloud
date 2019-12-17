package com.spring.base.jdk8.json;

import cn.hutool.core.bean.BeanUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 *
 * @author xuweizhi
 * @since 2019/12/10 21:33
 */
public class CopyTest {

    @Test
    public void test() {
        User user = new User();
        user.setUsername("tag");
        List<String> list = new ArrayList<>();
        list.add("tag");
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        System.out.println(userVO);
    }

}
