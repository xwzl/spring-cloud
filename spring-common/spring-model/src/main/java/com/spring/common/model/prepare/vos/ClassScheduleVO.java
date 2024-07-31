package com.spring.common.model.prepare.vos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author xuweizhi
 * @since 2020/05/27 11:00
 */
@Data
@Schema(description = "课程 vo")
public class ClassScheduleVO {

    /**
     * 姓名
     */
    @Schema(description = "姓名",required = true)
    @NotEmpty(message = "姓名不能为空")
    private String userName;

    /**
     * 课程名称
     */
    @Schema(description = "课程名称",required = true)
    @NotEmpty(message = "课程名称不能为空")
    private String classScheduleName;

}
