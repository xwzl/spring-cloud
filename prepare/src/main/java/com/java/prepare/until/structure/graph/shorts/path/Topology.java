package com.java.prepare.until.structure.graph.shorts.path;

/**
 * 拓扑排序
 * <p>
 * 在一个表示工程的有向图中，用顶点表示活动，用弧表示活动之间的有限关系，这样的有向图
 * 为顶点表示的网，我们成为 AOV 网（Activity On Vertex Network）.AOV 网中的弧表示
 * 活动之间存在的某种制约关系.
 * <p>
 * AOV网中的弧表示活动之间存在的某种制约关系，并且不应该存在回路，因为若带有回路，则回
 * 路上的所有活动都无法进行。
 * <p>
 * 设 G=(V,E) 是一个具有 n 个顶点的有向图，v 中的顶点序列 v1,v2,......vn,满足若从
 * 顶点 vi 到 vj 有一条路径，则在顶点序列中顶点 vi 必在顶点 vj 之前。则我们称这样的
 * 顶点序列为一个拓扑排序。
 * <p>
 * 所谓拓扑排序(Topological Sort)，其实就是对一个有向图无环图构造拓扑序列的过程。从离
 * 散数学的角度来看，拓扑排序就是由某集合上的一个偏序得到该集合上的一个全序。偏序指集合
 * 中仅有部分成员之间可比较（集合存在部分排序关系，但是任然存在某些元素间无法比较），而
 * 全序指集合中全体成员之间都可以比较（对于集合中的任何一对元素，在某个关系下都是相互可
 * 比较的）。
 * <p>
 * 偏序就像是一个流程图，其中有些步骤是没有明确先后关系的，比如上面的中间的有向无环图中，
 * D和F是无法比较的（无法得知先后顺序），甚至左边路径C-D-B和右边路径的F-G的先后顺序都是
 * 无法比较的。拓扑排序的任务是在这个偏序上得到一个全序，即得到一个完成整个项目的各步骤的
 * 序列。
 * 正是由于某些步骤间没有规定优先关系（这就是偏序的特点），拓扑排序得到的序列有可能不是唯
 * 一的，在实际生活中，比如醒来-穿衣服-穿裤子-出门。醒来一定是最先的，出门一定是最后的，但
 * 是穿衣服和穿裤子他们的顺序是可以交换的。在拓扑排序的时候常常需要人为的加入一些规则，使得
 * 到的序列为满足偏序关系的一个全序。
 * <p>
 * 假设你正在规划一个项目，并有该项目是一个很大的有向无环图，其中充斥着需要做的事情，但却不
 * 知道要从哪里开始。这时就可使用拓扑排序并且根据人为规定的一些先后顺序来创建一个有序的任务
 * 列表，让所有的活动都具有先后次序，方便项目的开展。
 * <p>
 * 拓扑排序的常见实现算法是Kahn算法。
 * <p>
 * Kahn算法的基本思想是：
 * <ul>
 *     <li>找到入度为0 的顶点找到并记录到队列或者栈中；</li>
 *     <li>移除找到的入度为0的顶点和对应的以该顶点为起点的边，并将被移除的顶点加入到list集合
 *     中，同时移除的顶点作为起点的边的终点的如度减去1；继续循环1的步骤，直至队列或者栈为空。</li>
 *     <li>此时list集合中的顶点的顺序输出就是拓扑排序的结果；如果list集合的元素数量少于顶点数
 *     量则说明该有向图存在环。</li>
 * </ul>
 * 所谓拓扑排序，其实就是对一个有向图构造。
 *
 * https://blog.csdn.net/weixin_43767015/article/details/106203289
 *
 * @author xuweizhi
 * @since 2020/06/03 18:02
 */
public class Topology {


}
