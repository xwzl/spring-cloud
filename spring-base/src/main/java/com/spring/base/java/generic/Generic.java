package com.spring.base.java.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Java 泛型设计原则：只要在编译时期没有出现警告，那么运行时期就不会出现ClassCastException异常.
 *
 * 泛型：把类型明确的工作推迟到创建对象或调用方法的时候才去明确的特殊的类型
 *
 * @author xuweizhi
 * @since 2019/12/31 14:01
 */
public class Generic {

    public static void main(String[] args) {
        GenericDemo<String> s = new GenericDemo<>();
    }


    public static class GenericDemo<T>{

        private T t ;

        public GenericDemo() {
            Type genericSuperclass = this.getClass().getGenericSuperclass();
            ParameterizedType typeParameter1 = (ParameterizedType) genericSuperclass;
            Type[] actualTypeArguments = typeParameter1.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument.getTypeName());
            }
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }
}


