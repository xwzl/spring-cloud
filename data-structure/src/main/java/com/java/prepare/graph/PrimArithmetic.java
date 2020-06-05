package com.java.prepare.graph;

import lombok.Data;

/**
 * 普利姆算法:解决最小生成树的问题
 *
 * @author xuweizhi
 * @since 2020/06/02 14:48
 */
@Data
public class PrimArithmetic {
    /**
     * 通常设置为不可能的大数字
     */
    private final static int INFINITY = 65535;

    /**
     * 生成一颗权值最小的树
     *
     * @param g 图
     */
    public void minTreePrim(Graph g) {
        int min;
        int j;
        int k;
        int sum = 0;
        // 保存相关顶点下标
        int tops = g.vertexList.size();
        int[] adjVex = new int[tops];
        // 保存相关顶点间边的权重
        int[] lowCost = new int[tops];
        // 初始化第一个权值为0,即 vo 加入生成树
        lowCost[0] = 0;
        // 初始化第一个顶点下标为 0
        adjVex[0] = 0;
        for (int q = 0; q < tops; q++) {
            // 将 V0 顶点与之有边的权值存入数组
            lowCost[q] = g.edges[0][q];
            adjVex[q] = 0;
        }
        for (int l = 0; l < tops; l++) {
            min = INFINITY;
            j = 1;
            k = 0; // 存放在节点中的在数组的位置
            // 循环全部顶点,修改 min 为当前 lowCost 数组最小值，k 保留最小值的顶点下标
            while (j < tops) {
                // 如果权值不为 0 且权值小于 min,0 表示已经是生成树的顶点不参与最小权值的查找
                if (lowCost[j] != 0 && lowCost[j] < min) {
                    min = lowCost[j]; // 让当前权值成为最小值
                    k = j;  // 将当前最小值得下标存入 k
                }
                j++;
            }
            // 打印当前定点边中权值最小边，下标到顶点表示最小生成树的第一条边。
            System.out.printf("下标：%d，顶点：%d \n", adjVex[k], k);
            // 将当前顶点的权值设置为 0 ，表示顶点已经完成任务
            lowCost[k] = 0;
            if (min != INFINITY) {
                sum += min;
            }
            // 遍历所有顶点
            for (j = 1; j < tops; j++) {
                // 若下标为 k 顶点各边权值小于此前这些顶点未被加入生成树权值
                if (lowCost[j] != 0 && g.edges[k][j] < lowCost[j]) {
                    // 将较小权值存入 lowCost 中
                    lowCost[j] = g.edges[k][j];
                    // 将下标为 k 的顶点存入 adjVex,当前结点的关联顶点
                    adjVex[j] = k;
                }
            }
        }
        System.out.println(sum + "");
    }


    public static void main(String[] args) {
        int min = Integer.MAX_VALUE;    //定义min变量保存每一个lowcost数组中的最小值，默认为无效权值
        int minId = 0;//定义minId变量保存最小权值的顶点编号
        int sum = 0;//定义sum变量记录总权值
        String[] vertex = new String[]{"A", "B", "C", "D", "E", "F"};    //顶点集
        int[][] matrix = new int[6][];    //邻接矩阵

        //邻接矩阵初始化
        matrix[0] = new int[]{0, 7, 5, 1, min, min};
        matrix[1] = new int[]{7, 0, min, 6, 3, min};
        matrix[2] = new int[]{5, min, 0, 7, min, 2};
        matrix[3] = new int[]{1, 6, 7, 0, 6, 4};
        matrix[4] = new int[]{min, 3, min, 6, 0, 7};
        matrix[5] = new int[]{min, min, 2, 4, 7, 0};

        int vertexSize = vertex.length;//顶点个数
        int lowCost[] = new int[vertexSize];//定义最小代价矩阵
        int adjVex[] = new int[vertexSize];//定义数组保存最小权值的顶点编号

        //lowcost矩阵初始化
        for (int i = 0; i < vertexSize; i++) {
            lowCost[i] = matrix[0][i];
        }

        for (int i = 1; i < vertexSize; i++) {
            min = Integer.MAX_VALUE;
            minId = 0;
            for (int j = 1; j < vertexSize; j++) {
                if (lowCost[j] != 0 && lowCost[j] < min) {//找到lowcost中的最小有效权值
                    min = lowCost[j];//记录最小值
                    minId = j;//记录最小权值的顶点编号
                }
            }
            lowCost[minId] = 0;
            sum += min;
            System.out.println("连接顶点：" + vertex[adjVex[minId]] + " 权值：" + min);
            for (int j = 1; j < vertexSize; j++) {
                //在邻接矩阵中以编号为minId的顶点作为下一个顶点对lowcost中进行最小值替换
                if (lowCost[j] != 0 && matrix[minId][j] < lowCost[j]) {
                    lowCost[j] = matrix[minId][j];
                    adjVex[j] = minId;
                }
            }
        }
        System.out.println("总权值为：" + sum);
    }

}
