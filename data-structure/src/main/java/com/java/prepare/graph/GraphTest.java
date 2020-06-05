package com.java.prepare.graph;

import org.junit.Before;
import org.junit.Test;

/**
 * 图相关测试
 *
 * @author xuweizhi
 * @since 2020/06/02 14:47
 */
public class GraphTest {

    private final int SIZE = 9;
    Graph tuGraph = new Graph(SIZE);

    @Before
    public void init() {
        //插入节点
        tuGraph.insertVertex("V0");
        tuGraph.insertVertex("V1");
        tuGraph.insertVertex("V2");
        tuGraph.insertVertex("V3");
        tuGraph.insertVertex("V4");
        tuGraph.insertVertex("V5");
        tuGraph.insertVertex("V6");
        tuGraph.insertVertex("V7");
        tuGraph.insertVertex("V8");
        //插入边
        tuGraph.insertEdge(0, 1, 10);
        tuGraph.insertEdge(0, 5, 11);
        tuGraph.insertEdge(1, 2, 18);
        tuGraph.insertEdge(1, 6, 16);
        tuGraph.insertEdge(1, 8, 12);
        tuGraph.insertEdge(2, 8, 8);
        tuGraph.insertEdge(2, 3, 22);
        tuGraph.insertEdge(3, 6, 24);
        tuGraph.insertEdge(3, 7, 16);
        tuGraph.insertEdge(3, 8, 21);
        tuGraph.insertEdge(3, 4, 20);
        tuGraph.insertEdge(4, 5, 26);
        tuGraph.insertEdge(4, 7, 7);
        tuGraph.insertEdge(5, 6, 17);
        tuGraph.insertEdge(6, 7, 19);

        //构建邻接矩阵===================================
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(tuGraph.edges[i][j] + "\t");
                if (i != j && tuGraph.edges[i][j] == 0) {
                    tuGraph.edges[i][j] = 65535;
                }
            }
            System.out.println();
        }
    }

    @Test
    public void traverse() {

        //深度遍历邻接矩阵===================================
        Traversing depthTraversing = new Traversing();
        String resultStringDFS = depthTraversing.DFS("V1", tuGraph.vertexList, tuGraph.edges);
        System.out.println(resultStringDFS);
        //广度遍历邻接矩阵===================================
        String resultStringBFS = depthTraversing.BFS("V1", tuGraph.vertexList, tuGraph.edges);
        System.out.println(resultStringBFS);
    }

    @Test
    public void prim() {
        PrimArithmetic p = new PrimArithmetic();
        p.minTreePrim(tuGraph);
    }

    @Test
    public void kruskal() {
        Kruskal p = new Kruskal();
        p.kruskal(tuGraph);
    }
}
