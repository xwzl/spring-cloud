package com.java.prepare.until.structure.recursive;

import lombok.extern.slf4j.Slf4j;

/**
 * 八皇后问题：
 * <p>
 * 皇后攻击范围左右斜线皆可攻击，8 * 8 格子放置到不能攻击的格子上有多少种放法
 *
 * @author xuweizhi
 * @since 2020/05/29 0:52
 */
@Slf4j
public class EightQueens {

    private static int COUNT = 0;

    /**
     * 皇后/棋盘的个数
     */
    private static final int QUEEN_NUM = 8;

    /**
     * 首先定义一个8 * 8 的棋盘
     */
    private static final int[][] Checkerboard = new int[QUEEN_NUM][QUEEN_NUM];


    /**
     * 初始化棋盘
     */
    public static void init() {
        for (int i = 0; i < QUEEN_NUM; i++) {
            for (int j = 0; j < QUEEN_NUM; j++) {
                Checkerboard[i][j] = 0;
            }
        }
    }


    /**
     * 打印棋盘
     */
    public static void show() {
        System.out.println("第" + (++COUNT) + "次摆法");
        for (int i = 0; i < QUEEN_NUM; i++) {
            for (int j = 0; j < QUEEN_NUM; j++) {
                System.out.print(Checkerboard[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * 判断当前位置是否能放置皇后
     *
     * @param row 行数
     * @param col 列数
     * @return 结果
     */
    public static boolean check(int row, int col) {
        // 判断当前位置的上面是否有皇后
        for (int i = row - 1; i >= 0; i--) {
            if (Checkerboard[i][col] == 1)
                return false;
        }
        // 判断左上是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (Checkerboard[i][j] == 1)
                return false;
        }
        // 判断右上是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < QUEEN_NUM; i--, j++) {
            if (Checkerboard[i][j] == 1)
                return false;
        }
        return true;
    }

    /**
     * 从第n行放置皇后
     *
     * @param row 行数
     */
    public static void play(int row) {
        // 遍历当前行的所有单元格 以列为单元
        for (int i = 0; i < QUEEN_NUM; i++) {
            // 是否能够放置皇后
            if (check(row, i)) {
                Checkerboard[row][i] = 1;
                if (row == QUEEN_NUM - 1) {
                    // 最后行 放置完毕 打印皇后
                    show();
                } else {
                    // 放置下一行
                    play(row + 1);
                }
                //回退到当前步骤，把皇后设置为0
                Checkerboard[row][i] = 0;
            }

        }
    }

    /**
     * row=0;
     * play() 循环是改变皇后的初始位置;
     * 如果满足情况，则放置皇后，猜判当前行是最后一行？
     * 不是再次进入 play 判断下一行是否能放置皇后；
     */
    public static void main(String[] args) {
        play(0);
        System.out.println(COUNT);
    }

}
