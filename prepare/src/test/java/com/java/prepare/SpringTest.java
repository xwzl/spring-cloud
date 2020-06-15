package com.java.prepare;

import com.java.prepare.module.Message;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xuweizhi
 * @since 2020/06/15 10:21
 */
public class SpringTest {

    @Test
    public void test(){
        // 用我们的配置文件来启动一个 ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:XmlBeanFactory.xml");
        System.out.println("context 启动成功");
        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        Message messageService = context.getBean(Message.class);
        // 这句将输出: hello world
        System.out.println(messageService.message());
    }

}
