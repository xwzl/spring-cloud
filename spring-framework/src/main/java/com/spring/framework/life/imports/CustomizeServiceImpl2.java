package com.spring.framework.life.imports;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("all")
public class CustomizeServiceImpl2 implements CustomizeService1 {

    public CustomizeServiceImpl2() {
        log.info("construct : " + this.getClass().getSimpleName());
    }

    @Override
    public void execute() {
        log.info("execute : " + this.getClass().getSimpleName());
    }
}
