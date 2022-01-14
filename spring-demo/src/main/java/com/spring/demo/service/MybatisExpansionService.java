package com.spring.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.demo.model.dos.MybatisExpansion;

/**
 * mybatis plus 扩展测试
 *
 * @author xuweizhi
 * @since 2019-08-07
 */
public interface MybatisExpansionService extends IService<MybatisExpansion> {

    void mybatisDemo();
}
