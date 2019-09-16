package com.spring.base.thread.disruptor;


import com.lmax.disruptor.EventFactory;

/**
 * 产生 PcDate 的工厂，在 Disruptor 框架系统初始化时，构造所有得缓冲区中的对象实例（Disruptor 会预先分配空间）
 *
 * @author xuweizhi
 */
public class PcDataFactory implements EventFactory<PcData> {

    @Override
    public PcData newInstance() {
        return new PcData();
    }

}
