package com.java.prepare.service;

import com.java.prepare.model.ClassSchedule;
import com.java.prepare.model.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.common.model.common.ApiResult;
import com.spring.common.model.prepare.vos.ClassScheduleVO;

import java.util.List;

/**
 *  服务类
 *
 * @author xuweizhi
 * @since 2020-05-25
 */
public interface UserService extends IService<Student> {

    ApiResult<String> initData();

    ApiResult<List<Student>> search(String keyWord);

    ApiResult<List<Student>> searchUser(Student student);

    ApiResult<String> selectClass(ClassScheduleVO scheduleVO);

    ApiResult<String> addClass(ClassSchedule classSchedule);
}
