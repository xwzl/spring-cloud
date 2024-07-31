package com.spring.demo.model.dos;

import com.spring.demo.annotation.MyValidated;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xuweizhi
 * @since 2019-08-06
 */
@Data
@Slf4j
public class User {

    @NotNull(message = "用户名不能为空")
    @Length(max = 20, message = "用户名不能超过20个字符")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    //@Size(min = 5, max = 10, message = "年龄必须在 5 到 10 岁之间")
    //@Range(max = 150, min = 1, message = "年龄范围应该在1-150内。")
    private String age;

    private Date birthday;

    @MyValidated(value = "diy", message = "这是一个错误信息")
    private String diy;


    /**
     * 用户ID
     */
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")


    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String mobile;

    /**
     * 性别
     */
    private String sex;

    @NotBlank(message = "name 不允许为空")
    @Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间")
    private String name;

    @NotNull(message = "price 不允许为空")
    @DecimalMin(value = "0.1", message = "价格不能低于 {value}")
    private BigDecimal price;

    /**
     * 邮箱
     */
    @NotBlank(message = "联系邮箱不能为空")
    @Email(message = "邮箱格式不对")
    private String email;


    /*** 创建时间 */
    @Future(message = "时间必须是将来时间")
    private Date createTime;
}

