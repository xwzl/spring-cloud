package com.spring.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.demo.mapper.ComputerMapper;
import com.spring.demo.model.dos.Computer;
import com.spring.demo.service.ComputerService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author xuweizhi
 * @since 2019-08-05
 */
@Service
public class ComputerServiceImpl extends ServiceImpl<ComputerMapper, Computer> implements ComputerService {

}
