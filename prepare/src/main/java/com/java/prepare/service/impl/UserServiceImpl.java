package com.java.prepare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.prepare.mapper.UserMapper;
import com.java.prepare.model.User;
import com.java.prepare.service.UserService;
import com.spring.common.model.common.ApiResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuweizhi
 * @since 2020-05-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
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
}
