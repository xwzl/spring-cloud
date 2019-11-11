package com.spring.demo.service.impl;

import com.spring.demo.model.Title;
import com.spring.demo.mapper.TitleMapper;
import com.spring.demo.service.TitleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务实现类
 *
 * @author xuweizhi
 * @since 2019-10-20
 */
@Service
public class TitleServiceImpl extends ServiceImpl<TitleMapper, Title> implements TitleService {

    @Resource
    private TitleMapper titleMapper;

    @Override
    public List<Title> listByIds(List<String> ids) {
        return titleMapper.listByIds(ids);
    }
}
