package com.spring.base.thread.futrue.simplefuture;

/**
 * @author xuweizhi
 */
public class FutureData implements Data {

    private RealData realdata = null;

    private boolean isReady = false;

    synchronized void setRealData(RealData realdata) {
        if (isReady) {
            return;
        }
        this.realdata = realdata;
        isReady = true;
        notifyAll();
    }

    /**
     * 这里阻塞结果
     */
    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realdata.result;
    }
}
