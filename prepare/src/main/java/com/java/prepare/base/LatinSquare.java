package com.java.prepare.base;

/**
 * 拉丁方针
 *
 * @author xuweizhi
 * @since 2020/05/13 21:53
 */
public class LatinSquare {

    void init(Node l, int n) {
        Node p = l;
        Node s;
        for (int i = 0; i < n * n; i++) {
            s = new Node();
            s.data = 0;
            p.next = s;
            p = s;
        }
        p.next = l.next;
        creat(l, n);
        print(l, n);

    }

    /**
     * 自己写的
     */
    private void createSelf(Node p, int n) {
        int i = 0;
        while (i < n) {
            for (int j = 0; j <= n; j++) {
                p = p.next;
                if (j == n) {
                    p = p.next;
                }
                if (p.data == i + 1) {
                    System.out.print(p.data + " ");
                    continue;
                }
                System.out.print(p.data + " ");

            }
            i++;
            System.out.println();
        }
    }

    void creat(Node l, int n) {
        Node p = l;
        for (int i = 0; i < n * n; i++) {
            int a = i / n;
            int b = i % n + 1;
            int r = (a + b) % n;
            r = r % n == 0 ? n : r;
            p.next.data = r;
            p = p.next;
        }

    }

    void print(Node l, int n) {
        Node p = l;
        for (int i = 0; i < n * n; i++) {
            System.out.print(p.next.data + "    ");
            p = p.next;
            if ((i + 1) % n == 0) System.out.println();
        }
    }


    // 5 1 2 3 4
    // 5 1 2 3 4 5 1
    // 1 2 3 4 5 1 2  2
    // 3 4 5 1 2 3 4 3
    public static void main(String[] args) {
        LatinSquare latinSquare = new LatinSquare();
        latinSquare.init(new Node(), 5);
    }

    static class Node {
        int data;
        Node next;
    }
}

