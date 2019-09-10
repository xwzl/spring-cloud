package com.spring.demo.untils;

import com.spring.demo.model.dos.Computer;
import com.spring.demo.model.excel.ComputerModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuweizhi
 * @since 2019/09/10 16:27
 */
@Component
public class ListCopy<S, T> {

    /**
     * 复制 list
     *
     * @param source 源对象
     * @param target 目标独享
     */
    public <T> List<T> copyList(List<S> source, Class<T> targetClass) throws NoSuchMethodException, IllegalAccessException, InstantiationException {
        T t;
        List<T> result = new ArrayList<>();
        for (S s : source) {
            targetClass.getDeclaredConstructor().setAccessible(true);
            t = targetClass.newInstance();
            BeanUtils.copyProperties(s, t);
            result.add(t);
        }
        return result;
    }

}
