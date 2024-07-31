package com.spring.demo.model.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import   io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.Contract;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xwz
 * @since 2019-04-22
 */
@Data
@AllArgsConstructor
public class People implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "人主键")
    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer uId;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "公寓")
    private String apartment;

    /**
     * 无法解决Redis 序列化问题 @JsonFormat(pattern = "yyyy-MM-dd")
     */
    @Schema(description = "创建时间")
    //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    //@JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    @Schema(description = "密码")
    private String password;

    @Schema(description ="电话号码")
    private String phoneNumber;

    @Schema(description ="角色")
    private Integer role;

    @Schema(description ="用户名")
    private String username;

    @Contract(pure = true)
    public People() {
    }

}
