package com.java.prepare.until.structure.recursive;

import lombok.extern.slf4j.Slf4j;

/**
 * 八皇后问题：
 *
 * 皇后攻击范围左右斜线皆可攻击，8 * 8 格子放置到不能攻击的格子上有多少种放法
 *
 * @author xuweizhi
 * @since 2020/05/29 0:52
 */
@Slf4j
public class EightQueens {

    int count = 0;

    /**
     * 初始化棋盘
     */
    public int[][] init() {
        int[][] temp = new int[8][];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                temp[i][j] = 0;
            }
        }
        return temp;
    }

    /**
     * 八皇后
     *
     * @param row
     *            起始行
     * @param n
     *            列
     * @param input
     *            输入
     */
    public void eight(int row, int n, int[][] input) {
        int[][] temp = new int[8][];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                temp[i][j] = input[i][j];
            }
        }
        if (row == 8) {
            log.info("第 {} 种", count);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.printf("{}", temp[i][j]);
                }
            }
            count++;
        }else {
            for (int i = 0; i < n; i++) {
                //if()
            }
        }
    }
}
