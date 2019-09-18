package com.spring.demo.mapper;

import com.spring.demo.DemoApplicationTest;
import com.spring.demo.model.dos.Computer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @Test
    public void getList2() {
        List<Computer> list = computerMapper.sequenceAppend("逾");
        list.forEach(System.out::println);
    }

}
