package com.java.prepare.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.prepare.mapper.ClassScheduleMapper;
import com.java.prepare.mapper.UserClassRelationMapper;
import com.java.prepare.mapper.UserMapper;
import com.java.prepare.model.ClassSchedule;
import com.java.prepare.model.User;
import com.java.prepare.model.UserClassRelation;
import com.java.prepare.service.UserService;
import com.spring.common.model.common.ApiResult;
import com.spring.common.model.prepare.vos.ClassScheduleVO;
import com.spring.common.model.utils.Assert;
import com.spring.common.model.utils.ServiceCodeEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.random;

/**
 * 服务实现类
 *
 * @author xuweizhi
 * @since 2020-05-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserClassRelationMapper userClassRelationMapper;

    @Resource
    private ClassScheduleMapper classScheduleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult<String> initData() {
        List<String> names = Stream.of("Jack", "Lucy", "Tom", "Jay", "MrsLi", "Jerry").collect(Collectors.toList());
        List<String> address = Stream.of("北京市", "成都市", "广州市", "南京市", "西南市", "香港特别行政区").collect(Collectors.toList());
        List<User> userList =
            Stream
                .generate(() -> new User().setAge((int)(random() * 100))
                    .setUserName(names.get((int)(Math.random() * 5))).setAddress(address.get((int)(Math.random() * 5))))
                .limit(5).collect(Collectors.toList());
        return new ApiResult<>(this.saveBatch(userList) ? "成功" : "失败");
    }

    @Override
    public ApiResult<List<User>> search(String keyWord) {
        return new ApiResult<>(this.baseMapper.search(keyWord));
    }

    @Override
    public ApiResult<List<User>> searchUser(User user) {
        return new ApiResult<>(this.baseMapper.searchUser(user));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult<String> selectClass(ClassScheduleVO scheduleVO) {
        User user = this.baseMapper.selectOne(
            Wrappers.<User>query().lambda().select(User::getId).eq(User::getUserName, scheduleVO.getUserName()));
        ClassSchedule classSchedule = classScheduleMapper.selectOne(Wrappers.<ClassSchedule>query().lambda()
            .select(ClassSchedule::getId).eq(ClassSchedule::getScheduleName, scheduleVO.getClassScheduleName()));
        Assert.isNull(user, "找不到正确的用户信息");
        Assert.isNull(classSchedule, "找不到可用的课程信息");
        int insert = userClassRelationMapper
            .insert(new UserClassRelation().setClassId(classSchedule.getId()).setUserId(user.getId()));
        return insert == 0 ? new ApiResult<>(ServiceCodeEnum.FAIL.getCode(), "服务器压力过大，请重试") : new ApiResult<>();
    }
}
