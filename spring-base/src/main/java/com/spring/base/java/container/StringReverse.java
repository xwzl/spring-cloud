package com.spring.base.java.container;

import lombok.Data;
import org.jetbrains.annotations.Contract;

/**
 * 字符串逆序
 *
 * @author xuweizhi
 * @since 2019/10/29 17:18
 */
@Data
public class StringReverse {

    private String content;

    @Contract(pure = true)
    public StringReverse(String content) {
        this.content = content;
    }

    public String reverse() {
        char[] chars = this.content.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length; i > 0; i--) {
            sb.append(chars[i - 1]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new StringReverse("Hello Java !").reverse());
    }
}
