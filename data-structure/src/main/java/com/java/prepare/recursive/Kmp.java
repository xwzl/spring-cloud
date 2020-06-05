package com.java.prepare.recursive;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 符串匹配算法：克努特—莫里斯—普拉特操作（简称KMP算法）
 *
 * @author xuweizhi
 * @since 2020/05/29 19:01
 */
@Slf4j
public class Kmp {


    /**
     * 返回子串的中索引： -1 未匹配到相应子串
     *
     * @param source 原字符串
     * @param target 子串
     * @return 子串索引
     */
    public int violentMatch(String source, String target) {
        char[] sLen = source.toCharArray();
        char[] tLen = target.toCharArray();
        int i = 0;
        int j = 0;

        while (i < source.length() && j < target.length()) {
            if (sLen[i] == tLen[j]) {
                i++;
                j++;
            } else {
                // 回溯，从下一个字符串开始匹配
                i = i - j + 1;
                // target 重新匹配
                j = 0;
            }
        }
        return j == target.length() ? i - j : -1;
    }

    /**
     * kmp
     *
     * @param source 原字符串
     * @param target 子串
     * @return 子串索引
     */
    public int kmpSearch(String source, String target) {
        char[] sLen = source.toCharArray();
        char[] tLen = target.toCharArray();
        int i = 0;
        int j = 0;
        int[] next = new int[tLen.length];
        recursiveNext(tLen, next);
        while (i < source.length() && j < target.length()) {
            // 如果 j == -1 || sLen[i] == tLen[j] 匹配成功，说明 i++ j++
            if (j == -1 || sLen[i] == tLen[j]) {
                i++;
                j++;
            } else {
                // 如果字符匹配失败，则令 i 不变，j = next[j]
                // next[j] 即为 j 所对应的 next 值
                j = next[j];
            }
        }
        return j == target.length() ? i - j : -1;
    }

    /**
     * next 数组有问题
     *
     * @param target 模式串
     * @param next   next 数组
     */
    public void getNext(char[] target, int[] next) {
        int len = target.length;
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < len - 1) {
            // 这里有点复杂，有递归的概念
            if (k == -1 || target[j] == target[k]) {
                k++;
                j++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
    }

    /**
     * 优化 aabb 出现导致匹配失效的情况
     * <p>
     * 比如，如果用之前的next 数组方法求模式串“abab”的next 数组，可得其next 数组为-1 0 0 1（0 0 1 2整体右移一位，初值赋为-1），当
     * 它跟下图中的文本串去匹配的时候，发现b跟c失配，于是模式串右移j - next[j] = 3 - 1 =2位。
     * <p>
     * 右移2位后，b又跟c失配。事实上，因为在上一步的匹配中，已经得知p[3] = b，与s[3] = c失配，而右移两位之后，让p[ next[3] ] = p[1]
     * = b 再跟s[3]匹配时，必然失配。问题出在哪呢？
     * <p>
     * 问题出在不该出现p[j] = p[ next[j] ]。为什么呢？理由是：当p[j] != s[i] 时，下次匹配必然是p[ next [j]] 跟s[i]匹配，如果p[j]
     * = p[ next[j] ]，必然导致后一步匹配失败（因为p[j]已经跟s[i]失配，然后你还用跟p[j]等同的值p[next[j]]去跟s[i]匹配，很显然，必然
     * 失配），所以不能允许p[j] = p[ next[j ]]。如果出现了p[j] = p[ next[j] ]咋办呢？如果出现了，则需要再次递归，即令next[j] =
     * next[ next[j] ]。
     *
     * @param target 模式串
     * @param next   next 数组
     */
    public void recursiveNext(char[] target, int[] next) {
        int len = target.length;
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < len - 1) {
            // 这里有点复杂，有递归的概念
            if (k == -1 || target[j] == target[k]) {
                k++;
                j++;
                if (target[j] != target[k]) {
                    next[j] = k;
                } else {
                    // 因为不能出现 p[j]=p[next[j]],所以出现时需要继续递归
                    // k = next[k] = next[next[k]]
                    next[j] = next[k];
                }
            } else {
                k = next[k];
            }
        }
    }

    @Test
    public void testNext() {
        String value = "ABCDABD";
        int[] next = new int[value.length()];
        getNext(value.toCharArray(), next);
        System.out.println("");
    }

    @Test
    public void testViolentMatch() {
        int you = violentMatch("I love you!", "you");
        log.info(you + "");
        int java = violentMatch("com.java.prepare.until.structure.recursive.Kmp!", "until");
        log.info(java + "");
        int until = kmpSearch("com.java.prepare.until.structure.recursive.Kmp!", "until");
        log.info(until + "");
        log.info("com.java.prepare.until.structure.recursive.Kmp!".indexOf("until") + "");
    }

}
