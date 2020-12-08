package com.java.prepare.tree.heap;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * 小顶堆的实现
 * {@link MinBinaryHeap#MinBinaryHeap()} 初始化空的小顶堆,使用默认容量
 * {@link MinBinaryHeap#MinBinaryHeap(int)} 初始化空的小顶堆,使用指定容量
 * {@link MinBinaryHeap#MinBinaryHeap(Comparator)} 初始化空的小顶堆,使用指定比较器
 * {@link MinBinaryHeap#MinBinaryHeap(int, Comparator)} 初始化空的小顶堆,使用指定容量和比较器
 * {@link MinBinaryHeap#MinBinaryHeap(Collection)} 根据指定集合元素构建小顶堆
 * {@link MinBinaryHeap#MinBinaryHeap(Collection, Comparator)} 根据指定集合元素构建小顶堆,使用自定义比较器
 * {@link MinBinaryHeap#add(Object)} 添加元素,并重构小顶堆
 * {@link MinBinaryHeap#remove(Object)} 删除元素,并重构小顶堆
 * {@link MinBinaryHeap#heapSort()} 小顶堆排序(顺序)
 * {@link MinBinaryHeap#toString()} 输出小顶堆
 *
 * @author lx
 */
public class MinBinaryHeap<E> {
    /**
     * 堆的物理结构,即使用数组来实现
     */
    private Object[] heap;

    /**
     * 节点数量
     */
    private int size;

    /**
     * 容量
     */
    private static int capacity = 16;

    /**
     * 如果元素使用自然排序,那么比较器为null
     */
    private final Comparator<? super E> cmp;


    /**
     * 对元素进行比较大小的方法,如果传递了自定义比较器,则使用自定义比较器,否则则需要数据类型实现Comparable接口
     *
     * @param e1 被比较的第一个对象
     * @param e2 被比较的第二个对象
     * @return 0 相等 ;小于0 e1 < e2 ;大于0 e1 > e2
     */
    private int compare(E e1, E e2) {
        if (cmp != null) {
            return cmp.compare(e1, e2);
        } else {
            return ((Comparable<E>) e1).compareTo(e2);
        }
    }


    /**
     * 初始化空的小顶堆,使用默认容量
     */
    public MinBinaryHeap() {
        this(capacity, null);
    }

    /**
     * 初始化空的小顶堆,指定容量
     *
     * @param initCapacity 指定容量数组
     */
    public MinBinaryHeap(int initCapacity) {
        this(initCapacity, null);
    }

    /**
     * 初始化空的小顶堆,指定比较器
     *
     * @param comparator 指定比较器
     */
    public MinBinaryHeap(Comparator<? super E> comparator) {
        this(capacity, comparator);
    }

    /**
     * 初始化空的小顶堆,指定容量和比较器
     *
     * @param initCapacity 指定数组容量
     * @param comparator   指定比较器
     */
    public MinBinaryHeap(int initCapacity, Comparator<? super E> comparator) {
        if (initCapacity < 1) {
            throw new IllegalArgumentException();
        }
        capacity = initCapacity;
        this.heap = new Object[initCapacity];
        cmp = comparator;
    }

    /**
     * 同通过一批数据初始化小顶堆
     *
     * @param heap 数组
     */
    public MinBinaryHeap(Collection<? extends E> heap) {
        this(heap, null);
    }


    /**
     * 同通过一批数据和指定比较器初始化小顶堆
     *
     * @param heap       数组
     * @param comparator 自定义的比较器
     */
    public MinBinaryHeap(Collection<? extends E> heap, Comparator<? super E> comparator) {
        Object[] array = heap.toArray();
        this.cmp = comparator;
        if (array.getClass() != Object[].class) {
            array = Arrays.copyOf(array, array.length, Object[].class);
        }
        for (Object o : array) {
            if (o == null) {
                throw new NullPointerException();
            }
        }
        this.heap = array;
        this.size = array.length;
        buildHeap(this.heap);
    }

    /**
     * 构建小顶堆
     *
     * @param heap 一批数据
     */
    private void buildHeap(Object[] heap) {
        /*i从最后一个非叶子节点的索引开始，递减构建，直到i=-1结束循环
        这里元素的索引是从0开始的，所以最后一个非叶子节点array.length/2 - 1，这是利用了完全二叉树的性质*/
        for (int i = heap.length / 2 - 1; i >= 0; i--) {
            buildHeap(heap, i, heap.length);
        }
    }


    /**
     * 从指定索引向下构建小顶堆，最终该位置的结点小于等于其子结点
     *
     * @param arr    数组
     * @param i      非叶子节点的索引
     * @param length 堆长度
     */
    private void buildHeap(Object[] arr, int i, int length) {
        //先把当前非叶子节点元素取出来，因为当前元素可能要一直移动
        Object temp;
        //节点的子节点的索引
        int childIndex;
        /*循环判断父节点是否大于两个子节点,如果左子节点索引大于等于堆长度 或者父节点大于两个子节点 则结束循环*/
        for (temp = arr[i]; (childIndex = 2 * i + 1) < length; i = childIndex) {
            //childIndex + 1 < length 说明该节点具有右子节点,并且如果如果右子节点的值小于左子节点，那么childIndex自增1，即childIndex指向右子节点索引
            if (childIndex + 1 < length && compare((E) arr[childIndex], (E) arr[childIndex + 1]) > 0) {
                childIndex++;
            }
            //如果发现最小子节点(左、右子节点)小于根节点，为了满足小顶堆根节点的值小于子节点，需要进行值的交换
            //如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，交换之后继续循环对子节点所在的树进行判断
            if (compare((E) arr[childIndex], (E) temp) < 0) {
                swap(arr, i, childIndex);
            } else {
                //走到这里，说明父节点小于等于最小的子节点，满足小顶堆的条件，直接终止循环
                break;
            }
        }
    }

    /**
     * 小顶堆排序(顺序)
     *
     * @param arr 需要被排序的数据集合
     */
    public void bigHeapSort(Object[] arr) {
        /*1、构建小顶堆*/
        /*i从最后一个非叶子节点的索引开始，递减构建，直到i=-1结束循环
        这里元素的索引是从0开始的，所以最后一个非叶子节点array.length/2 - 1，这是利用了完全二叉树的性质*/
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            buildHeap(arr, i, arr.length);
        }
        /*2、开始堆排序，i = arr.length - 1，即从小顶堆尾部的数开始，直到i=0结束循环*/
        for (int i = arr.length - 1; i > 0; i--) {
            //交换堆顶与堆尾元素顺序
            swap(arr, 0, i);
            //重新构建小顶堆
            buildHeap(arr, 0, i);
        }
    }


    /**
     * 小顶堆排序(逆序)
     * 实际上就是不断循环将堆顶元素与堆尾元素互换,然后移除堆尾元素,之后重构小顶堆的过程
     */
    public Object[] heapSort() {
        //使用小顶堆的副本进行排序输出
        Object[] arr = Arrays.copyOf(heap, size);
        /*2、开始堆排序，i = arr.length - 1，即从小顶堆尾部的数开始，直到i=0结束循环*/
        for (int i = arr.length - 1; i > 0; i--) {
            //交换堆顶与堆尾元素顺序
            swap(arr, 0, i);
            //重新构建小顶堆，此时堆的大小为交换前堆大小-1
            buildHeap(arr, 0, i);
        }
        return arr;
    }

    /**
     * 添加节点
     *
     * @param e 被添加的节点元素
     */
    public void add(E e) {
        /*判空*/
        if (e == null) {
            throw new NullPointerException();
        }
        /*检查容量*/
        if (heap.length == size) {
            resize();
        }
        /*添加节点*/
        addNode(e, size++);
    }

    /**
     * 添加节点，并向上重构小顶堆，最终找到一个位置加入新结点e，该位置的结点大于等于其父结点
     *
     * @param e     要添加的节点
     * @param start 即新添加元素所在的索引
     */
    private void addNode(E e, int start) {
        //获取size处结点的父节点索引
        int parent = (start - 1) / 2;
        /*如果size>0 寻找合适的位置:在某个插入的位置的新节点大于等于对应的父节点的值*/
        while (start > 0) {
            //判断父节点和新子节点的大小,如果父节点小于等于新子节点,那么符合小顶堆的要求,重构结束
            if (compare((E) heap[parent], e) <= 0) {
                break;
            } else {
                //否则,将父节点的值移动到子节点的位置处
                heap[start] = heap[parent];
                //将start的索引值变成父节点的索引值
                start = parent;
                //重新计算父节点的索引,不断循环,直到找到父节点值小于等于新子节点值的索引
                parent = (start - 1) / 2;
            }
        }
        //在合适的位置插入新节点值
        heap[start] = e;
    }

    /**
     * 扩容
     */
    private void resize() {
        heap = Arrays.copyOf(heap, heap.length * 2, Object[].class);
    }


    /**
     * 交换元素
     *
     * @param arr 数组
     * @param a   元素的下标
     * @param b   元素的下标
     */
    private static void swap(Object[] arr, int a, int b) {
        Object temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    /**
     * 删除找到的第一个堆节点,并且重构小顶堆
     *
     * @param e 需要删除的节点
     * @return false 删除失败 true 删除成功
     */
    public boolean remove(E e) {
        int eIndex = -1;
        for (int i = 0; i < size; i++) {
            if (compare((E) heap[i], e) == 0) {
                eIndex = i;
            }
        }
        if (eIndex == -1) {
            return false;
        }
        //原尾部元素x
        E x = (E) heap[size - 1];
        //交换查找到的元素与堆尾元素的位置
        swap(heap, eIndex, size - 1);
        //移除堆尾元素
        heap[size--] = null;
        //从eIndex开始向下重新构建小顶堆
        buildHeap(heap, eIndex, size);
        //构建之后如果eIndex位置的元素就是x，说明没有调整堆结构，那么将该位置的元素看成新插入的元素，需要向上构建小顶堆
        if (heap[eIndex] == x) {
            //调用addNode从eIndex开始向上重构小顶堆
            addNode(x, eIndex);
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(heap[i]);
            if (i != size - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
