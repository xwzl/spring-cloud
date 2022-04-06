package com.spring.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.spring.common.model.common.ResultVO;
import com.spring.common.model.model.vos.ReturnViewVO;
import com.spring.demo.annotation.DateTime;
import com.spring.demo.config.config.RestTemplateConfig;
import com.spring.demo.model.dos.User;
import com.spring.demo.model.vos.TakeValidatedVO;
import com.spring.demo.model.vos.TitleVO;
import com.spring.demo.view.Validated.BasketBallValidated;
import com.spring.demo.view.Validated.GameValidated;
import com.spring.demo.view.Visible.BasketBallView;
import com.spring.demo.view.Visible.GameView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Spring 框架自带验证功能校验
 *
 * @author xuweizhi
 * @since 2019-08-06
 */
@Slf4j
@RestController
@RequestMapping("/validated")
//@Api(tags = "数据校验")
@Validated
public class ValidatedController {

    /**
     * Swagger-ui 传得值有点问题，参数为空的话，不会拼接到空参
     * <p>
     * 一般用来校验String类型不能为空 @NotBlank
     * <p>
     * 一般用来校验Integer类型不能为空 @NotNull
     * <p>
     * 一般用来校验List类型不能为空 @NotEmpty
     *
     * @param name 姓名
     * @param age  年龄
     */
    //@ApiOperation("单个参数校验")
    @PostMapping("/test1")
    public void test(@NotBlank(message = "d") String name, @NotNull(message = "不能为空") @Max(value = 3, message = "不能大于 3") Integer age) {

    }

    @PostMapping
    //@ApiOperation("基本校验")
    public void testValidated(@RequestBody User user, String fix) {
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
     * single parameter check 无效果.postman 测试效果好一些
     */
    @PostMapping("singleParamCheck")
    //@ApiOperation("single parameter check 无效果")
    public void single(@Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间") @NotBlank(message = "id 值不能为空") String id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        log.info(id);
    }

    /**
     * 默认字段验证和返回值可见
     */
    @PostMapping("/default")
    //@ApiOperation("默认字段验证和返回值可见")
    public TakeValidatedVO defaultMethod(@Validated TakeValidatedVO takeValidatedVO) {
        return takeValidatedVO;
    }

    /**
     * 游戏段验证和返回值可见，验证好像没有效果
     */
    @JsonView(GameView.class)
    @PostMapping("/game")
    //@ApiOperation("游戏段验证和返回值可见，验证好像没有效果")
    public TakeValidatedVO game(@Validated(value = GameValidated.class) TakeValidatedVO takeValidatedVO, BindingResult bindingResult) {
        return takeValidatedVO;
    }

    /**
     * 篮球字段验证和返回值可见，验证好像没有效果
     */
    @JsonView(BasketBallView.class)
    @PostMapping("/basketBall")
    //@ApiOperation("篮球字段验证和返回值可见，验证好像没有效果")
    public TakeValidatedVO basketBall(@Validated(value = BasketBallValidated.class) TakeValidatedVO takeValidatedVO, BindingResult bindingResult) {
        return takeValidatedVO;
    }

    @PostMapping("/test")
    //@ApiOperation("自定校验器")
    public String test(@DateTime(message = "您输入的格式错误，正确的格式为：{format}", format = "yyyy-MM-dd HH:mm") String date) {
        return "success";
    }

    /**
     * 如果添加了@JsonView的返回类被重新封装，这个时候这个注解不生效。
     * <p>
     * 还有一个原因就是 RestTemplateConfig 配置 fastJson 的转换 {@link RestTemplateConfig#fastJsonHttpMessageConverter()} 注释掉就可以了
     *
     * @return 测试值
     */
    @GetMapping("/returnView")
    //@ApiOperation("返回值校验")
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
    //@ApiOperation("返回值校验")
    @JsonView(ReturnViewVO.BaseDetails.class)
    public ReturnViewVO returnViewDetails() {
        return new ReturnViewVO("root", "admin");
    }

    /**
     * 正则表达式路径匹配 name:a,version:1.0.0,ext:.aaaa
     * <p>
     * /validated/a-1.0.0.aaaa
     *
     * @param name    名字
     * @param version 版本号
     * @param ext     额外参数
     */
    @GetMapping("/{name:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{ext:\\.[a-z]+}")
    public void handle(@PathVariable String name, @PathVariable String version, @PathVariable String ext) {
        log.info("name:{},version:{},ext:{}", name, version, ext);
    }

    /**
     * 限定参数相当于限定 myParam 值为 myValue
     * <p>
     * params: 可写 = , != 表达式对值做限定
     * headers: myHeader
     *
     * @param petId id
     */
    //@ApiOperation("参数校验问题")
    @GetMapping(path = "/pets/{petId}", headers = "test", params = {"myParam=myValue", "username"})
    public void findPet(@PathVariable String petId, String myHeader, String username) {
        log.info("petId={},myHeader={}", petId, myHeader);
    }


    /**
     * 矩阵变量：官网 demo 不行
     *
     * @param id       id
     * @param username 名称
     * @param tables   列表
     * @return 返回值
     */
    //@ApiOperation("矩阵变量")
    @GetMapping("/variable/{id}/{tables}")
    public ResultVO<Map<String, String>> matrixVariable(@PathVariable String id, @PathVariable List<String> tables, @MatrixVariable String username) {
        Map<String, String> response = new HashedMap<>();
        response.put("ids", id);
        response.put("username", username);
        response.put("tables", tables.toString());
        return new ResultVO<>(response);
    }


    /**
     * 测试视图简单分组
     *
     * @return 返回值
     */
    //@ApiOperation("测试视图简单分组")
    @GetMapping("simple")
    @JsonView(TitleVO.Simple.class)
    public TitleVO simpleView() {
        return new TitleVO("simple", "details");
    }

    /**
     * 测试视图详细分组
     *
     * @return 返回值
     */
    //@ApiOperation("测试视图详细分组")
    @GetMapping("details")
    @JsonView(TitleVO.Details.class)
    public TitleVO detailsView() {
        return new TitleVO("simple", "details");
    }

    /**
     * 保存吗？
     *
     * @param titleVO 好
     * @return 1
     */
    @GetMapping("saveTile")
    public TitleVO saveTile(TitleVO titleVO) {
        return titleVO;
    }
}
