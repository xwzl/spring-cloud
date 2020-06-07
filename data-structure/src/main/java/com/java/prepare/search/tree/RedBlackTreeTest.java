package com.java.prepare.search.tree;

import org.junit.Test;

public class RedBlackTreeTest<E> {
    @Test
    public void test3() {
        RedBlackTree<Integer, Integer> map = new RedBlackTree<>();
        map.put(3, 47);
        map.put(2, 16);
        map.put(1, 73);
        map.put(4, 1);
        map.put(5, 24);
        map.put(6, 59);
        map.put(7, 59);
        map.put(16, 59);
        map.put(15, 59);
        map.put(14, 11);
        map.put(13, 59);
        map.put(12, 59);
        map.put(11, 12);
        map.put(10, 59);
        map.put(8, 59);
        map.put(9, 59);
        /*中序遍历输出,即根据排序顺序从小到大输出*/
        System.out.println(map.toInorderTraversalString());

        System.out.println(map.remove(2));
        System.out.println(map.remove(4));
        System.out.println(map.remove(5));
        System.out.println(map.remove(12));
        System.out.println(map.remove(7));
        System.out.println(map.remove(13));
        System.out.println(map.remove(8));
        System.out.println(map.remove(16));
        System.out.println(map.remove(11));
        System.out.println(map.remove(10));
        System.out.println(map.remove(14));
        System.out.println(map.remove(3));
        System.out.println(map.remove(9));
        System.out.println(map.remove(6));
        System.out.println(map.remove(15));
        System.out.println(map.remove(1));
        System.out.println(map.toInorderTraversalString());
    }

    @Test
    public void test1() {
        RedBlackTree<Integer, Integer> map = new RedBlackTree<>();

        map.put(47, 47);
        map.put(16, 16);
        map.put(73, 73);
        map.put(1, 1);
        map.put(24, 24);
        map.put(59, 59);
        map.put(20, 20);
        map.put(35, 35);
        map.put(62, 62);
        map.put(77, 77);
        map.put(77, 101);
        /*中序遍历输出,即根据排序顺序从小到大输出*/
        System.out.println(map.toInorderTraversalString());

        System.out.println(map.remove(47));
        System.out.println(map.remove(62));
        System.out.println(map.remove(77));
        System.out.println(map.remove(16));
        System.out.println(map.remove(20));
        System.out.println(map.remove(59));
        System.out.println(map.remove(1));
        System.out.println(map.remove(24));
        System.out.println(map.remove(35));
        System.out.println(map.remove(73));

        System.out.println(map.toInorderTraversalString());
    }

    @Test
    public void test2() {
        RedBlackTree<Integer, Integer> map = new RedBlackTree<>();
        map.put(1, 47);
        map.put(2, 16);
        map.put(4, 73);
        map.put(9, 1);
        map.put(5, 24);
        map.put(7, 59);
        map.put(6, 59);
        /*中序遍历输出,即根据排序顺序从小到大输出*/
        System.out.println(map.toInorderTraversalString());

        System.out.println(map.remove(2));
        System.out.println(map.remove(4));
        System.out.println(map.remove(1));
        System.out.println(map.remove(6));
        System.out.println(map.remove(7));
        System.out.println(map.remove(5));
        System.out.println(map.remove(9));
        System.out.println(map.toInorderTraversalString());
    }

    @Test
    public void test4() {
        RedBlackTree<Integer, Integer> map = new RedBlackTree<>();
        map.put(1, 47);
        map.put(2, 16);
        map.put(4, 73);
        map.put(9, 1);
        map.put(5, 24);
        map.put(7, 59);
        map.put(6, 59);
        /*中序遍历输出,即根据排序顺序从小到大输出*/
        System.out.println(map.toInorderTraversalString());

        System.out.println("maxKey=====>" + map.maxKey());
        System.out.println(map.remove(9));
        System.out.println("maxKey=====>" + map.maxKey());

        System.out.println("minKey=====>" + map.minKey());
        System.out.println(map.remove(1));
        System.out.println(map.remove(1));
        System.out.println("minKey=====>" + map.minKey());

        System.out.println("get 2=====>" + map.get(2));
        System.out.println(map.remove(2));
        System.out.println("get 2=====>" + map.get(2));

    }

}
