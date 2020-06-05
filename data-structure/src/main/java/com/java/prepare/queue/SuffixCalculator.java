package com.java.prepare.queue;

import cn.hutool.core.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Scanner;
import java.util.Stack;

/**
 * 后缀表达式计算
 *
 * @author xuweizhi
 * @since 2020/05/27 9:29
 */
@Slf4j
public class SuffixCalculator {

    /**
     * 循环结束标志
     */
    private final static String END_SIGN = "#";
    /**
     * 小数点标志
     */
    private final static String POINT = "";

    public static void main(String[] args) {
        // 暂存数字
        Stack<String> tempStack = new Stack<>();
        log.info("请输入逆波兰表达式，数字或者字符用空格作为间隔，以 # 作为结束标志");
        Scanner scanner = new Scanner(System.in);
        // 为什么要加 0 ？ 编码的原因吗？
        String tempChar = scanner.next();
        String tempStr = "";
        double startValue = 0;
        double endValue = 0;
        while (!tempChar.equals(END_SIGN)) {
            int i = 0;
            while (Character.isDigit(tempChar.charAt(0)) || tempChar == "") {
                tempStr = tempStr + tempChar.trim();
                tempChar = scanner.next();
                if (StringUtils.isEmpty(tempChar)) {
                    tempStack.push(tempStr.trim());
                    tempStr="";
                    break;
                }
            }

            switch (tempChar) {
                case "+":
                    startValue = Double.parseDouble(tempStack.pop());
                    endValue = Double.parseDouble(tempStack.pop());
                    tempStack.push(String.valueOf(NumberUtil.add(startValue, endValue)));
                    break;
                case "-":
                    startValue = Double.parseDouble(tempStack.pop());
                    endValue = Double.parseDouble(tempStack.pop());
                    tempStack.push(String.valueOf(NumberUtil.sub(startValue, endValue)));
                    break;
                case "*":
                    startValue = Double.parseDouble(tempStack.pop());
                    endValue = Double.parseDouble(tempStack.pop());
                    tempStack.push(String.valueOf(NumberUtil.mul(startValue, endValue)));
                    break;
                case "/":
                    startValue = Double.parseDouble(tempStack.pop());
                    endValue = Double.parseDouble(tempStack.pop());
                    tempStack.push(String.valueOf(NumberUtil.div(startValue, endValue)));
                    break;
                default:
                    break;
            }
            // 重新输入字符
            tempChar = scanner.next();
        }
        log.info("表达式计算结果为：{}", tempStack.pop());
    }

    @Test
    public void test() {
        int b = 1;
        char b1 = (char)(b + '0');
        System.out.println(Character.isDigit(b1));
    }

}
