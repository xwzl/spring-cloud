package com.spring.demo.mapper;

import com.spring.demo.DemoApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xuweizhi
 * @since 2019-08-21
 */
public class ComputerMapperTest extends DemoApplicationTest {

    @Autowired
    private ComputerMapper computerMapper;

    @Test
    public void getList() {
        computerMapper.getList("null", "111");
    }

}
