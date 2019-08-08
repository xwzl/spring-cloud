package com.spring.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.demo.mapper.HyyEmpMapper;
import com.spring.demo.mapper.OfficeComputerMapper;
import com.spring.demo.model.HyyEmp;
import com.spring.demo.model.OfficeComputer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xuweizhi
 * @since 2019-08-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

    @Autowired
    private OfficeComputerMapper officeComputerMapper;

    @Test
    public void test() {
        OfficeComputer officeComputer = officeComputerMapper.selectOne(new QueryWrapper<OfficeComputer>().lambda().eq(OfficeComputer::getAssetNumber, "主机编号：12001281"));
        System.out.println(officeComputer);
    }

}
