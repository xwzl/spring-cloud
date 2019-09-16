package com.spring.base.thread.futrue.simplefuture;

/**
 * @author xuweizhi
 */
public class Client {

    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        // RealData的构建很慢
        new Thread(() -> {
            RealData realdata = new RealData(queryStr);
            future.setRealData(realdata);
        }).start();
        return future;
    }

}
