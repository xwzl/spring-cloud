package com.java.prepare.turing.linear.my;

/**
 * 静态链表的简单实现,为了简单,该实现的底层数组设计为不可扩展
 */
public class MyStaticLinkedList<E> {

    /**
     * 采用数组实现链式存储
     */
    private final Node<E>[] elements;

    /**
     * 容量
     */
    private final int capacity;

    /**
     * 数据链表的头结点索引
     */
    private int dataFirst;

    /**
     * 空闲链表的头结点索引
     */
    private int freeFirst;

    /**
     * 元素个数
     */
    private int size;

    /**
     * 构造器
     */
    public MyStaticLinkedList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    capacity);
        }
        this.capacity = capacity;
        this.elements = new Node[capacity];
    }


    /**
     * 单链表内部的节点
     */
    private static class Node<E> {
        //下一个结点的引用,这里采用数组下标索引来表示
        Integer next;
        //结点数据
        E data;

        //节点构造器
        private Node(E data, Integer next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", data=" + data +
                    '}';
        }
    }

    /**
     * 默认 添加元素到单链表尾部
     * @param e 要添加的元素
     * @return true 添加成功 false 添加失败
     */
    public boolean add(E e) {
        if (checkCapcity()) {
            return false;
        }
        //创建新节点
        Node<E> newNode = new Node(e, null);
        if (size == 0) {
            elements[freeFirst] = newNode;
            freeFirst = 1;
        } else {
            //寻找尾节点
            Node<E> firstNode = elements[dataFirst];
            while (firstNode.next != null) {
                firstNode = elements[firstNode.next];
            }
            firstNode.next = freeFirst;
            elements[freeFirst] = newNode;
            //寻找下一个空闲链表头部
            findFreeFirst();
        }
        size++;
        return true;
    }

    /**
     * 默认 删除单链表头部元素
     * @return 被删除的元素
     */
    public E remove() {
        if (size == 0) {
            return null;
        } else {
            Node<E> first = elements[dataFirst];
            E e = first.data;
            Integer nextIndex = first.next;
            if (nextIndex == null) {
                dataFirst = 0;
                freeFirst = 0;
                elements[dataFirst] = null;
            } else {
                elements[dataFirst] = null;
                dataFirst = nextIndex;
                //寻找下一个空闲链表头部
                findFreeFirst();
            }
            size--;
            return e;
        }

    }


    /**
     * 添加元素到链表指定索引位置，原索引节点链接为next
     * @param e 要添加的元素
     * @param index 指定索引
     * @return true 添加成功 false 添加失败
     */
    public boolean add(E e, int index) {
        checkPositionIndex(index);
        if (checkCapcity()) {
            return false;
        }
        if (index == size) {
            add(e);
        } else if (index == 0) {
            //新建节点
            elements[freeFirst] = new Node<>(e, dataFirst);
            //更新数据链表的头结点
            dataFirst = freeFirst;
            //寻找下一个空闲链表的头结点
            findFreeFirst();
        } else {
            //寻找指定节点的前一个节点
            Node<E> preNode = getNode(index - 1);
            //新建节点
            elements[freeFirst] = new Node<>(e, preNode.next);
            //改变"索引"
            preNode.next = freeFirst;
            //寻找下一个空闲链表头部
            findFreeFirst();
        }
        size++;
        return true;
    }


    /**
     * 删除链表指定索引元素
     *
     * @param index 指定索引
     * @return 被删除的元素
     */
    public E remove(int index) {
        checkElementIndex(index);
        if (size == 0) {
            return null;
        } else if (index == 0) {
            return remove();
        } else {
            //获取被删除元素的前一个前驱元素
            Node<E> preNode = getNode(index - 1);
            //获取当前元素的索引
            Integer nodeIndex = preNode.next;
            Node<E> node = elements[nodeIndex];
            E data = node.data;
            //改变"指针"
            preNode.next = node.next;
            //被删除的元素位置置空,让GC来回收
            elements[nodeIndex] = null;
            //调整空闲链表的头结点索引
            if (nodeIndex < freeFirst) {
                freeFirst = nodeIndex;
            }
            size--;
            return data;
        }

    }


    /**
     * 获取指定链表索引的元素
     *
     * @param index 索引
     * @return 索引处的元素
     */
    public E get(int index) {
        checkElementIndex(index);
        return getNode(index).data;
    }


    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            Node<E> node = elements[dataFirst];
            stringBuilder.append(node.data).append(", ");
            while (node.next != null) {
                node = elements[node.next];
                stringBuilder.append(node.data);
                if (node.next != null) {
                    stringBuilder.append(" ,");
                }
            }
            return stringBuilder.toString();
        }
    }

    /***
     * 增强toString ,用于测试
     */
    public String toStringAll() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        for (int i = 0; i < elements.length; i++) {
            Node<E> node = elements[i];
            if (node != null) {
                stringBuilder.append(node.toString());
            } else {
                stringBuilder.append("null");
            }
            if (i != elements.length - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(" ]");
        stringBuilder.append("{ freefirst:").append(freeFirst);
        stringBuilder.append(", datafirst: ").append(dataFirst);
        stringBuilder.append(", size: ").append(size).append(" }");
        return stringBuilder.toString();
    }


    /**
     * 寻找下一个空闲链表头部
     */
    private void findFreeFirst() {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                freeFirst = i;
                break;
            }
        }
    }


    /**
     * 获取指定位置的节点
     *
     * @param index 指定索引
     * @return 节点
     */
    private Node<E> getNode(int index) {
        /*寻找节点*/
        Node<E> firstNode = elements[dataFirst];
        for (int i = 0; i < index; i++) {
            firstNode = elements[firstNode.next];
        }
        return firstNode;
    }


    /**
     * 检查索引是否越界，用在删除、获取
     */
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
    }


    /**
     * 检查索引是否越界，用在添加
     */
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
    }


    /**
     * 检查容量
     */
    private boolean checkCapcity() {
        return size == capacity;
    }
}

