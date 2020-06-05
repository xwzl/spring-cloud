package com.java.prepare.graph;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 图的遍历
 *
 * @author xuweizhi
 * @since 2020/06/02 14:46
 */
public class Traversing {
    public String DFS(String startNode, ArrayList<String> vertexList, int[][] edges) {
        if (!vertexList.contains(startNode)) {
            System.out.print("输入节点不在该图内");
            return null;
        }
        int startIndex = vertexList.indexOf(startNode);
        int numOfNodes = vertexList.size();
        boolean[] visited = new boolean[numOfNodes];
        StringBuilder resultBuilder = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        stack.push(startIndex);
        visited[startIndex] = true;
        while (!stack.isEmpty()) {
            int v = stack.pop();
            resultBuilder.append(vertexList.get(v) + ",");
            for (int i = 0; i < numOfNodes; i++) {
                //当edges【v】【i】的值不为0，不为最大，且没有被访问时，将其压入栈中
                if ((edges[v][i] != 0) && (edges[v][i] != Integer.MAX_VALUE) && !visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }


        return resultBuilder.length() > 0 ? resultBuilder.substring(0, resultBuilder.length() - 1) : null;
    }

    public String BFS(String startNode, ArrayList<String> vertexList, int[][] edges) {
        if (!vertexList.contains(startNode)) {
            System.out.print("输入节点不在该图内");
            return null;
        }
        StringBuilder resultBuilder = new StringBuilder();
        boolean[] visited = new boolean[vertexList.size()];
        int startIndex = vertexList.indexOf(startNode);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startIndex);
        visited[startIndex] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            resultBuilder.append(vertexList.get(v) + ",");
            for (int i = 0; i < vertexList.size(); i++) {
                if ((edges[v][i] != 0) && (edges[v][i] != Integer.MAX_VALUE) && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }

        }
        return resultBuilder.length() > 0 ? resultBuilder.substring(0, resultBuilder.length() - 1) : null;
    }
}
