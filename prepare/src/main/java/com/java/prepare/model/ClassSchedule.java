package com.java.prepare.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spring.common.model.interceptor.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class ClassSchedule  extends BaseDO<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 课程名称
     */
    @TableField("schedule_name")
    private String scheduleName;

    /**
     * 老师
     */
    @TableField("teacher")
    private String teacher;

    /**
     * 课程安排
     */
    @TableField("arrangement")
    private String arrangement;

}
