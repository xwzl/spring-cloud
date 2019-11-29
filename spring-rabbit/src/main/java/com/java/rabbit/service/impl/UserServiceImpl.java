package com.java.rabbit.service.impl;

import com.java.rabbit.model.User;
import com.java.rabbit.mapper.UserMapper;
import com.java.rabbit.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuweizhi
 * @since 2019-07-16
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

}
