package com.spring.demo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.spring.demo.model.excel.ComputerModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 * @since 2019/09/10 9:54
 */
@Slf4j
@Data
public class ComputerListener extends AnalysisEventListener<ComputerModel> {

    /**
     * 数据实体集合
     */
    private List<ComputerModel> data = new ArrayList<>();

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;

    /**
     * 都会调用呢？还是写调用呢？
     *
     * @param computerModel 数据实体
     * @param context       分析对象
     */
    @Override
    public void invoke(ComputerModel computerModel, AnalysisContext context) {
        //log.info("解析到一条数据:{}", computerModel.toString());
        data.add(computerModel);
        //if (data.size() > BATCH_COUNT) {
        //    save();
        //}
    }

    private void save() {

        data.clear();
        log.info("保存数据到数据库中......");
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //save();
        log.info("所有数据保存成功");
    }
}
