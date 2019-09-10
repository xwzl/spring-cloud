package com.spring.demo.core.large;

import com.spring.demo.util.TestFileUtil;
import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 *
 * @author xuweizhi
 */
public class LargeDataTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LargeDataTest.class);

    @Test
    public void read() {
        long start = System.currentTimeMillis();
        EasyExcel.read(TestFileUtil.getPath() + "large" + File.separator + "large07.xlsx", LargeData.class,
            new LargeDataListener()).headRowNumber(2).sheet().doRead();
        LOGGER.info("Large data total time spent:{}", System.currentTimeMillis() - start);
    }
}
