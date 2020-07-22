package com.java.prepare.turing.linear;

import org.junit.Test;

import java.io.*;
import java.util.Random;

/**
 * 年龄统计测试
 *
 * @author xuweizhi
 * @since 2020/07/23 1:36
 */
public class AgeTxt {

    @Test
    public void writeDate() throws IOException {
        String s = "age.txt";
        s = this.getClass().getResource("/").getPath() + s;
        System.out.println("");
        OutputStreamWriter buff = new OutputStreamWriter(new FileOutputStream(s), "UTF-8");
        BufferedWriter bw = new BufferedWriter(buff);
        int i1 = Integer.MAX_VALUE / 2;
        for (int i = 0; i < i1; i++) {
            int floor = (int) Math.floor(Math.random() * 120);
            bw.write(floor);
            bw.newLine();
        }
        bw.close();
    }

    @Test
    public void writeDate2() throws Exception {
        final String fileName = this.getClass().getResource("/").getPath() + "age1.txt";
        final Random random = new Random();
        BufferedWriter objWriter = null;
        objWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        for (int i = 0; i < 1400000000; i++) {
            int age = Math.abs(random.nextInt()) % 180;
            objWriter.write(age + "\r\n");
        }
        objWriter.flush();
        objWriter.close();

    }

    @Test
    public void readData() throws Exception {
        String str = null;
        String fileName = this.getClass().getResource("/").getPath() + "age1.txt";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");

        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(isr);
        int tot = 0;    //21亿
        int data[] = new int[200];
        while ((str = br.readLine()) != null) {        //一行一行的读 O(n)
            int age = Integer.valueOf(str);
            data[age]++;
            tot++;
        }
        //O(n) 14亿. 100万/秒 *1000 = 10亿 100~1000s之间 => 500s以下 60*8=480s
        System.out.println("总共的数据大小: " + tot);

        for (int i = 0; i < 200; i++) {//下标从0开始的
            System.out.println(i + ":" + data[i]);
        }
        //144239ms => 144s
        System.out.println("计算花费的时间为:" + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    public void testString() {
        // new 出来的变量都存放在堆中，因此地址与常量池中字符串的地址不一样
        String s1 = new String("abc");
        String s2 = "abc";
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);
        // intern 获取池中对象地址
        System.out.println(s1.intern() == s2);
    }

    @Test
    public void testString2() {
        String s1 = "ja";
        String s2 = "va";
        String s3 = "java";
        //java 注意这个+号，java里面重载了+，其实调用了stringBuild，会new对象。
        String s4 = s1 + s2;
        //false
        System.out.println(s3 == s4);
        //true 只是比较值
        System.out.println(s3.equals(s4));

    }
}
