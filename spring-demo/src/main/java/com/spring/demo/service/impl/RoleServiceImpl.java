package com.spring.demo.service.impl;

import com.spring.demo.model.Role;
import com.spring.demo.mapper.RoleMapper;
import com.spring.demo.service.RoleService;
import com.spring.demo.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuweizhi
 * @since 2019-07-25
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

}
