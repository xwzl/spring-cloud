package com.spring.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.spring.demo.DemoApplicationTest;
import com.spring.demo.model.dos.Computer;
import com.spring.demo.service.ComputerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xuweizhi
 * @since 2019-08-21
 */
@Slf4j
public class EmpMapperTest extends DemoApplicationTest {

    @Autowired
    private ComputerMapper computerMapper;

    @Autowired
    private ComputerService computerService;

    @Test
    public void test() {
        Computer computer = computerMapper.selectOne(new QueryWrapper<Computer>().lambda().eq(Computer::getAssetNumber, "主机编号：12001281"));
        System.out.println(computer);
    }

    /**
     * 这是一个比悲伤还悲伤的故事
     */
    @Test
    public void testHaha() {

        Computer computer = new Computer().setAssetType("台式机");

        QueryWrapper<Computer> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(StringUtils.isNotEmpty(computer.getAssetType()), "asset_type", computer.getAssetType());
        queryWrapper.eq(StringUtils.isNotEmpty(computer.getBrand()), "brand", computer.getBrand());

        List<Computer> list = computerService.list(queryWrapper);

        list.forEach(officeComputer1 -> log.info(officeComputer1.toString()));
    }


}
