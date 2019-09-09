package com.spring.base.thread.futrue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xuweizhi
 * @since 2019/08/24 12:01
 */
public class FutureCook {

    /**
     * cancel方法用来取消任务，如果取消任务成功则返回true，如果取消任务失败则返回false。参数mayInterruptIfRunning表
     * 示是否允许取消正在执行却没有执行完毕的任务，如果设置true，则表示可以取消正在执行过程中的任务。如果任务已经完成，则无论
     * mayInterruptIfRunning为true还是false，此方法肯定返回false，即如果取消已经完成的任务会返回false。
     * <p>
     * 有可能会出现 调用 cancel 方法 和 isDone 方法返回均返回 true， 但是任务还在执行的情况。cancel方法有个参数mayInterruptIfRunning，
     * 如果是false的话意味着不能中断正在运行的代码，调用cancel返回true。isDone方法就是判断任务开始了没，只要开始了调用isDone就会返回true。
     * <p>
     * isCancelled方法表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true。
     * <p>
     * isDone方法表示任务是否已经完成，若任务完成，则返回true；
     * <p>
     * get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回；
     * <p>
     * get(long timeout, TimeUnit unit)用来获取执行结果，如果在指定时间内，还没获取到结果，就抛出 TimeoutException 异常。
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        // 第一步 网购厨具
        // Callable接口可以看作是Runnable接口的补充，call方法带有返回值，并且可以抛出异常。
        Callable<Chuju> onlineShopping = () -> {
            System.out.println("第一步：下单");
            System.out.println("第一步：等待送货");
            Thread.sleep(5000);  // 模拟送货时间
            System.out.println("第一步：快递送到");
            return new Chuju();
        };
        // 继承了 Runnable 和 Future 接口
        // 这个继承体系中的核心接口是Future。Future的核心思想是：一个方法f，计算过程可能非常耗时，等待f返回，显
        // 然不明智。可以在调用f的时候，立马返回一个Future，可以通过Future这个数据结构去控制方法f的计算过程。
        // 把Callable实例当作参数，生成一个FutureTask的对象，然后把这个对象当作一个Runnable，作为参数另起线程。
        FutureTask<Chuju> task = new FutureTask<Chuju>(onlineShopping);
        new Thread(task).start();

        // 第二步 去超市购买食材
        // 模拟购买食材时间
        Thread.sleep(2000);
        Shicai shicai = new Shicai();
        System.out.println("第二步：食材到位");
        // 第三步 用厨具烹饪食材
        // 联系快递员，询问是否到货
        if (!task.isDone()) {
            System.out.println("第三步：厨具还没到，心情好就等着（心情不好就调用cancel方法取消订单）");
        }

        // 得到 future 回调的返回值，直到阻塞完毕 哈哈哈
        Chuju chuju = task.get();
        System.out.println("第三步：厨具到位，开始展现厨艺");
        cook(chuju, shicai);

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 用厨具烹饪食材
     */
    static void cook(Chuju chuju, Shicai shicai) {
    }

    /**
     * 厨具类
     */
    static class Chuju {
    }

    /**
     * 食材类
     */
    static class Shicai {
    }
}

