package com.spring.base.thread.futrue.simplefuture;

/**
 * @author xuweizhi
 */
public class RealData implements Data {

    final String result;

    RealData(String para) {
        //RealData的构造可能很慢，需要用户等待很久
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
