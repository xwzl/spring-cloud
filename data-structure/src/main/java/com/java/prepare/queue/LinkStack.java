package com.java.prepare.queue;

/**
 * 链表栈实现
 *
 * @author xuweizhi
 * @since 020/05/27 23:42
 */
public class LinkStack implements Stack {

    Node head;  //栈顶指针
    int size;  //结点的个数

    public LinkStack() {
        head = null;
        size = 0;
    }

    @Override
    public Object getTop() throws Exception {
        // TODO Auto-generated method stub
        return head.getElement();
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return head == null;
    }

    @Override
    public Object pop() throws Exception {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            throw new Exception("栈为空！");
        }
        Object obj = head.getElement();
        head = head.getNext();
        size--;
        return obj;

    }

    @Override
    public void push(Object obj) throws Exception {
        // TODO Auto-generated method stub
        head = new Node(obj, head);
        size++;
    }

    public static void main(String[] args) throws Exception {

        String str1 = "ABCDCBA"; //是回文
        String str2 = "ABCDECAB"; //不是回文

        try {
            if (LinkStack.isHuiWen(str1)) {
                System.out.println(str2 + ":是回文！");
            } else {
                System.out.println(str2 + ":不是回文！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //方法：判断字符串是否回文
    public static boolean isHuiWen(String str) throws Exception {
        int n = str.length();
        LinkStack stack = new LinkStack();//创建堆栈
        LinkQueue queue = new LinkQueue();//创建队列
        for (int i = 0; i < n; i++) {
            stack.push(str.subSequence(i, i + 1)); //把字符串每个字符压进堆栈
            queue.append(str.subSequence(i, i + 1));//把字符串每个字符压入队列
        }
        while (!queue.isEmpty() && !stack.isEmpty()) {
            if (!queue.delete().equals(stack.pop())) {  //出队列，出栈，同时判断是否相同
                return false;
            }
        }

        return true;
    }

}
