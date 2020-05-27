package com.spring.common.model.prepare.vos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author xuweizhi
 * @since 2020/05/27 11:00
 */
@Data
@ApiModel("课程 vo")
public class ClassScheduleVO {

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名",required = true)
    @NotEmpty(message = "姓名不能为空")
    private String userName;

    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称",required = true)
    @NotEmpty(message = "课程名称不能为空")
    private String classScheduleName;

}
