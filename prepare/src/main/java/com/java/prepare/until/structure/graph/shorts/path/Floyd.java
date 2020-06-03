package com.java.prepare.until.structure.graph.shorts.path;

import com.java.prepare.until.structure.graph.Graph;

/**
 * 佛洛依德
 *
 * @author xuweizhi
 * @since 2020/06/03 16:00
 */
public class Floyd {

    /**
     * 存储各点到各点的下标值
     */
    private final int[][] pathMatrix;

    /**
     * 用于存储到各点最短路径的权值和:表示 0 到 各个顶点的最短路径
     */
    private final int[][] shortPathTable;

    private final Graph graph;

    private final int size;

    public Floyd() {
        graph = GraphUtil.graphInstance();
        size = graph.vertexList.size();
        pathMatrix = new int[size][size];
        shortPathTable = new int[size][size];
    }

    /**
     * 求网图 G 中个顶点 v 到其余各顶点 w 最短庐陵 p[v][w] 及带权长度 d[v][w]
     *
     * @param g 图
     */
    public void shortestPath(Graph g) {
        for (int v = 0; v < size; v++) {
            for (int w = 0; w < size; w++) {
                // 初始化 d[v][w] 值即为对应顶点间的权值
                shortPathTable[v][w] = g.edges[v][w];
                // 初始化 p
                pathMatrix[v][w] = w;
            }
        }

        // 外两层循环顶点位置，内两层循环各顶点到另外顶点的最短距离
        // k 代表的是中转顶的下标
        for (int k = 0; k < size; k++) {
            // v 代表的是始顶点，w 代表的结束顶点
            for (int v = 0; v < size; v++) { // 每个顶点
                for (int w = 0; w < size; w++) { // 每个顶点到其他顶点的最小路径
                    // 如果经过下标为 k 顶点路径比原两点间路径更短
                    // 外层移动 (v,k)
                    //      |\(0,0)
                    // (v,k)| \
                    //      |__\(v,w)
                    //       (k,w)
                    if (shortPathTable[v][w] > shortPathTable[v][k] + shortPathTable[k][w]) {
                        // 将当前两点权值设为更小的一个
                        shortPathTable[v][w] = shortPathTable[v][k] + shortPathTable[k][w];
                        // 路径设置经过下标为 k 的顶点
                        pathMatrix[v][w] = pathMatrix[v][k];
                    }
                }
            }
        }

    }

    /**
     * 抱歉：完全看不懂
     */
    public void printPath() {
        int k = 0;
        for (int v = 0; v < size; v++) {
            for (int w = v + 1; w < size; w++) {
                System.out.printf("v%d-v%d weight:%d", v, w, shortPathTable[v][w]);
                k = pathMatrix[v][w];
                System.out.printf("path:%d", v);
                while (k != w) {
                    System.out.printf(" -> %d", k);
                    k = pathMatrix[k][w];
                }
                System.out.printf(" -> %d\n", w);

            }
            System.out.printf("\n");
        }
    }

    public static void main(String[] args) {
        Floyd floyd = new Floyd();
        floyd.shortestPath(floyd.graph);
        floyd.printPath();
    }
}
