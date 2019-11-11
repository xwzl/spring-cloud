package com.spring.demo.mapper;

import com.spring.demo.model.Title;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author xuweizhi
 * @since 2019-10-20
 */
public interface TitleMapper extends BaseMapper<Title> {


    /**
     * 查询
     *
     * @param ids id
     * @return 返回值
     */
    @Select("<script> " +
            "select * from title " +
            "where id in " +
            " <foreach collection='ids' item='id' open='(' close=')' separator=','>" +
            "  #{id}" +
            " </foreach>" +
            "</script>")
    List<Title> listByIds(@Param("ids") List<String> ids);

}
