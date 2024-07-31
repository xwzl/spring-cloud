package com.spring.demo.model.vos;

import com.fasterxml.jackson.annotation.JsonView;
import com.spring.demo.view.Validated.BasketBallValidated;
import com.spring.demo.view.Validated.GameValidated;
import com.spring.demo.view.Visible;
import com.spring.demo.view.Visible.GameView;
import lombok.Data;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;

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
    @JsonView(GameView.class)
    @NotNull(message = "id 不能为空", groups = GameValidated.class)
    private String game;

    /**
     * 篮球分组验证，validated 注解指定具体的分组信息
     */
    @JsonView(Visible.BasketBallView.class)
    @NotNull(message = "id 不能为空", groups = BasketBallValidated.class)
    private String basketBall;

}



