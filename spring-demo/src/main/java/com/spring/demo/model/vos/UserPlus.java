package com.spring.demo.model.vos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "用户实体")
public class UserPlus implements Serializable {

    @Schema(description = "用户id")
    private Integer id;
    
    @Schema(description = "用户昵称")
    private String nickname;
    
    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户密码")
    private String password;

}

