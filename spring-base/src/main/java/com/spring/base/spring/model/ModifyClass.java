package com.spring.base.spring.model;

import lombok.Data;

/**
 * 权限修饰符
 *
 * @author xuweizhi
 * @since 2020/01/15 10:39
 */
@Data
public class ModifyClass {

    private int modify;

    public ModifyClass() {
    }

    public ModifyClass(int modify) {
        this.modify = modify;
    }
}
