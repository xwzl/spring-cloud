package com.java.prepare.service;

import com.java.prepare.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.common.model.common.ApiResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuweizhi
 * @since 2020-05-25
 */
public interface UserService extends IService<User> {

    ApiResult<String> initData();

    ApiResult<List<User>> search(String keyWord);

    ApiResult<List<User>> searchUser(User user);
}
