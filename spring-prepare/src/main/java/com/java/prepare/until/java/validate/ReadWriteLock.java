package com.java.prepare.until.java.validate;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 类ReentrantLock具有完全排他的效果，即同一时间只有一个线程在执行ReentrantLock.lock()方法后面的任务，这样做虽然保证了实例变量的线程安全，
 * 但效率却非常低下的，所以在JDK中提供了一种读写锁ReentrantWriteLock,使用它可以加快运行效率，在某些不需要操作实例变量的方法中，完全可以使用
 * 读写锁ReentrantWriteLock来提升该代码的运行速度。
 * <p>
 * 读写锁表示也有两个锁，一个是读操作相关的锁，也成为共享锁；另一个是写操作相关的锁，也叫排他锁。也就是多个读锁之间不互斥，写锁与写锁互斥，写锁
 * 与读锁互斥。在没有线程Thread进行写入操作时，进行读取操作的多个Thread可以获取读锁。而进行写入操作的Thread只有在获取写锁后才能进行写入操作。
 * 即多个Thread可以同时进行读取操作，但是同一时刻只允许一个Thread进行写入操作。
 *
 * @author xuweizhi
 * @since 2019/01/03 13:24
 */
public class ReadWriteLock {

}

class ReadShare {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println("获取读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    //允许多个线程同时执行读的操作
    public static void main(String[] args) {
        ReadShare writeShare = new ReadShare();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(writeShare::read);
        executor.execute(writeShare::read);
        executor.execute(writeShare::read);
        executor.execute(writeShare::read);
        executor.execute(writeShare::read);
    }

}

class WriteShare {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 写锁互斥，等待其他线程执行后面的代码
     */
    public void write() {
        try {
            lock.writeLock().lock();
            System.out.println("写锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println("获取读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 读锁和写锁互斥，写锁和写锁互斥，相同时间内只允许单独线程执行相应代码
     */
    public static void main(String[] args) {
        WriteShare writeShare = new WriteShare();
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10; i++) {
            executor.execute(writeShare::write);
        }
        for (int i = 0; i < 100; i++) {
            executor.execute(writeShare::read);
        }
    }

}
