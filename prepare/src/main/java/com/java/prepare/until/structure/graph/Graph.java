package com.java.prepare.until.structure.graph;

import lombok.Data;

import java.util.ArrayList;

/**
 * 图对象
 *
 * @author xuweizhi
 * @since 2020/06/02 11:35
 */
@Data
public class Graph {

    /**
     * 存储节点
     */
    public ArrayList<String> vertexList;
    /**
     * 邻接矩阵，用来存储边
     */
    public int[][] edges;
    /**
     * 边的数目
     */
    public int numOfEdges;

    //初始化矩阵
    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdges = 0;
    }

    //得到节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的个数
    public int getNunOfEdges() {
        return numOfEdges;
    }

    //返回两个节点之间的权值
    public int getWeight(String temp1, String temp2) {
        int i = vertexList.indexOf(temp1);
        int j = vertexList.indexOf(temp2);
        return edges[i][j];
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //插入边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    public void deleteEdge(int v1, int v2) {
        edges[v1][v2] = 0;
        numOfEdges--;
    }
}



