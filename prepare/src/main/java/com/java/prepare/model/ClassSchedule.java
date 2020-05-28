package com.java.prepare.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.spring.common.model.model.dos.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 *
 *
 * @author xuweizhi
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("class_schedule")
public class ClassSchedule extends BaseDO<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 课程名称
     */
    @NotBlank(message = "课程名不能为空")
    private String scheduleName;

    /**
     * 老师
     */
    @NotBlank(message = "教师不能为空")
    private String teacher;

    /**
     * 课程安排
     */
    @NotBlank(message = "课程安排不能为空")
    private String arrangement;

    /**
     * 结束时间
     */
    private Date endTime;

}
