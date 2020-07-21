package com.java.prepare.until.java.idea;

/**
 * idea 快捷键
 *
 * @author xuweizhi
 * @since 2020/07/19 17:17
 */
public interface Maybe {

    void a();

    void b();

    void c();

    default String d(RefactorParameter refatorParameter) {
        return refatorParameter.getA() + refatorParameter.getB() + refatorParameter.getC();
    }

}
