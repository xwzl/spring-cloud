package com.java.prepare.until.java;

class VolatileBarrierExample {
    int a;
    volatile int v1 = 1;
    volatile int v2 = 2;

    void readAndWrite() {
        int i = v1; // 第一个 volatile 读
        int j = v2; // 第二个 volatile 读
        a = i + j; // 普通写
        v1 = i + 1; // 第一个 volatile 写
        v2 = j * 2; // 第二个 volatile 写
    }

}
