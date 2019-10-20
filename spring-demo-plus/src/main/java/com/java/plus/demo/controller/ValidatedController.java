package com.java.plus.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.java.plus.demo.view.Validated.BasketBallValidated;
import com.java.plus.demo.view.Validated.GameValidated;
import com.java.plus.demo.view.Visible;
import com.java.plus.demo.vos.ReturnViewVO;
import com.java.plus.demo.vos.TakeValidatedVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Spring 框架自带验证功能校验
 *
 * @author xuweizhi
 * @since 2019-08-06
 */
@Slf4j
@RestController
@RequestMapping("/validated")
@Api(tags = "数据校验")
public class ValidatedController {

    /**
     * Swagger-ui 传得值有点问题，参数为空的话，不会拼接到空参
     *
     * @param name 姓名
     * @param age  年龄
     */
    @ApiOperation("单个参数校验")
    @PostMapping("/test1")
    public void test(@NotBlank(message = "d") String name, @NotNull(message = "不能为空") @Max(value = 3, message = "不能大于 3") Integer age) {

    }


    /**
     * 默认字段验证和返回值可见
     */
    @PostMapping("/default")
    @ApiOperation("默认字段验证和返回值可见")
    public TakeValidatedVO defaultMethod(@Validated TakeValidatedVO takeValidatedVO) {
        return takeValidatedVO;
    }

    /**
     * 游戏段验证和返回值可见，验证好像没有效果
     */
    @JsonView(Visible.GameView.class)
    @PostMapping("/game")
    @ApiOperation("游戏段验证和返回值可见，验证好像没有效果")
    public TakeValidatedVO game(@Validated(value = GameValidated.class) TakeValidatedVO takeValidatedVO, BindingResult bindingResult) {
        return takeValidatedVO;
    }

    /**
     * 篮球字段验证和返回值可见，验证好像没有效果
     */
    @JsonView(Visible.BasketBallView.class)
    @PostMapping("/basketBall")
    @ApiOperation("篮球字段验证和返回值可见，验证好像没有效果")
    public TakeValidatedVO basketBall(@Validated(value = BasketBallValidated.class) TakeValidatedVO takeValidatedVO, BindingResult bindingResult) {
        return takeValidatedVO;
    }

    /**
     * 如果添加了@JsonView的返回类被重新封装，这个时候这个注解不生效。
     *
     * @return 测试值
     */
    @GetMapping("/returnView")
    @ApiOperation("返回值校验")
    @JsonView(ReturnViewVO.Base.class)
    public ReturnViewVO returnView() {
        return new ReturnViewVO("root", "admin");
    }

    /**
     * 如果添加了@JsonView的返回类被重新封装，这个时候这个注解不生效。
     * 不知道什么原因失效了
     *
     * @return 测试值
     */
    @GetMapping("/returnViewDetails")
    @ApiOperation("返回值校验详情")
    @JsonView(ReturnViewVO.BaseDetails.class)
    public ReturnViewVO returnViewDetails() {
        return new ReturnViewVO("root", "admin");
    }
}
