package com.java.prepare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.prepare.model.TagDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 兴趣标签表
 *
 * @author 徐伟智
 * @since 2019-12-10
 */
public interface TagMapper extends BaseMapper<TagDO> {

    @Select("select * from mb_tag where parent_id is null")
    List<TagDO> parentSelect();


    @Select("select * from mb_tag where parent_id = #{id}")
    List<TagDO> childSelect(@Param("id") Long id);
}
