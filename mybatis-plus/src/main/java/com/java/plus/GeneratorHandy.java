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
        handy.setChildModuleName("spring-demo1");
        handy.setBasePackageName("com.spring");
        handy.setAuthor("xuweizhi");
        handy.setUrl("47.105.218.58:3306/wtf");
        handy.setPassword("158262751");
        handy.setUsername("root");

        handy.setBaseColumnList(true);
        handy.setBaseResultMap(true);

        GeneratorUntil.generatorCode(handy);
    }
}
