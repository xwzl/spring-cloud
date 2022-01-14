package com.spring.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.demo.model.dos.People;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * 服务类
 *
 * @author xuweizhi
 * @since 2019-04-22
 */
public interface PeopleService extends IService<People> {

    /**
     * 删除一条记录，为redis准备
     **/
    default void delete(People people) {
    }

    /**
     * 同上
     **/
    default People update(People t) {
        return null;
    }

    default People findById(Integer id){
        return null;
    }

    /**
     * 同上
     **/
    default List<People> findAll() {
        return null;
    }

    /**
     * 同上
     **/
    default People insert(People t) {
        return null;
    }

    /**
     * all data
     *
     * @return data
     */
    default List<People> getAll() {
        return null;
    }

}
