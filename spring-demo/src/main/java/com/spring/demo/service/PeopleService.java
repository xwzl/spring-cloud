package com.spring.demo.service;


import com.spring.demo.model.dos.People;

import java.util.List;

/**
 * 服务类
 *
 * @author xuweizhi
 * @since 2019-04-22
 */
public interface PeopleService extends BaseService<People> {

    /**
     * all data
     *
     * @return data
     */
    List<People> getAll();

}
