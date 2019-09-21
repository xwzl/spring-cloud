package com.spring.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.demo.model.dos.Computer;
import com.spring.demo.service.provider.OfficeProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xuweizhi
 * @since 2019-08-05
 */
@Repository
public interface ComputerMapper extends BaseMapper<Computer> {

    /**
     * provider 测试
     *
     * @param assetType 类型
     * @param brand     品牌
     * @return 返回数据
     */
    @SelectProvider(type = OfficeProvider.class, method = "provider")
    List<Computer> getList(@Param("assetType") String assetType, @Param("brand") String brand);

    /**
     * Provider 对象参数测试
     *
     * @param computer 入参
     * @return 集合
     */
    @SelectProvider(type = OfficeProvider.class, method = "list1")
    List<Computer> paramProviderReference(@Param("Computer") Computer computer);

    /**
     * sql 字符串拼接 '' ， 无法回去 '#{keyWord}%' 中 #{keyWord} 数据
     *
     * @param keyWord 关键字
     * @return 数据集
     */
    @Select("select * from computer where brand like CONCAT(#{keyWord},'%')")
    List<Computer> sequenceAppend(String keyWord);
}
