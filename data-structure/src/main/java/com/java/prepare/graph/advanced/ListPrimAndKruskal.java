package com.java.prepare.graph.advanced;

import java.util.*;

/**
 * 无向加权图邻接表实现
 * {@link ListPrimAndKruskal#ListPrimAndKruskal(E[], Edge[])} 构建无向加权图
 * {@link ListPrimAndKruskal#DFS()}  深度优先遍历无向加权图
 * {@link ListPrimAndKruskal#BFS()}  广度优先遍历无向加权图
 * {@link ListPrimAndKruskal#toString()}  输出无向加权图
 * {@link ListPrimAndKruskal#prim()}  Prim算法实现最小生成树
 * {@link ListPrimAndKruskal#kruskal()}   Kruskal算法实现最小生成树
 * {@link ListPrimAndKruskal#getEdges()}  获取边集数组
 *
 */
public class ListPrimAndKruskal<E> {
    /**
     * 顶点类
     *
     * @param <E>
     */
    private static class Node<E> {
        /**
         * 顶点信息
         */
        E data;
        /**
         * 指向第一条依附该顶点的边
         */
        LNode firstLNode;

        public Node(E data, LNode firstLNode) {
            this.data = data;
            this.firstLNode = firstLNode;
        }
    }

    /**
     * 边表节点类
     */
    private static class LNode {
        /**
         * 该边所指向的顶点的索引位置
         */
        int vertex;
        /**
         * 该边的权值
         */
        int weight;
        /**
         * 指向下一条边的指针
         */
        LNode nextLNode;
    }

    /**
     * 边对象,具有权值,在构建加权无向图时使用
     */
    private static class Edge<E> {

        private E from;
        private E to;
        private int weight;

        public Edge(E from, E to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    /**
     * 顶点数组
     */
    private final Node<E>[] vertExs;


    /**
     * 边数组
     */
    private final Edge<E>[] edges;

    /**
     * 由于是加权图,这里设置一个边的权值上限,任何边的最大权值不能大于等于该值，在实际应用中，该值应该根据实际情况确定
     */
    private static final int NO_EDGE = 99;

    /**
     * 创建无向加权图
     *
     * @param vExs  顶点数组
     * @param edges 边二维数组
     */
    public ListPrimAndKruskal(E[] vExs, Edge<E>[] edges) {
        this.edges = edges;
        /*初始化顶点数组,并添加顶点*/
        vertExs = new Node[vExs.length];
        for (int i = 0; i < vertExs.length; i++) {
            vertExs[i] = new Node<>(vExs[i], null);
        }
        /*初始化边表,并添加边节点到边表尾部,即采用尾插法*/
        for (Edge<E> edge : edges) {
            // 读取一条边的起始顶点和结束顶点索引值
            int p1 = getPosition(edge.from);
            int p2 = getPosition(edge.to);
            int weight = edge.weight;
            /*这里需要相互添加边节点,无向图可以看作相互可达的有向图*/
            // 初始化lNode1边节点
            LNode lNode1 = new LNode();
            lNode1.vertex = p2;
            lNode1.weight = weight;
            // 将LNode链接到"p1所在链表的末尾"
            if (vertExs[p1].firstLNode == null) {
                vertExs[p1].firstLNode = lNode1;
            } else {
                linkLast(vertExs[p1].firstLNode, lNode1);
            }
            // 初始化lNode2边节点
            LNode lNode2 = new LNode();
            lNode2.vertex = p1;
            lNode2.weight = weight;
            // 将lNode2链接到"p2所在链表的末尾"
            if (vertExs[p2].firstLNode == null) {
                vertExs[p2].firstLNode = lNode2;
            } else {
                linkLast(vertExs[p2].firstLNode, lNode2);
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
     * 将lnode节点链接到边表的最后,采用尾插法
     *
     * @param first 边表头结点
     * @param node  将要添加的节点
     */
    private void linkLast(LNode first, LNode node) {
        while (true) {
            if (first.vertex == node.vertex) {
                return;
            }
            if (first.nextLNode == null) {
                break;
            }
            first = first.nextLNode;
        }
        first.nextLNode = node;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < vertExs.length; i++) {
            stringBuilder.append(i).append("(").append(vertExs[i].data).append("): ");
            LNode node = vertExs[i].firstLNode;
            while (node != null) {
                stringBuilder.append(node.vertex).append("(").append(vertExs[node.vertex].data).append("-").append(node.weight).append(")");
                node = node.nextLNode;
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
        LNode node = vertExs[i].firstLNode;
        //循环遍历该顶点的邻接点,采用同样的方式递归搜索
        while (node != null) {
            if (!visited[node.vertex]) {
                DFS(node.vertex, visited);
            }
            node = node.nextLNode;
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
                LNode node = vertExs[j].firstLNode;
                while (node != null) {
                    int k = node.vertex;
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.print(vertExs[k].data + " ");
                        //继续入队列
                        indexLinkedList.add(k);
                    }
                    node = node.nextLNode;
                }
            }
        }
        System.out.println();
    }

    /**
     * Prim算法求最小生成树
     */
    public void prim() {
        System.out.println("prim: ");
        //对应节点应该被连接的前驱节点,用来输出
        //默认为0,即前驱结点为第一个节点
        int[] mid = new int[vertExs.length];
        int start = 0;
        int min, tmp, sum = 0;
        int num = vertExs.length;

        //顶点间边的权值
        //存储未连接顶点到已连接顶点的最短距离(最小权)
        int[] dis = new int[num];


        // 初始化"顶点的权值数组"，
        // 将每个顶点的权值初始化为"第start个顶点"到"该顶点"的权值。
        //首先将其他顶点到0索引顶点的权值存储进去
        for (int i = 0; i < num; i++) {
            dis[i] = getWeight(start, i);
        }
        //如果某顶点作为末端顶点被连接，对应位置应该为true
        //第一个顶点默认被连接
        boolean[] connected = new boolean[vertExs.length];
        connected[0] = true;
        /*默认第一个顶点已经找到了,因此最多还要需要大循环n-1次*/
        for (int k = 1; k < num; k++) {
            min = NO_EDGE;
            //最小权值的顶点的索引
            int minIndex = 0;
            // 在未被加入到最小生成树的顶点中，找出权值最小的顶点。
            for (int i = 1; i < vertExs.length; i++) {
                //排除已连接的顶点,排除权值等于0的值,因为这里默认顶点指向自己的权值为0
                if (!connected[i] && dis[i] != 0 && dis[i] < min) {
                    min = dis[i];
                    minIndex = i;
                }
            }
            //如果没找到,那么该图可能不是连通图,直接返回了,此时最小生成树没啥意义
            if (minIndex == 0) {
                return;
            }
            //权值和增加
            sum += min;
            //该新连接顶点对应的索引值变成true,表示已被连接,后续判断时跳过该顶点
            connected[minIndex] = true;
            //输出对应的前驱顶点到该最小顶点的权值
            System.out.println(vertExs[mid[minIndex]].data + " ---> " + vertExs[minIndex].data + " 权值:" + min);
            /*在新顶点minIndex加入之前的其他所有顶点到连接顶点最小的权值已经计算过了
            因此只需要更新其他顶点到新连接顶点minIndex是否还有更短的权值,有的话就更新找到距离已连接的顶点权最小的顶点*/
            for (int i = 1; i < num; i++) {
                //如果该顶点未连接
                if (!connected[i]) {
                    // 获取minIndex顶点到未连接顶点i的权值
                    tmp = getWeight(minIndex, i);
                    /*如果新顶点到未连接顶点i的权值不为0,并且比原始顶点到未连接顶点i的权值还要小,那么更新对应位置的最小权值*/
                    if (tmp != 0 && dis[i] > tmp) {
                        dis[i] = tmp;
                        //更新前驱节点索引为新加入节点索引
                        mid[i] = minIndex;
                    }
                }
            }
        }
        System.out.println("sum: " + sum);
    }

    /**
     * 尝试获取边起点start到边终点end的边的权值,当然可能获取不到
     *
     * @param start 边起点
     * @param end   边终点
     * @return 返回权值; 如果起点和终点相同则返回0;如果边起点和边终点之间并没有边, 则返回NO_EDGE
     */
    private int getWeight(int start, int end) {
        //如果start=end，则返回0
        if (start == end) {
            return 0;
        }
        //获取该顶点的边表的第一个值
        LNode node = vertExs[start].firstLNode;
        //循环查找边表,看能否找到对应的索引=end,找不到就返回NO_EDGE,表示两个顶点未连接。
        while (node != null) {
            if (end == node.vertex) {
                return node.weight;
            }
            node = node.nextLNode;
        }
        return NO_EDGE;
    }

    /**
     * Kruskal算法求最小生成树,可以说邻接矩阵和邻接链表的实现方式是完全一致的
     */
    public void kruskal() {
        //由于创建图的时候保存了边集数组,这里直接使用就行了
        //Edge[] edges = getEdges();
        //this.edges=edges;
        //对边集数组进行排序
        Arrays.sort(this.edges, Comparator.comparingInt(o -> o.weight));
        // 用于保存已有最小生成树中每个顶点在该最小树中的最终终点的索引
        int[] vends = new int[this.edges.length];
        //能够知道终点索引范围是[0,this.edges.length-1],因此填充edges.length表示没有终点
        Arrays.fill(vends, this.edges.length);
        int sum = 0;
        for (Edge<E> edge : this.edges) {
            // 获取第i条边的起点索引from
            int from = getPosition(edge.from);
            // 获取第i条边的终点索引to
            int to = getPosition(edge.to);
            // 获取顶点from在"已有的最小生成树"中的终点
            int m = getEndIndex(vends, from);
            // 获取顶点to在"已有的最小生成树"中的终点
            int n = getEndIndex(vends, to);
            // 如果m!=n，意味着没有形成环路,则可以添加,否则直接跳过,进行下一条边的判断
            if (m != n) {
                //添加设置原始终点索引m在已有的最小生成树中的终点为n
                vends[m] = n;
                System.out.println(vertExs[from].data + " ---> " + vertExs[to].data + " 权值:" + edge.weight);
                sum += edge.weight;
            }
        }
        System.out.println("sum: " + sum);
        //System.out.println(Arrays.toString(this.edges));
    }

    /**
     * 获取顶点索引i的终点如果没有终点则返回顶点索引本身
     *
     * @param vends 顶点在最小生成树中的终点
     * @param i     顶点索引
     * @return 顶点索引i的终点如果没有终点则返回顶点索引本身
     */
    private int getEndIndex(int[] vends, int i) {
        //这里使用循环查找的逻辑,寻找的是最终的终点
        while (vends[i] != this.edges.length) {
            i = vends[i];
        }
        return i;
    }

    /**
     * 如果没有现成的边集数组，那么根据邻接表结构获取图中的边集数组
     *
     * @return 图的边集数组
     */
    private Edge[] getEdges() {
        List<Edge> edges = new ArrayList<>();
        //遍历顶点数组
        for (int i = 0; i < vertExs.length; i++) {
            LNode node = vertExs[i].firstLNode;
            while (node != null) {
                //只需添加起点索引小于终点索引的边就行了
                if (node.vertex > i) {
                    edges.add(new Edge<>(vertExs[i].data, vertExs[node.vertex].data, node.weight));
                }
                node = node.nextLNode;
            }
        }
        return edges.toArray(new Edge[0]);
    }

    public static void main(String[] args) {
        //顶点数组
        Character[] vExs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //边数组,加权值
        Edge[] edges = {
                new Edge('A', 'C', 1),
                new Edge('D', 'A', 2),
                new Edge('A', 'F', 3),
                new Edge('B', 'C', 4),
                new Edge('C', 'D', 5),
                new Edge('E', 'G', 6),
                new Edge('E', 'B', 7),
                new Edge('D', 'B', 8),
                new Edge('F', 'G', 9)};
        //构建图
        ListPrimAndKruskal<Character> listPrimAndKruskal = new ListPrimAndKruskal<Character>(vExs, edges);
        //输出图
        System.out.println(listPrimAndKruskal);
        //深度优先遍历
        //DFS:
        //A C B E G F D
        listPrimAndKruskal.DFS();
        //广度优先遍历
        //BFS:
        //A C D F B G E
        listPrimAndKruskal.BFS();
        //Prim算法求最小生成树
        listPrimAndKruskal.prim();
        //Kruskal算法求最小生成树
        listPrimAndKruskal.kruskal();
        //获取边集数组
        Edge[] edges1 = listPrimAndKruskal.getEdges();
        System.out.println(Arrays.toString(edges1));
    }
}
