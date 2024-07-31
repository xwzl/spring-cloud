package com.spring.demo.model.vos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2023/7/8 12:28
 * @since knife4j-spring-boot3-demo
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "账户登陆请求参数")
public class AuthenticationRequest implements Serializable {

    private static final long serialVersionUID = 5147016262318227459L;

    @Schema(description = "用户账户", example = "ssadmin", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @Schema(description = "用户密码", example = "test123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}
