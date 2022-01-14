package com.spring.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.demo.mapper.EmpMapper;
import com.spring.demo.model.dos.Emp;
import com.spring.demo.service.EmpService;
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
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

    @Override
    public void delete(Emp emp) {
        if (Math.random() > 0.3d) {
            throw new RuntimeException("这是一个异常信息;");
        }
    }
}
