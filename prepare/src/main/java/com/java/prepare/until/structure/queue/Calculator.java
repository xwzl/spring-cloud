package com.java.prepare.until.structure.queue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * 计算器类
 *
 * @author xuweizhi
 * @since 2020/05/26 21:25
 */
public class Calculator {

    /**
     * 存放算术表达式
     */
    private String expression;

    /**
     * 存储运算符优先级，value越大代表优先级越大
     */
    private Map<String, Integer> map = new HashMap<>();

    /**
     * 构造函数
     *
     * @param expression 算术表达式
     */
    public Calculator(String expression) {
        map.put("+", 0);
        map.put("-", 0);
        map.put("*", 1);
        map.put("/", 1);
        map.put("(", -1);
        map.put(")", -1);
        map.put("#", -2);
        this.expression = expression;
    }

    /**
     * 表达式合法性检验
     * <p>
     * 回到check()方法，我们先考虑括号必须成对出现的规则。这里我们可以使用多种方法来检验。
     * <p>
     * 方法一：
     * <p>
     * 我们可以设置一个计数器且初值为0，在遍历数组时，每当读取到(，计数器+1；读取到)，计数器-1。
     * 任何时候计数器的值都不能小于0，且遍历结束后计数器的值必须也随即归0，否则代表括号未成对出现，即表达式不合法。
     * <p>
     * 方法二：
     * <p>
     * 我们可以使用栈(Stack)来解决该问题。在遍历数组时，每当读取到(，将其压入栈；每当读到)，将栈中的(弹出栈。
     * 如果当读取到)时栈为空，或遍历结束后栈未空，代表括号未成对出现，即表达式不合法。
     */
    private void check() throws FormatException {
        // 使用Stack变量来存储左右括号的出现
        Stack<Character> brackets = new Stack<>();
        // 上一个出现的字符，不含括号
        char lastChar = ' ';
        // 将算术表达式转换成字符数组
        char[] chars = expression.toCharArray();
        // 循环遍历整个字符数组
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '(':
                    // 遇到`(`压入栈
                    brackets.push(chars[i]);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    // 处理运算符出现位置
                    dealOperatorAppears(chars, i);
                    // 存储这次读取到的运算符
                    lastChar = chars[i];
                    break;

                case '0':
                    // 关于这条规则，我们可以引入一个局部变量lastChar，用来存储上一个出现的字符，不包括左右括号。如果循环遍历到字符0时，
                    // 程序即开始检验lastChar中的值是否为/，若是则抛出异常，即除数不能为0。解决 / 后为 0 的情况
                    if (lastChar == '/') {
                        try {
                            throw new FormatException("除数不能为0");
                        } catch (FormatException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case ')':
                    // 遇到`)`弹出栈，若栈空，则代表括号未成对出现，抛出异常;解决括号成对出现的问题
                    if (brackets.empty() || brackets.pop() != '(') {
                        throw new FormatException("算术表达式不合法");
                    }
                    break;
                default:
                    // 存储这次读取到的运算符
                    lastChar = chars[i];
                    break;
            }
        }
        // 若遍历结束后，栈未空，则代表括号未成对出现，抛出异常
        if (!brackets.empty()) {
            throw new FormatException("算术表达式不合法");
        }
    }

    /**
     * 运算符出现位置
     * 根据规则：
     * <p>
     * 运算符不能出现在表达式开始或末尾，也不能出现在左括号的右侧或右括号的左侧
     * 运算符不能连续出现
     * <p>
     * 我们可以利用下标，在循环遍历的switch分支中便捷地检验上述情况。
     *
     * @param chars 表达式
     * @param i     当前游标
     * @throws FormatException 异常
     */
    private void dealOperatorAppears(char[] chars, int i) throws FormatException {
        // 存储这次读取到的运算符
        // 如果运算符出现在开头或末尾，抛出异常
        if (i == 0 || i == chars.length - 1) {
            throw new FormatException("算术表达式不合法");
        }
        // 如果运算符的前一位是运算符或左括号(右括号)，抛出异常
        if (chars[i - 1] == '+' || chars[i - 1] == '-' || chars[i - 1] == '*' || chars[i - 1] == '/'
                || chars[i - 1] == '(' ) {
            throw new FormatException("算术表达式不合法:" + chars[i - 1] + "/");
        }
        // 如果运算符的后一位是运算符或右括号，抛出异常
        if (chars[i + 1] == '+' || chars[i + 1] == '-' || chars[i + 1] == '*' || chars[i + 1] == '/'
                || chars[i + 1] == '(' ) {
            throw new FormatException("算术表达式不合法" + chars[i + 1] + "/");
        }
    }

    /**
     * 转化为逆波兰式
     *
     * @return 存储在Stack中的逆波兰表达式
     */
    private Stack<String> convert2RPN() {
        // 声明栈S1
        Stack<String> s1 = new Stack<>();
        // 声明栈S2
        Stack<String> s2 = new Stack<>();
        // 将最低优先级的#符号放入S1栈，为了方便统一后续操作
        s1.push("#");
        // 将算术表达式转换成字符数组
        char[] chars = expression.toCharArray();
        // 循环遍历字符数组
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '(':
                    // 读取到左括号，直接压入S1栈
                    s1.push(chars[i] + "");
                    break;
                case ')':
                    // 若取出的字符是“）”，则将距离S1栈栈顶最近的“（”之间的运算符，逐个出栈，依次送入S2栈，此时抛弃“（”。
                    do {
                        s2.push(s1.pop());
                    } while (!s1.peek().equals("("));
                    s1.pop();
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    //  若取出的字符是运算符，则将该运算符与S1栈栈顶元素比较，
                    // 如果该运算符优先级(不包括括号运算符)大于S1栈栈顶运算符优先级，则将该运算符进S1栈，
                    // 否则，将S1栈的栈顶运算符弹出，送入S2栈中，直至S1栈栈顶运算符低于（不包括等于）该运算符优先级，
                    // 最后将该运算符送入S1栈。
                    if (map.get(String.valueOf(chars[i])) > map.get(s1.peek())) {
                        s1.push(chars[i] + "");
                    } else {
                        do {
                            s2.push(s1.pop());
                        } while (!(map.get(chars[i] + "") > map.get(s1.peek())));
                        s1.push(chars[i] + "");
                    }
                    break;
                default:
                    // 若取出的字符是操作数，则分析出完整的运算数
                    StringBuilder sb = new StringBuilder();
                    // 处理俩位以上的数以及小数的读取
                    while (Character.isDigit(chars[i]) || chars[i] == '.') {
                        sb.append(chars[i]);
                        if (i < chars.length - 1 && (Character.isDigit(chars[i + 1]) || chars[i + 1] == '.')) {
                            i++;
                        } else {
                            break;
                        }
                    }
                    // 该操作数直接送入S2栈
                    s2.push(sb.toString());
                    break;
            }
        }
        // 将S1栈内所有运算符（不包括“#”），逐个出栈，依次送入S2栈。
        while (!s1.peek().equals("#")) {
            s2.push(s1.pop());
        }
        // S2应做一下逆序处理
        Stack<String> stack = new Stack<>();
        while (!s2.empty()) {
            stack.push(s2.pop());
        }
        // 返回S2的逆序栈
        return stack;
    }

    /**
     * 计算结果
     *
     * @return 表达式计算结果
     */
    /**
     * 计算结果
     *
     * @return 表达式计算结果
     * @throws FormatException
     */
    public double calculate() throws FormatException {
        check();
        double result;
        Stack<String> tmp = new Stack<>();
        Stack<String> stack = convert2RPN();
        while (!stack.empty()) {
            String s = stack.pop();
            if (Character.isDigit(s.charAt(0))) {
                tmp.push(s);
            } else {
                double a = Double.parseDouble(tmp.pop());
                double b = Double.parseDouble(tmp.pop());
                switch (s) {
                    case "+":
                        tmp.push(String.valueOf(add(a, b)));
                        break;
                    case "-":
                        tmp.push(String.valueOf(delete(b, a)));
                        break;
                    case "*":
                        tmp.push(String.valueOf(multiply(a, b)));
                        break;
                    case "/":
                        tmp.push(String.valueOf(divide(b, a)));
                        break;
                }
            }
        }
        result = Double.parseDouble(tmp.pop());
        return result;
    }

    private double add(double a, double b) {
        return a + b;
    }

    private double delete(double a, double b) {
        return a - b;
    }

    private double multiply(double a, double b) {
        return a * b;
    }

    private double divide(double a, double b) {
        BigDecimal b1 = new BigDecimal(a);
        BigDecimal b2 = new BigDecimal(b);
        return b1.divide(b2, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public static void main(String[] args) {
        System.out.print("输入算式：");
        Scanner scanner = new Scanner(System.in);
        String express = scanner.nextLine();
        Calculator calculator = new Calculator(express);
        try {
            System.out.println(calculator.calculate());
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }


}
