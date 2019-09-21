package com.spring.demo.config.properties;

import lombok.Data;

/**
 * import 方式属性注入创建 bean
 *
 * @author xuweizhi
 * @since 2019/09/21 13:49
 */
@Data
public class ImportProperties {

    public void say() {
        System.out.println("import 向 ioc 容器中注册普通的bean");
    }
}
