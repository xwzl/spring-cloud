package com.spring.base.thread.disruptor.feeling;

import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;

/**
 * 手机购买者
 *
 * @author xuweizhi
 * @since 2019/09/16 21:18
 */
@Slf4j
public class Consumer implements WorkHandler<Phone> {

    private String consumerName;

    @Contract(pure = true)
    Consumer(String consumerName) {
        this.consumerName = consumerName;
    }

    @Override
    public void onEvent(Phone phone) throws Exception {
        Thread.sleep(2000);
        log.info("{}被{}购买了,其手机编号为{}", phone.getBrand(), consumerName, phone.getId());
    }


}
