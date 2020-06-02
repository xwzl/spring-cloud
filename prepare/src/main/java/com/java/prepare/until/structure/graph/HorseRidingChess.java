package com.java.prepare.until.structure.graph;

import java.awt.*;
import java.util.ArrayList;

/**
 * 马踏棋盘问题的棋盘为 8*8 的方格棋盘，现将马放在任意指定的方格中，按照马走棋的规则将马进行移动。
 * <p>
 * 要求每个方格只能进入一次，最终使得马走遍棋盘 64 个方格
 * <p>
 * 使用回溯（就是深度优先搜索）来解决。
 *
 * <ul>
 *     <li>创建棋盘 chessBoard , 是一个二维数组</li>
 *     <li>将当前位置设置为已经访问，然后根据当前位置，计算马儿还能走哪些位置，并放入到一个集合中(ArrayList), 最多有8个位置， 每走一步，就使用step+1</li>
 *     <li>遍历ArrayList中存放的所有位置，看看哪个可以走通 , 如果走通，就继续，走不通，就回溯.</li>
 *     <li>判断马儿是否完成了任务，使用 step 和应该走的步数比较 ， 如果没有达到数量，则表示没有完成任务，将整个棋盘置0</li>
 *     <li>可使用贪心算法优化</li>
 *     <li>我们获取当前位置，可以走的下一个位置的集合</li>
 *     <li>我们需要对 ps 中所有的Point 的下一步的所有集合的数目，进行非递减排序,就ok。</li>
 * </ul>
 * <p>
 * 注意：马儿不同的走法（策略），会得到不同的结果，效率也会有影响(优化)
 *
 * @author xuweizhi
 * @since 2020/06/01 22:41
 */
public class HorseRidingChess {

    //棋盘的列
    private static int X;
    //棋盘的行
    private static int Y;
    //创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean[] visited;
    //使用一个属性,标记是否期盼的所有位置都被访问
    private static boolean isFinished;

    public static void main(String[] args) {
        System.out.println("骑士周游 - start");

        //行与列
        X = 8;
        Y = 8;
        //马的起始位置,从1开始
        int row = 4;
        int column = 5;
        //创建棋盘
        int[][] chessBoard = new int[X][Y];
        //初始值为false;
        visited = new boolean[X * Y];
        //测试耗时
        long begin = System.currentTimeMillis();
        //进行骑士周游运算
        traversalChessBoard(chessBoard, row - 1, column - 1, 1);

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - begin));

        System.out.println("骑士周游 - end");

        show(chessBoard);
    }

    public static ArrayList<Point> next(Point curPoint) {
        //创建一个集合存储点
        ArrayList<Point> ps = new ArrayList<>();
        //创建一个point
        Point p1 = new Point();
        //表示马可以走 左上偏左 的位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走 左上偏右 的位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走 右上偏左 的位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走 右上偏右 的位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走 右下偏右 的位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //表示马可以走 右下偏左 的位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //表示马可以走 左下偏右 的位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //表示马可以走 左下偏左 的位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    /**
     * 根据当前这一步的所有的下一步的选择位置,进行非递减排序,减少回溯次数
     *
     * @param ps
     */
    public static void
    sort(ArrayList<Point> ps) {
        ps.sort(
                (o1, o2) -> {
                    //获取到o1的下一步的所有位置个数
                    int count1 = next(o1).size();
                    //获取到o2的下一步的所有位置个数
                    int count2 = next(o2).size();
                    if (count1 < count2) {
                        return -1;
                    } else if (count1 == count2) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
        );
    }

    /**
     * 骑士周游算法
     * <p>
     * 其实马踏棋盘的问题很早就有人提出，且早在1823年，J.C.Warnsdorff就提出了一个有名的算法。在每个结点对其子结点进行选取时，优先选择‘出口’最小
     * 的进行搜索，‘出口’的意思是在这些子结点中它们的可行子结点的个数，也就是‘孙子’结点越少的越优先跳，为什么要这样选取，这是一种局部调整最优的做
     * 法，如果优先选择出口多的子结点，那出口少的子结点就会越来越多，很可能出现‘死’结点（顾名思义就是没有出口又没有跳过的结点），这样对下面的搜索
     * 纯粹是徒劳，这样会浪费很多无用的时间，反过来如果每次都优先选择出口少的结点跳，那出口少的结点就会越来越少，这样跳成功的机会就更大一些。 [4]
     * 这种算法称为为贪心算法，也叫贪婪算法或启发式算法，它对整个求解过程的局部做最优调整，它只适用于求较优解或者部分解，而不能求最优解。这样的调
     * 整方法叫贪心策略，至于什么问题需要什么样的贪心策略是不确定的，具体问题具体分析。实验可以证明马遍历问题在运用到了上面的贪心策略之后求解速率
     * 有非常明显的提高，如果只要求出一个解甚至不用回溯就可以完成，因为在这个算法提出的时候世界上还没有计算机，这种方法完全可以用手工求出解来，其
     * 效率可想而知。 [4]
     *
     * @param chessboard 棋盘
     * @param row        马的当前行 从0开始
     * @param column     马的当前列 从0开始
     * @param step       第几步,从1开始
     */
    public static void traversalChessBoard(int[][] chessboard, int row, int column, int step) {
        //获取当前位置
        chessboard[row][column] = step;
        //标记当前位置为已访问,1-64
        visited[row * X + column] = true;
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        //贪心算法优化，对ps进行优化排序
        sort(ps);
        //开始遍历ps
        while (!ps.isEmpty()) {
            //取出下一个可以移动的位置
            Point p = ps.remove(0);
            //判断当前点是否已经访问过
            if (!visited[p.y * X + p.x]) {
                //说明没有访问过
                traversalChessBoard(chessboard, p.y, p.x, step + 1);
            }
        }
        //判断马是否走完了所有位置,使用step和应走的步数比较
        if (step < X * Y && !isFinished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            isFinished = true;
        }
    }

    public static void show(int[][] chessBoard) {
        for (int[] rows : chessBoard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }


}
