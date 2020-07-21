package com.java.prepare.container;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author xuweizhi
 * @since 2020/07/21 10:38
 */
@Slf4j
public class ArrayListTest {

    static List<Integer> list = new ArrayList<Integer>();

    static {
        for (int i = 1; i <= 100000000; i++) {
            list.add(i);
        }
    }

    public static long arrayFor() {
        //开始时间
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < list.size(); j++) {
            Object num = list.get(j);
        }
        //结束时间
        long endTime = System.currentTimeMillis();
        //返回所用时间
        return endTime - startTime;
    }

    public static long arrayIterator() {
        long startTime = System.currentTimeMillis();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        long time1 = arrayFor();
        long time2 = arrayIterator();

        System.out.println("ArrayList for循环所用时间==" + time1);
        System.out.println("ArrayList 迭代器所用时间==" + time2);
    }

    @Test
    public void arrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            arrayList.add(i + "");
        }
        arrayList.forEach(log::info);
    }

    /**
     * 快速失败演示
     */
    @Test
    public void test6() {
        ArrayList<Integer> integers = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        //获取迭代器
        Iterator<Integer> iterator = integers.iterator();
        //是否存在下一个元素
        while (iterator.hasNext()) {
            //获取下一个元素
            Object next = iterator.next();
            //使用集合的方法 移除一个元素，此时会在next()方法中抛出异常
            integers.remove(0);
        }
    }

    @Test
    public void test7() {
        ArrayList<String> integers = new ArrayList<>(Arrays.asList("1", "2", "3"));
        //for (int i = 0; i < integers.size(); i++) {
        //    String s = integers.get(i);
        //    System.out.println(s);
        //    integers.remove(s);
        //}
        ListIterator<String> iterator = integers.listIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
            iterator.remove();
        }
    }

    @Test
    public void deduplication() {
        ArrayList<String> al = new ArrayList<>();
        al.add("aa");
        al.add("bb");
        al.add("aa");
        al.add("dd");
        al.add("dd");
        al.add("dd");
        al.add(null);
        al.add("ee");
        al.add("ee");
        //去重思路一  借助辅助集合
        //ArrayList<String> al2 = new ArrayList<>();
        //for (String s : al) {
        //    if (!al2.contains(s)) {
        //        al2.add(s);
        //    }
        //}
        //al.clear();
        //al.addAll(al2);
        //System.out.println(al);   //[aa, bb, dd, null, ee]

        ListIterator<String> sli = al.listIterator();
        while (sli.hasNext()) {
            String next =  sli.next();   //获得下一个元素
            sli.remove();   //移除获得的元素
            if (!al.contains(next)){  //判断源集合是否包含被移除的元素
                sli.add(next);  //没包含就再添加进来
            }
        }
        System.out.println(al);
    }

    @Test
    public void test5() {
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3));
        //获取迭代器
        Iterator<Integer> iterator = integers.iterator();
        //是否存在下一个元素
        while (iterator.hasNext()) {
            //获取下一个元素
            Object next = iterator.next();
            //移除下一个元素
            iterator.remove();
        }
        System.out.println("");
    }

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Integer.MAX_VALUE + i);
        }
    }

    @Test
    public void testInt() {
        IntClass intClass = new IntClass();
        intClass.print();
    }

    @Test
    public void test8() {
        CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<Integer>(Arrays.asList(1, 2, 3));
        //获取迭代器
        Iterator<Integer> iterator = integers.iterator();
        //是否存在下一个元素
        while (iterator.hasNext()) {
            //使用集合的方法 移除第一个元素，此时不会在next()方法中抛出异常
            Integer remove = integers.remove(0);
            System.out.println("被移除的: " + remove);
            //获取下一个元素,被移除的元素还是能获取到,正是由于Copy-On-Write技术造成的
            Object next = iterator.next();
            System.out.println("获取到的: " + next);
        }
    }

    @Data
    private static class IntClass {
        private int c;

        public void print() {
        }
    }


    @Test
    public void binary() {
        String s = Integer.toBinaryString(1000);
        System.out.println(s);
    }

    @Test
    public void arrayMove() {
        int[] x = new int[]{1, 2, 3, 4, 5};
        int[] y = new int[10];
        System.arraycopy(x, 0, y, 0, 3);
        System.arraycopy(x, 4, y, 3, 1);
        System.out.println("");
        test3();
    }

    /**
     * 测试数组最大分配长度,这和VM实现,以及堆内存大小有关
     */
    @Test
    public void test3() {
        // 尝试分配Integer.MAX_VALUE-1长的byte数组,将会抛出异常:
        // java.lang.OutOfMemoryError: Requested array size exceeds VM limit
        //表示请求分配的数组长度超过了VM的限制
        //byte[] arr1 = new byte[Integer.MAX_VALUE - 1];
        // 但是分配Integer.MAX_VALUE-2长的byte数组,则不会抛出异常
        //说明本人HotSpot JDK8虚拟机允许分配的数组最大长度为Integer.MAX_VALUE-2
        byte[] arr2 = new byte[Integer.MAX_VALUE - 2];
        //尝试分配Integer.MAX_VALUE-2长的int数组,则直接抛出异常:
        //java.lang.OutOfMemoryError: Java heap space
        //这说明,你具体能够分配多大长度的数组,还要看数组的类型,说白了就是你的JVM的堆空间内存的大小
        int[] arr3 = new int[Integer.MAX_VALUE - 2];
        // 一个 int 站
        //BigDecimal decimal = new BigDecimal(Integer.MAX_VALUE);
        //System.out.println(decimal.multiply(new BigDecimal(4)).divide(new BigDecimal(1024)).divide(new BigDecimal(1024)));
    }

}
