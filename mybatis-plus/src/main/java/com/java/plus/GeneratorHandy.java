package com.java.plus;


import com.java.plus.old.MybatisHandy;

/**
 * @author xuweizhi
 * @date 2019/04/03 19:13
 */
public class GeneratorHandy {

    /**
     * https://mp.baomidou.com/config/generator-config.html#drivername
     */
    public static void main(String[] args) {
        MybatisHandy handy = new MybatisHandy();

        //如果是子模块，必须设置
        handy.setChildModule(true);
        handy.setChildModuleName("prepare");
        handy.setBasePackageName("com.java");
        handy.setAuthor("xuweizhi");
        handy.setUrl("172.16.142.128:3306/prepare");
        handy.setPassword("root");
        handy.setUsername("root");

        handy.setBaseColumnList(true);
        handy.setBaseResultMap(true);

        GeneratorUntil.generatorCode(handy);
    }
}
