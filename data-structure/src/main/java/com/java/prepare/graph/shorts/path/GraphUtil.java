package com.java.prepare.graph.shorts.path;


import com.java.prepare.graph.Graph;

/**
 * 矩阵工具
 *
 * @author xuweizhi
 * @since 2020/06/03 16:15
 */
public class GraphUtil {

    public static Graph graphInstance() {
        Graph graph = new Graph(9);
        //插入节点
        graph.insertVertex("V0");
        graph.insertVertex("V1");
        graph.insertVertex("V2");
        graph.insertVertex("V3");
        graph.insertVertex("V4");
        graph.insertVertex("V5");
        graph.insertVertex("V6");
        graph.insertVertex("V7");
        graph.insertVertex("V8");
        int maxVex = graph.vertexList.size();
        //插入边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 5);
        graph.insertEdge(1, 2, 3);
        graph.insertEdge(1, 3, 7);
        graph.insertEdge(1, 4, 5);
        graph.insertEdge(2, 4, 1);
        graph.insertEdge(2, 5, 7);
        graph.insertEdge(3, 4, 2);
        graph.insertEdge(3, 6, 3);
        graph.insertEdge(4, 5, 3);
        graph.insertEdge(4, 6, 6);
        graph.insertEdge(4, 7, 9);
        graph.insertEdge(5, 7, 5);
        graph.insertEdge(6, 7, 2);
        graph.insertEdge(6, 8, 7);
        graph.insertEdge(7, 8, 4);
        //构建邻接矩阵===================================
        for (int i = 0; i < maxVex; i++) {
            for (int j = 0; j < maxVex; j++) {
                System.out.print(graph.edges[i][j] + "\t");
                if (i != j && graph.edges[i][j] == 0) {
                    graph.edges[i][j] = 65535;
                }
            }
            System.out.println();
        }
        return graph;
    }
}
