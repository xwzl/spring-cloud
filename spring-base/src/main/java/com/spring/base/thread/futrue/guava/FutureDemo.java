package com.spring.base.thread.futrue.guava;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.spring.base.thread.futrue.RealData;
import com.spring.base.thread.pool.ThreadPoolUtils;

/**
 * @author xuweizhi
 */
public class FutureDemo {

    public static void main(String[] args) throws InterruptedException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(ThreadPoolUtils.noQueue());

        ListenableFuture<String> task = service.submit(new RealData("x"));

        task.addListener(() -> {
            System.out.print("异步处理成功:");
            try {
                System.out.println(task.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, MoreExecutors.directExecutor());

        System.out.println("main task done.....");
        Thread.sleep(3000);
    }
}
