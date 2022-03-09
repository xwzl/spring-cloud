package com.java.prepare.service;

import com.java.prepare.model.ClassSchedule;
import com.java.prepare.model.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.common.model.common.ResultVO;
import com.spring.common.model.prepare.vos.ClassScheduleVO;

import java.util.List;

/**
 *  服务类
 *
 * @author xuweizhi
 * @since 2020-05-25
 */
public interface UserService extends IService<Student> {

    ResultVO<String> initData();

    ResultVO<List<Student>> search(String keyWord);

    ResultVO<List<Student>> searchUser(Student student);

    ResultVO<String> selectClass(ClassScheduleVO scheduleVO);

    ResultVO<String> addClass(ClassSchedule classSchedule);
}
