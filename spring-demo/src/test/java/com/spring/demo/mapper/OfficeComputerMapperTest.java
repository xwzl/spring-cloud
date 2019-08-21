package com.spring.demo.mapper;

import com.spring.demo.DemoApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author xuweizhi
 * @since 2019-08-21
 */
public class OfficeComputerMapperTest extends DemoApplicationTest {

    @Autowired
    private OfficeComputerMapper officeComputerMapper;

    @Test
    public void getList() {
        officeComputerMapper.getList("null", "111");
    }

}
