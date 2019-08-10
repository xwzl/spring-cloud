package com.spring.demo.service.impl;

import com.spring.demo.mapper.HyyEmpMapper;
import com.spring.demo.model.HyyEmp;
import com.spring.demo.service.HyyEmpService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuweizhi
 * @since 2019-08-01
 */
@Service
public class HyyEmpServiceImpl extends BaseServiceImpl<HyyEmpMapper, HyyEmp> implements HyyEmpService {

    @Override
    public void delete(HyyEmp hyyEmp) {
        if (Math.random() > 0.3d) {
            throw new RuntimeException("这是一个异常信息;");
        }
    }
}
