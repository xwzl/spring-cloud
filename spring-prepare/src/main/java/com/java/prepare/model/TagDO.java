package com.java.prepare.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.spring.common.model.model.dos.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 兴趣标签表
 *
 * @author 徐伟智
 * @since 2019-12-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder(toBuilder = true)
@TableName("mb_tag")
public class TagDO extends BaseDO<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 标签
     */
    private String tag;

    /**
     * 标签地址
     */
    private String imgUrl;

    private Long parentId;

    private Long searchId;

    private Integer rank;

    private String other;

}
