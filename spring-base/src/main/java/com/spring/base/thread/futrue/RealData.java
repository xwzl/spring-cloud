package com.spring.base.thread.futrue;

import org.jetbrains.annotations.Contract;

import java.util.concurrent.Callable;

/**
 * @author xuweizhi
 */
public class RealData implements Callable<String> {

    private String para;

    @Contract(pure = true)
    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        return sb.toString();
    }
}
