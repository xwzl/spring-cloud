package com.spring.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.demo.model.Title;

import java.util.List;

/**
 * 服务类
 *
 * @author xuweizhi
 * @since 2019-10-20
 */
public interface TitleService extends IService<Title> {

    /**
     * 查询
     *
     * @param ids id
     * @return 返回值
     */
    List<Title> listByIds(List<String> ids);
}
