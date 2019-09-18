package com.spring.demo;

import com.spring.demo.config.async.AsyncTask;
import com.spring.demo.untils.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xuweizhi
 * @since 2019-08-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTest {


    @Test
    public void test1() {
        AsyncTask bean = SpringContextUtil.getBean(AsyncTask.class);
        System.out.println("");
    }
}
