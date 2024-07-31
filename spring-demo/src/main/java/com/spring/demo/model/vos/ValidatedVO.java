package com.spring.demo.model.vos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * 验证测试
 *
 * @author xuweizhi
 * @since 2019-08-21
 */
@Data
@ToString
@ApiModel("这是一个验证 VO 测试")
public class ValidatedVO {

    @ApiModelProperty("姓名")
    @NotNull(message = "字符串不能为空测试")
    public String username;

    @Max(value = 150, message = "最大年龄不能超过 150 岁")
    @Min(value = 0, message = "最小年龄不能小于 10 岁")
    public Integer age;

}
