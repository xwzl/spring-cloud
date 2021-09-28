package com.spring.framework.life.imports;

@SuppressWarnings("all")
public class CustomizeServiceImpl1 implements CustomizeService1 {

    public CustomizeServiceImpl1() {
        System.out.println("construct : " + this.getClass().getSimpleName());
    }

    @Override

    public void execute() {
        System.out.println("execute : " + this.getClass().getSimpleName());
    }
}
