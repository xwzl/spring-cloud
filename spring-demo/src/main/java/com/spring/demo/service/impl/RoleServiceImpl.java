package com.spring.demo.service.impl;

import com.spring.demo.model.dos.Role;
import com.spring.demo.mapper.RoleMapper;
import com.spring.demo.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author xuweizhi
 * @since 2019-07-25
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

}
