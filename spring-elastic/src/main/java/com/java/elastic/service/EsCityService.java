package com.java.elastic.service;

import com.java.elastic.entity.EsCityEntity;

import java.util.ArrayList;

/**
 * ES城市 Service
 *
 * @author xuweizhi
 * @since 2019-11-29
 */
public interface EsCityService {
    /**
     * 保存城市
     *
     * @param entity 实体
     * @return EsCityEntity
     */
    EsCityEntity saveCity(EsCityEntity entity);

    /**
     * 删除城市
     * - 根据ID
     *
     * @param id ID
     */
    void deleteCityById(Long id);

    /**
     * 搜索城市
     * - 根据ID
     *
     * @param id ID
     * @return EsCityEntity
     */
    EsCityEntity searchById(Long id);

    /**
     * 搜索所有的城市
     *
     * @return ArrayList<EsCityEntity>
     */
    ArrayList<EsCityEntity> searchAll();

    /**
     * 搜索城市
     *
     * @param searchParam 搜索参数
     * @return Page<EsCityEntity>
     */
    //Page<EsCityEntity> searchCities(EsSearchParam searchParam);
}
