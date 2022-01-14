package com.spring.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.demo.model.dos.People;

import java.util.List;

/**
 * 服务类
 *
 * @author xuweizhi
 * @since 2019-04-22
 */
public interface PeopleService extends IService<People> {

    /**
     * all data
     *
     * @return data
     */
    List<People> getAll();

}
