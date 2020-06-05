package com.java.prepare.queue;

/**
 * 链表队列
 *
 * @author xuweizhi
 * @since 2020/05/27 23:35
 */
public class LinkQueue implements Queue {

    Node front; //队头
    Node rear;  //队尾
    int count; //计数器

    public LinkQueue() {
        init();
    }

    public void init() {
        front = rear = null;
        count = 0;
    }

    @Override
    public void append(Object obj) throws Exception {
        // TODO Auto-generated method stub
        Node node = new Node(obj, null);
        //如果当前队列不为空。
        if (rear != null) {
            rear.next = node; //队尾结点指向新结点
        }
        rear = node; //设置队尾结点为新结点

        //说明要插入的结点是队列的第一个结点
        if (front == null) {
            front = node;
        }
        count++;
    }

    @Override
    public Object delete() throws Exception {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            throw new Exception("队列已空！");
        }
        Node node = front;
        front = front.next;
        count--;
        return node.getElement();
    }

    @Override
    public Object getFront() throws Exception {
        // TODO Auto-generated method stub
        if (!isEmpty()) {
            return front.getElement();
        } else {
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return count == 0;
    }

    public static void main(String[] args) throws Exception {
        LinkQueue queue = new LinkQueue();
        queue.append("a");
        queue.append("b");
        queue.append("c");
        queue.append("d");
        queue.append("e");
        queue.append("f");
        queue.delete();
        queue.delete();
        queue.append("g");
        while (!queue.isEmpty()) {
            System.out.println(queue.delete());
        }
    }
}
