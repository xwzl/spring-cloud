package com.spring.base.jdk8;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * bilibi 获取文件缺省项
 *
 * @author xuweizhi
 * @since 2019/12/09 19:47
 */
public class BilibiFileName {

    public static void main(String[] args) {
        String baseUrl = "D:\\Tool\\bilidown\\Download\\2975983";
        File file = new File(baseUrl);
        File[] files = file.listFiles();
        List<Integer> list = new ArrayList<>();
        for (File file1 : files) {
            String name = file1.getName();
            if (name.contains(".")) {
                list.add(Integer.valueOf(name.substring(0, name.indexOf("."))));
            }
        }
        list.stream().sorted().forEach(int1 -> {
                    if (int1 % 10 == 0) {
                        System.out.println("");
                    }
                    System.out.print(int1 + " ");
                }
        );
    }
}
