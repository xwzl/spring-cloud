package com.spring.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.spring.demo.annotation.DateTime;
import com.spring.demo.model.dos.User;
import com.spring.demo.model.vos.TakeValidatedVO;
import com.spring.demo.view.Validated.BasketBallValidated;
import com.spring.demo.view.Validated.GameValidated;
import com.spring.demo.view.Visible.BasketBallView;
import com.spring.demo.view.Visible.GameView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

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

    @GetMapping
    @ApiOperation("基本校验")
    public void testValidated(@Validated User user, String fix) {
        // 全局处理异常已处理
        //if (bindingResult.hasErrors()) {
        //    FieldError age = bindingResult.getFieldError("age");
        //    bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        //    throw new ServiceException(age.getDefaultMessage());
        //}
        log.info(user.toString());
        System.out.println(fix);
    }

    /**
     * single parameter check 无效果
     */
    @GetMapping("singleParamCheck")
    @ApiOperation("single parameter check 无效果")
    public void single(@Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间") @NotBlank(message = "id 值不能为空") String id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        log.info(id);
    }

    /**
     * 默认字段验证和返回值可见
     */
    @GetMapping("/default")
    @ApiOperation("默认字段验证和返回值可见")
    public TakeValidatedVO defaultMethod(@Validated TakeValidatedVO takeValidatedVO) {
        return takeValidatedVO;
    }

    /**
     * 游戏段验证和返回值可见，验证好像没有效果
     */
    @JsonView(GameView.class)
    @GetMapping("/game")
    @ApiOperation("游戏段验证和返回值可见，验证好像没有效果")
    public TakeValidatedVO game(@Validated(value = GameValidated.class) TakeValidatedVO takeValidatedVO, BindingResult bindingResult) {
        return takeValidatedVO;
    }

    /**
     * 篮球字段验证和返回值可见，验证好像没有效果
     */
    @JsonView(BasketBallView.class)
    @GetMapping("/basketBall")
    @ApiOperation("篮球字段验证和返回值可见，验证好像没有效果")
    public TakeValidatedVO basketBall(@Validated(value = BasketBallValidated.class) TakeValidatedVO takeValidatedVO, BindingResult bindingResult) {
        return takeValidatedVO;
    }

    @GetMapping("/test")
    @ApiOperation("自定校验器")
    public String test(@DateTime(message = "您输入的格式错误，正确的格式为：{format}", format = "yyyy-MM-dd HH:mm") String date) {
        return "success";
    }

}
