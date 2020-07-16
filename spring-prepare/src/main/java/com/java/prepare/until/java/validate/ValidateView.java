package com.java.prepare.until.java.validate;

import com.spring.common.model.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Validate 可见性
 *
 * @author xuweizhi
 * @since 2020/07/06 10:55
 */
@Slf4j
public class ValidateView {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static boolean flag= false;
    private static int  i=0;
    /**
     * 可见性验证: volatile 每次使用时都会从主内存中获取最新值
     */
    @Test
    public void volatileView() {
        ValidateMark mark = new ValidateMark();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.info(ExceptionUtils.getStackTrace(e));
                throw new ServiceException(500, "中断异常");
            }
            // isStop 的值已经写回主内存
            mark.stop();
            log.info("终止程序");
        }).start();

        while (true) {
            // isStop 由于一直从私有线程中获取值，没有从主内存中获取更新值，因此一直无法终止。
            if (mark.isStop) {
                break;
            }
        }
        log.info("退出程序");
    }

    @Test
    public void volatileNotAtomic() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(10);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(asynchronous(count, i)));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        count.await();
        // 计数器代替 sleep
        // Thread.sleep(1000);
        log.info("counter {}", ValidateNotAtomic.COUNTER);
    }

    @Test
    public void synchronousByAtomicInteger() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(10);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(synchronousByAtomicInteger(count, i)));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        count.await();
        // 计数器代替 sleep
        // Thread.sleep(1000);
        log.info("counter {}", ValidateNotAtomic.atomicInteger);
    }

    @Test
    public void test(){

    }

    private Runnable synchronousByAtomicInteger(CountDownLatch count, int index) {
        return () -> {
            // 数值大一些，不然有可能看不到结果
            for (int j = 0; j < 10000; j++) {
                // ++ 非原子操作，并发情况下有可能造成自增错误
                ValidateNotAtomic.atomicInteger.getAndIncrement();
            }
            log.info("index: {}", index);
            count.countDown();
        };
    }

    private Runnable asynchronous(CountDownLatch count, int index) {
        return () -> {
            // 数值大一些，不然有可能看不到结果
            for (int j = 0; j < 10000; j++) {
                // ++ 非原子操作，并发情况下有可能造成自增错误
                ValidateNotAtomic.COUNTER++;
                //while (true) {
                //    int counter = ValidateNotAtomic.COUNTER;
                //    int result = counter + 1;
                //    if (result == ValidateNotAtomic.COUNTER + 1) {
                //        ValidateNotAtomic.COUNTER = result;
                //        break;
                //    }
                //}
            }
            log.info("index: {}", index);
            count.countDown();
        };
    }

    private Runnable synchronous(CountDownLatch count, int index) {
        return () -> {
            try {
                int i = 0;
                while (true) {
                    log.info(Thread.currentThread().getName() + " 进入等待");
                    boolean b = lock.tryLock(3, TimeUnit.SECONDS);
                    if (b) {
                        log.info(Thread.currentThread().getName() + " 当前线程获得锁,当前计数为 {}", ValidateNotAtomic.COUNTER);
                        condition.signalAll();
                        for (int j = 0; j < 500; j++) {
                            if (j == 200) {
                                log.info(Thread.currentThread().getName() + " 当前计数为 {}，执行时间过长,释放锁让其他线程来做", ValidateNotAtomic.COUNTER);
                                condition.await();
                                log.info(Thread.currentThread().getName() + " 当前计数为 {}，接着干活", ValidateNotAtomic.COUNTER);
                            }
                            Thread.sleep((long) (Math.random() * 10));
                            ValidateNotAtomic.COUNTER++;
                        }
                        log.info(Thread.currentThread().getName() + " 当前计数为 {},结束当前线程任务", ValidateNotAtomic.COUNTER);
                        condition.signalAll();

                        log.info(Thread.currentThread().getName() + " index: {}", index);
                        count.countDown();
                        break;
                    } else if (i > 3) {
                        count.countDown();
                        throw new InterruptedException(Thread.currentThread().getName() + "到达最长重试时间，终止该任务");
                    }
                    log.info(Thread.currentThread().getName() + " 获取锁失败，进行第 {} 次 尝试", i + 1);
                    i++;
                }
            } catch (InterruptedException e) {
                log.error(ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        };
    }

}

class ValidateNotAtomic {
    // volatile 不保证变量原子性
    // volatile 其实际行为是对主内中的变量进行加锁，对应 jdk 中的原子操作 lock 和 unlock
    //
    public static volatile int COUNTER = 0;
    public static AtomicInteger atomicInteger = new AtomicInteger();

    public ValidateNotAtomic() {
        throw new RuntimeException("不能被创建");
    }
}

class ValidateMark {

    /**
     * 在服务环境中，运行jvm时加入参数server，就会出现无限死循环
     * <p>
     * 是什么样的原因造成 JVM 设置为 -server 就出现是循环呢？在 ValidateMark.java 对象中变量 isStop = false ;存在于公共堆栈
     * 以及线程有得私有堆栈中。在 JVM 被设置为 -server 模式时为了线程运行的效率，线程一直在私有堆栈中取得 isStop 的值为 false。
     * 而代码 mark.stop();虽然被执行，更新的却是公共堆栈中的 isStop 变量值 false,所以一直就是死循环的状态。
     * <p>
     * 这个问题其实就是私有堆栈中的值和公共堆栈中的值不同步造成的。解决这样的就在于使用 volatile 关键字了，它的主要作用就是当线程访
     * 问 isStop 这个变量时，强制从公共堆栈中获取数据。
     * </p>
     * 使用volatile关键字增加了实例变量在多个线程之间的可见性。但是 volatile 关键字最致命的缺点是不支持原子性:
     * 1. 关键字volatile是线程同步的轻量级实现，所以volatile性能肯定比synchronized要好，并且volatile执行修饰变量，而synchronized
     * 可以修饰方法。以及代码块。随着JDK新版本的发布，synchronized关键字在执行效率上得到很大提升，在开发中使用synchronized的比率还是
     * 比较大的。
     * 2. 多线程访问volatile不会发生阻塞，而synchronized会发生阻塞。
     * 3. volatile保证了数据的可见性，但不保证原子性。而synchronized可以保证原子性，也可以间接保证可见性。因为它会将私有内存和公共内存中
     * 的数据做同步。
     * 4. 再次重申一次，关键字volatile是解决变量在多个线程之间的可见性。而synchronized关键字解决的是多个线程之间访问资源的同步性。
     * 线程安全包含原子性和可见性两个方面，Java的同步机制都是围绕这两个方面来确定线程安全的。
     */
    //private volatile boolean isStop = false;
    protected boolean isStop = false;

    public void stop() {
        this.isStop = true;
    }
}
