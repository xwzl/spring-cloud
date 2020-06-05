package com.java.prepare.graph.shorts.path;


import com.java.prepare.graph.Graph;

import java.util.LinkedList;

/**
 * 迪杰特斯拉算法解决了从某个源点到其余各顶点的最短路径问题。
 *
 * @author xuweizhi
 * @since 2020/06/03 10:56
 */
public class Dijkstra {

    /**
     * 顶点数
     */
    private int maxVex;

    /**
     * 最大值
     */
    private int infinity = 65535;

    /**
     * 用于存储最短路径下标的数组了，当前结点为 vo 时，表示 vo 到 v2、v3、v4
     * 点的最短路径它们的前驱均是 v1.
     */
    private int[] pathMatrix;

    /**
     * 用于存储到各点最短路径的权值和:表示 0 到 各个顶点的最短路径
     */
    private int[] shortPathTable;

    public Dijkstra() {

    }

    public Graph create() {
        Graph graph = GraphUtil.graphInstance();
        this.maxVex = graph.vertexList.size();
        this.shortPathTable = new int[maxVex];
        this.pathMatrix = new int[maxVex];
        return graph;
    }

    /**
     * p[v] 的值为前驱顶点下标，d[v]表示 vo 到 v 的最短路径长度和
     *
     * @param g  图
     * @param vo 起点坐标
     */
    public void dijkstraPath(Graph g, int vo) {
        // result[w] = 1 表示求得顶点 vo 到 vw 最短路径和
        int[] result = new int[maxVex];
        for (int i = 0; i < g.vertexList.size(); i++) {
            // 全部顶点初始化为未知最短路径状态
            result[i] = 0;
            // 将与 vo 点有连线的顶点加上权值
            shortPathTable[i] = g.edges[vo][i];
            // 初始化路径数组 p 为 0
            pathMatrix[i] = 0;
        }
        shortPathTable[vo] = 0;// vo 到 vo 的路径为0
        result[vo] = 1; // vo 至 vo 不需要去路径
        int min = 0;
        int k = 0;
        // 开始主循环，每次求得 v0 到某个 v 顶点的最短路径
        for (int i = 0; i < g.vertexList.size(); i++) {
            min = infinity; // 当前所知离 vo 顶点的最近距离
            for (int w = 0; w < g.vertexList.size(); w++) {
                if (result[w] == 0 && shortPathTable[w] < min) {
                    k = w;
                    min = shortPathTable[w];// w 顶点离 v0 顶点更近
                }
            }
            result[k] = 1; // 将目前找到的最近的顶点置为 1

            // 修正当前最短路径及距离:它的目的是在刚才已经找到 vo 与 v1 的最短路径的基础上，
            // 对 v1 与其他顶点的边进行计算，得到vo 与它们的当前最短距离。
            for (int w = 0; w < g.vertexList.size(); w++) {
                // 如果经过 v 顶点的路径比现在这条路径的长度短的话
                if (result[w] == 0 && (min + g.edges[k][w] < shortPathTable[w])) {
                    // 说明找到了更短的路径，修改 d[w] 和 p[w],修改当前路径长度
                    shortPathTable[w] = min + g.edges[k][w];
                    pathMatrix[w] = k;
                }
            }
        }
        int temp = 8;
        LinkedList<String> stack = new LinkedList<>();
        stack.push("" + temp);
        while (true) {
            String format = String.format("%d -> ", pathMatrix[temp], temp);
            stack.push(format);
            temp = pathMatrix[temp];
            if (temp <= 0) {
                break;
            }
        }
        while (true) {
            if (stack.size() <= 0) {
                break;
            }
            String pop = stack.pop();
            System.out.printf(pop);
        }
        System.out.println("\n权值和为：" + shortPathTable[maxVex - 1]);
    }

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        Graph graph = dijkstra.create();
        dijkstra.dijkstraPath(graph, 8);
    }
}
