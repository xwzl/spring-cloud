package com.spring.demo.model.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Contract;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author xwz
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
public class People implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("人主键")
    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer uId;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("公寓")
    private String apartment;

    /**
     * 无法解决Redis 序列化问题 @JsonFormat(pattern = "yyyy-MM-dd")
     */
    @ApiModelProperty("创建时间")
    //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    //@JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("电话号码")
    private String phoneNumber;

    @ApiModelProperty("角色")
    private Integer role;

    @ApiModelProperty("用户名")
    private String username;

    @Contract(pure = true)
    public People() {
    }

}
