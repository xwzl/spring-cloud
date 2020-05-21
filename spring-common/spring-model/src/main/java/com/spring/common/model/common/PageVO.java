package com.spring.common.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页")
public class PageVO<T> implements Serializable {
    private static final long serialVersionUID = 913178003194448540L;
    @ApiModelProperty("当前页数")
    private Integer pageNum;
    @ApiModelProperty("总页数")
    private Integer pageSize;
    @ApiModelProperty("总记录数")
    private Long totalNum;
    @ApiModelProperty("集合")
    private List<T> list;
}
