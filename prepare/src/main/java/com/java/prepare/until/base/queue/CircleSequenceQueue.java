package com.java.prepare.until.base.queue;

/**
 * 循环队列
 *
 * @author xuweizhi
 * @since 2020/05/27 23:35
 */
public class CircleSequenceQueue implements Queue {
    //默认队列的长度
    static final int defaultSize = 10;
    //队头
    int front;
    //队尾
    int rear;
    //统计元素个数的计数器
    int count;
    //队的最大长度
    int maxSize;
    //队列
    Object[] queue;

    public CircleSequenceQueue() {
        init(defaultSize);
    }

    public CircleSequenceQueue(int size) {
        init(size);
    }

    public void init(int size) {
        maxSize = size;
        front = rear = 0;
        count = 0;
        queue = new Object[size];
    }

    @Override
    public void append(Object obj) throws Exception {
        // TODO Auto-generated method stub
        if (count > 0 && front == rear) {
            throw new Exception("队列已满！");
        }
        queue[rear] = obj;
        rear = (rear + 1) % maxSize;
        count++;
    }

    @Override
    public Object delete() throws Exception {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            throw new Exception("队列为空！");
        }
        Object obj = queue[front];
        front = (front + 1) % maxSize;
        count--;
        return obj;
    }

    @Override
    public Object getFront() throws Exception {
        // TODO Auto-generated method stub
        if (!isEmpty()) {
            return queue[front];
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
        CircleSequenceQueue queue = new CircleSequenceQueue();
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