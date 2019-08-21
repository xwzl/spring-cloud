package com.spring.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.spring.demo.DemoApplicationTest;
import com.spring.demo.model.OfficeComputer;
import com.spring.demo.service.OfficeComputerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * @author xuweizhi
 * @since 2019-08-21
 */
@Slf4j
public class HyyEmpMapperTest extends DemoApplicationTest {

    @Autowired
    private OfficeComputerMapper officeComputerMapper;

    @Autowired
    private OfficeComputerService officeComputerService;

    @Test
    public void test() {
        OfficeComputer officeComputer = officeComputerMapper.selectOne(new QueryWrapper<OfficeComputer>().lambda().eq(OfficeComputer::getAssetNumber, "主机编号：12001281"));
        System.out.println(officeComputer);
    }

    /**
     * 这是一个比悲伤还悲伤的故事
     */
    @Test
    public void testHaha() {

        OfficeComputer officeComputer = new OfficeComputer().setAssetType("台式机");

        QueryWrapper<OfficeComputer> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(StringUtils.isNotEmpty(officeComputer.getAssetType()), "asset_type", officeComputer.getAssetType());
        queryWrapper.eq(StringUtils.isNotEmpty(officeComputer.getBrand()), "brand", officeComputer.getBrand());

        List<OfficeComputer> list = officeComputerService.list(queryWrapper);

        list.forEach(officeComputer1 -> log.info(officeComputer1.toString()));
    }


}
