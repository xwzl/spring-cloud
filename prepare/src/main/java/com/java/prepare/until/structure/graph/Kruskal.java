package com.java.prepare.until.structure.graph;

/**
 * 普利姆算法是以某顶点为起点，逐步找各顶点上最小权值的边来构建最小生成树的。
 * <p>
 * 同样的思路，我们也可以直接以边为目标去构建，因为权值在边上，直接去找最小权值的边来构建生成
 * 也是很自然的想法，只不过构建时要考虑是否会形成环路而已。
 * <p>
 * 大话数据结构
 *
 * @author xuweizhi
 * @since 2020/06/02 17:05
 */
public class Kruskal {

    /**
     * 边集数组定义
     */
    static class Edge {
        int begin;
        int end;
        int weight;

        public Edge(int begin, int end, int weight) {
            this.begin = begin;
            this.end = end;
            this.weight = weight;
        }
    }

    /**
     * 生成一个最小权值树
     *
     * @param g 结点
     */
    public void kruskal(Graph g) {
        int i, n, m;
        // 定义边集数组
        int size = g.vertexList.size();
        Edge[] edges = intEdges();
        // 定义一数组来判断边鱼边是否形成环路
        int[] parent = new int[size];
        for (i = 0; i < size; i++) parent[i] = 0;
        for (i = 0; i < size; i++) {
            n = find(parent, edges[i].begin);
            m = find(parent, edges[i].end);
            // 假如 n 与 m 不等，说明此边没有与现有生成树形成环路
            if (n != m) {
                // 将此边的结尾顶点放入下标为起点的 parent 中,表示顶点已经在生成树集合中
                parent[n] = m;
                System.out.printf("(%d,%d) %d \n", edges[i].begin, edges[i].end, edges[i].weight);
            }
        }
    }

    private Edge[] intEdges() {
        Edge[] edges = new Edge[15];
        edges[0] = new Edge(4, 7, 7);
        edges[1] = new Edge(2, 8, 8);
        edges[2] = new Edge(0, 1, 10);
        edges[3] = new Edge(0, 5, 11);
        edges[4] = new Edge(1, 8, 12);
        edges[5] = new Edge(3, 7, 16);
        edges[6] = new Edge(1, 6, 16);
        edges[7] = new Edge(5, 6, 17);
        edges[8] = new Edge(1, 2, 18);
        edges[9] = new Edge(6, 7, 19);
        edges[10] = new Edge(3, 4, 20);
        edges[11] = new Edge(3, 8, 21);
        edges[12] = new Edge(2, 3, 22);
        edges[13] = new Edge(3, 6, 24);
        edges[14] = new Edge(4, 5, 26);
        return edges;
    }

    /**
     * 查找连线顶点的尾部下标
     *
     * @param parent
     * @param f
     * @return
     */
    private int find(int[] parent, int end) {
        while (parent[end] > 0) {
            end = parent[end];
        }
        return end;
    }
}
