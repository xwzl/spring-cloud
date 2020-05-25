package com.java.prepare.base;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * i++
 * <p>
 * i-- 测试
 *
 * @author xuweizhi
 * @since 2020/05/24 21:17
 */
@Slf4j
public class PlusPLus extends Plus {

    public PlusPLus() {
        log.info("当前类无参构造器");
    }

    /**
     * 这里不会调用本类的无参构造器
     */
    public PlusPLus(int i) {
        super(i);
    }

    private int add() {
        return i++;
    }

    private int sub() {
        return i--;
    }

    private int addBefore() {
        return ++i;
    }

    private int subBefore() {
        return --i;
    }

    private void print() {
        log.info("{}", i);
    }


    /**
     * 0 sipush 1000
     * 3 istore_1
     * 4 getstatic #10 <java/lang/System.out>
     * 7 iload_1
     * 8 iinc 1 by 1
     * 11 invokevirtual #11 <java/io/PrintStream.println>
     * 14 getstatic #10 <java/lang/System.out>
     * 17 iinc 1 by 1
     * 20 iload_1
     */
    public static void main(String[] args) {
        int i = 1000;
        // 字节码文件文件中可以看到，
        System.out.println(i++);
        System.out.println(++i);
        i += 5;
        Integer x = 100;
        // 这里涉及装箱与拆箱
        // 34 invokevirtual #12 <java/lang/Integer.intValue>
        // 37 sipush 1000
        // 40 iadd
        // 41 invokestatic #8 <java/lang/Integer.valueOf>
        //  x => 转换成基本类型进行加减后，在调用 valueOf 方法把封装类型转换为基本类型
        x += 1000;
        // 基本类型：没有装箱与拆箱的操作
        i += 1000;
        // 申请 10 然后调用 valueOf 方法装箱为 10
        Integer y = 10;
        // 以下两种方式都是转换成基本类型，在进行加减的
        x += 10;
        x = x + 2000;

        Integer q1 = Integer.valueOf(100);
        Integer q2 = new Integer(1000);

        //x++;
        //++x;
        //PlusPLus plusPLus = new PlusPLus();
        //plusPLus.print();
        //// 先返回给函数，内部加
        //log.info(plusPLus.i++ + "");
        //plusPLus.print();
        //log.info(++plusPLus.i + "");
    }
}

