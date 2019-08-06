package com.spring.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.demo.model.OfficeComputer;
import com.spring.demo.provider.OfficeProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xuweizhi
 * @since 2019-08-05
 */
public interface OfficeComputerMapper extends BaseMapper<OfficeComputer> {

    /**
     * @param assetType
     * @param brand
     * @return
     */
    @SelectProvider(type = OfficeProvider.class, method = "provider")
    public List<OfficeComputer> getList(@Param("assetType") String assetType, @Param("brand")String brand);
}
