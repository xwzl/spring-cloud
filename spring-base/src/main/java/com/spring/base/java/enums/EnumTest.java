package com.spring.base.java.enums;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author xuweizhi
 * @since 2020/03/26 10:49
 */
@Slf4j
public class EnumTest {

    /**
     * 测试枚举类型
     */
    @Test
    public void test() {
        log.info(SexEnum.MAN + "");
        log.info(SexEnum.MAN.name());
        log.info(SexEnum.MAN.name().getClass().getName());
        log.info(SexEnum.MAN.getClass().getName());
    }

    /**
     * <h1>使用 == 比较枚举类型</h1>
     * <p>
     * 由于枚举类型确保JVM中仅存在一个常量实例，因此我们可以安全地使用“ ==”运算符比较两个变量，如上例所示；
     * 此外，“ ==”运算符可提供编译时和运行时的安全性。
     * <br/><br/>
     * 首先，让我们看一下以下代码段中的运行时安全性，其中“ ==”运算符用于比较状态，并且如果两个值均为null 都
     * 不会引发 NullPointerException。相反，如果使用equals方法，将抛出 NullPointerException：
     */
    @Test
    public void equalsTest() throws Exception {
        String nullStr = null;
        String valueStr = null;
        log.info("boolean:{}", nullStr == valueStr);
        // 空指针异常
        //log.info("boolean:{}", nullStr.equals(valueStr));

        Integer thousand = 1000;
        Integer thousandValue = 1000;
        System.out.println(thousand == thousandValue);
        System.out.println(thousand.equals(thousandValue));
        System.out.println(thousand.hashCode());
        System.out.println(thousandValue.hashCode());
        long l = Address.addressOf(thousand);
        long l1 = Address.addressOf(thousandValue);
        System.out.println(l + ":" + l1);
    }
}
