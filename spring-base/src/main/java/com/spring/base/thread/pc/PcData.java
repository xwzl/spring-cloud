package com.spring.base.thread.pc;

/**
 * @author xuweizhi
 */
public final class PcData {

    private final int intData;

    PcData(int d) {
        intData = d;
    }

    public PcData(String d) {
        intData = Integer.parseInt(d);
    }

    int getData() {
        return intData;
    }

    @Override
    public String toString() {
        return "data:" + intData;
    }
}
