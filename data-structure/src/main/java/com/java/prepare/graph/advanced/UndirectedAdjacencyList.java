package com.java.prepare.graph.advanced;

import lombok.ToString;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图邻接链表简单实现
 * <p>
 * {@link UndirectedAdjacencyList#UndirectedAdjacencyList(E[], E[][])}  构建无向图
 * {@link UndirectedAdjacencyList#DFS()}  深度优先遍历无向图
 * {@link UndirectedAdjacencyList#BFS()}  广度优先遍历无向图
 * {@link UndirectedAdjacencyList#toString()} ()}  输出无向图
 */
public class UndirectedAdjacencyList<E> {
    /**
     * 顶点类
     *
     * @param <E>
     */
    @ToString
    private static class Node<E> {
        /**
         * 顶点信息
         */
        E data;
        /**
         * 指向第一条依附该顶点的边
         */
        LNode firstEdge;

        public Node(E data, LNode firstEdge) {
            this.data = data;
            this.firstEdge = firstEdge;
        }
    }

    /**
     * 边表节点类
     */
    @ToString
    private static class LNode {
        /**
         * 该边所指向的顶点的索引位置
         */
        int vertex;
        /**
         * 指向下一条边的指针
         */
        LNode nextEdge;
    }

    /**
     * 顶点数组
     */
    private final Node<E>[] vertExs;

    /**
     * 创建图
     *
     * @param vExs  顶点数组
     * @param edges 边二维数组
     */
    public UndirectedAdjacencyList(E[] vExs, E[][] edges) {
        /*初始化顶点数组,并添加顶点*/
        vertExs = new Node[vExs.length];
        for (int i = 0; i < vertExs.length; i++) {
            vertExs[i] = new Node<>(vExs[i], null);
        }
        /*初始化边表,并添加边节点到边表尾部,即采用尾插法*/
        for (E[] edge : edges) {
            // 读取一条边的起始顶点和结束顶点索引值
            int p1 = getPosition(edge[0]);
            int p2 = getPosition(edge[1]);
            /*这里需要相互添加边节点,无向图可以看作相互可达的有向图*/
            // 初始化lNode1边节点
            LNode lNode1 = new LNode();
            lNode1.vertex = p2;
            // 将LNode链接到"p1所在链表的末尾"
            if (vertExs[p1].firstEdge == null) {
                vertExs[p1].firstEdge = lNode1;
            } else {
                linkLast(vertExs[p1].firstEdge, lNode1);
            }
            // 初始化lNode2边节点
            LNode lNode = new LNode();
            lNode.vertex = p1;
            // 将lNode2链接到"p2所在链表的末尾"
            if (vertExs[p2].firstEdge == null) {
                vertExs[p2].firstEdge = lNode;
            } else {
                linkLast(vertExs[p2].firstEdge, lNode);
            }
        }
    }

    /**
     * 获取某条边的某个顶点所在顶点数组的索引位置
     *
     * @param e 顶点的值
     * @return 所在顶点数组的索引位置, 或者-1 - 表示不存在
     */
    private int getPosition(E e) {
        for (int i = 0; i < vertExs.length; i++) {
            if (vertExs[i].data == e) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 将lNode节点链接到边表的最后,采用尾插法
     *
     * @param first 边表头结点
     * @param node  将要添加的节点
     */
    private void linkLast(LNode first, LNode node) {
        while (true) {
            // 结点相同直接返回
            if (first.vertex == node.vertex) {
                return;
            }
            // 访问到最后一个链表结点返回
            if (first.nextEdge == null) {
                break;
            }
            // 赋值
            first = first.nextEdge;
        }
        first.nextEdge = node;
    }

    /**
     * 深度优先搜索遍历图的递归实现,类似于树的先序遍历
     * 因此模仿树的先序遍历,同样借用栈结构,这里使用的是方法的递归,隐式的借用栈
     *
     * @param i       顶点索引
     * @param visited 访问标志数组
     */
    private void DFS(int i, boolean[] visited) {
        //索引索引标记为true ,表示已经访问了
        visited[i] = true;
        System.out.print(vertExs[i].data + " ");
        //获取该顶点的边表头结点
        LNode node = vertExs[i].firstEdge;
        //循环遍历该顶点的邻接点,采用同样的方式递归搜索
        while (node != null) {
            if (!visited[node.vertex]) {
                DFS(node.vertex, visited);
            }
            node = node.nextEdge;
        }
    }

    /**
     * 深度优先搜索遍历图,类似于树的前序遍历,
     */
    public void DFS() {
        //新建顶点访问标记数组,对应每个索引对应相同索引的顶点数组中的顶点
        boolean[] visited = new boolean[vertExs.length];
        //初始化所有顶点都没有被访问
        for (int i = 0; i < vertExs.length; i++) {
            visited[i] = false;
        }
        System.out.println("DFS: ");
        /*循环搜索*/
        for (int i = 0; i < vertExs.length; i++) {
            //如果对应索引的顶点的访问标记为false,则搜索该顶点
            if (!visited[i]) {
                DFS(i, visited);
            }
        }
        /*走到这一步,说明顶点访问标记数组全部为true,说明全部都访问到了,深度搜索结束*/
        System.out.println();
    }

    /**
     * 广度优先搜索图,类似于树的层序遍历
     * 因此模仿树的层序遍历,同样借用队列结构
     */
    public void BFS() {
        // 辅组队列
        Queue<Integer> indexLinkedList = new LinkedList<>();
        //新建顶点访问标记数组,对应每个索引对应相同索引的顶点数组中的顶点
        boolean[] visited = new boolean[vertExs.length];
        //初始化所有顶点都没有被访问
        for (int i = 0; i < vertExs.length; i++) {
            visited[i] = false;
        }
        System.out.println("BFS: ");
        for (int i = 0; i < vertExs.length; i++) {
            //如果访问方剂为false,则设置为true,表示已经访问,然后开始访问
            if (!visited[i]) {
                visited[i] = true;
                System.out.print(vertExs[i].data + " ");
                indexLinkedList.add(i);
            }
            //判断队列是否有值,有就开始遍历
            if (!indexLinkedList.isEmpty()) {
                //出队列
                Integer j = indexLinkedList.poll();
                LNode node = vertExs[j].firstEdge;
                while (node != null) {
                    int k = node.vertex;
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.print(vertExs[k].data + " ");
                        //继续入队列
                        indexLinkedList.add(k);
                    }
                    node = node.nextEdge;
                }
            }
        }
        System.out.print("\n");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < vertExs.length; i++) {
            stringBuilder.append(i).append("(").append(vertExs[i].data).append("): ");
            LNode node = vertExs[i].firstEdge;
            while (node != null) {
                stringBuilder.append(node.vertex).append("(").append(vertExs[node.vertex].data).append(")");
                node = node.nextEdge;
                if (node != null) {
                    stringBuilder.append("->");
                } else {
                    break;
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        //顶点数组 添加的先后顺序对于遍历结果有影响
        Character[] vExs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //边二维数组 添加的先后顺序对于遍历结果有影响
        Character[][] edges = {
                {'A', 'C'},
                //对于无向图来说是多余的边关系,在linkLast方法中做了判断,并不会重复添加
                {'A', 'D'},
                {'A', 'D'},
                {'D', 'A'},

                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'E', 'B'},
                {'D', 'B'},
                {'F', 'G'}};
        //构建图
        UndirectedAdjacencyList<Character> undirectedAdjacencyList = new UndirectedAdjacencyList<>(vExs, edges);
        //输出图
        System.out.println(undirectedAdjacencyList);
        //深度优先遍历
        undirectedAdjacencyList.DFS();
        //广度优先遍历
         undirectedAdjacencyList.BFS();
    }
}

