package com.spring.base.java;

/**
 * 处理字符串
 *
 * @author xuweizhi
 * @since 2019/10/29 15:45
 */
public class SplitStr {

    public final static String SPLIT = "|";
    public final static String SPLIT_MATCHER = ":-------";

    public static void main(String[] args) {

        String[] title = {"重载运算符", "含义"};
        int index = title.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                sb.append(SPLIT).append(title[0]).append(SPLIT).append(title[1]).append(SPLIT + "\n");
            } else {
                sb.append(SPLIT).append(SPLIT_MATCHER).append(SPLIT).append(SPLIT_MATCHER).append(SPLIT + "\n");
            }
        }
        System.out.println(sb.toString());
        String source = "";
        String s = source.replaceAll("\t", SPLIT).replaceAll("\n", "|\n|");
        s = "|" + s + "|\n";
        System.out.println(s);
    }
}
