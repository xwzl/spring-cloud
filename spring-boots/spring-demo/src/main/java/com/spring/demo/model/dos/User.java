package com.spring.demo.model.dos;

import com.spring.demo.annotation.MyValidated;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xuweizhi
 * @since 2019-08-06
 */
@Data
public class User {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    //@Size(min = 5, max = 10, message = "年龄必须在 5 到 10 岁之间")
    private String age;

    private Date birthday;

    @MyValidated(value = "diy", message = "这是一个错误信息")
    private String diy;
}
