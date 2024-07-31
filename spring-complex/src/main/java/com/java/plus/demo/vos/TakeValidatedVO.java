package com.java.plus.demo.vos;

import com.fasterxml.jackson.annotation.JsonView;
import com.java.plus.demo.view.Validated;
import com.java.plus.demo.view.Visible;
import lombok.Data;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;

/**
 * 默认分组验证
 *
 * @author xuweizhi
 * @since 2019/09/14 15:52
 */
@Data
@ToString
public class TakeValidatedVO {

    /**
     * 游戏分组验证，validated 注解指定具体的分组信息
     */
    @JsonView(Visible.GameView.class)
    @NotBlank(message = "id 不能为空", groups = Validated.GameValidated.class)
    private String game;

    /**
     * 篮球分组验证，validated 注解指定具体的分组信息
     */
    @JsonView(Visible.BasketBallView.class)
    @NotBlank(message = "id 不能为空", groups = Validated.BasketBallValidated.class)
    private String basketBall;

}



