package com.java.prepare.turing;

import lombok.Data;

/**
 * @author xuweizhi
 * @since 2020/09/23 23:58
 */
public class CycleNode {

    private Node head;

    private Node move;

    @Data
    static class Node {
        private int data;
        private Node next;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public boolean delete(Node node) {
        Node t = head;
        if (t.equals(node) && head.next != head) {
            head = head.next;
            return true;
        }
        if (head.next == head) {
            return false;
        }
        Node pre = t;
        while ((t = t.next) != null) {
            if (t.equals(node)) {
                break;
            }
            pre = t;
        }
        pre.next = t.next;
        return true;
    }

    public void insert(int data) {
        Node t = head;
        if (t == null) {
            head = new Node(data);
            return;
        }
        while (t.next != null) {
            t = t.next;
        }
        t.next = new Node(data);
    }

    public void cycleNode() {
        Node t = head;
        while (t.next != null) {
            t = t.next;
        }
        t.next = head;
    }

    public void move() {
        if (move == null) {
            move = head;
            return;
        }
        move = move.next;
    }

    public static void main(String[] args) {
        CycleNode cycleNode = new CycleNode();
        for (int i = 1; i < 7; i++) {
            cycleNode.insert(i);
        }
        cycleNode.cycleNode();
        int count = 1;
        while (true) {
            cycleNode.move();
            if (count % 5 == 0) {
                Node head = cycleNode.move;
                boolean delete = cycleNode.delete(head);
                System.out.println(head.data);
                if (!delete) {
                    break;
                }
            }
            count++;
        }
    }
}
