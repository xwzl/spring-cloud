package com.java.prepare.until.base.linear;

import lombok.Data;

/**
 * 发牌问题
 */
public class Magic {
    /**
     * @param l 头结点
     * @param n 初始化大小
     */
    void init(LinkList l, int n) {
        LinkList p = l;
        LinkList s;
        for (int i = 0; i < n; i++) {
            s = new LinkList();
            s.data = 0;
            // i=0 l: l.next = LinkList
            // i= 1 l: l.next= 2 LinkList
            // 尾插法
            p.next = s;
            p = s; // 每次都是最好的节点
            // 头插法
            //s.next = p;
            //p = s; // 每次都是最新的节点
        }
        p.next = l.next;
        creat(l, n);
        print(l, n);
    }

    void creat(LinkList l, int n) {
        LinkList p = l;
        int count = 1;
        do {
            for (int i = 1; i <= count; i++) {
                // 这里的作用的是,让赋值的结点不占用步数，因为现实生活赋值的解节点相当于被干掉了，因此不能占用步数
                if (p.next.data != 0) i--;
                p = p.next;
            }
            p.data = count;
            count++;
        } while (count != n + 1);

    }

    void print(LinkList l, int n) {
        LinkList p = l;
        for (int i = 0; i < n; i++) {
            System.out.print(p.next.data + "    ");
            p = p.next;
        }
    }

    public static void main(String[] args) {
        Magic m = new Magic();
        LinkList l = new LinkList();
        m.init(l, 13);
    }
}

@Data
class LinkList {
    int data;
    LinkList next;
}
