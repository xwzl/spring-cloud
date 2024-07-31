package com.spring.demo.controller;


import com.spring.demo.model.dos.Emp;
import com.spring.demo.model.dos.Role;
import com.spring.demo.service.RoleService;
import io.swagger.annotations.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * swagger ui html 测试
 *
 * @author xuweizhi
 * @since 2019-07-25
 */
@RestController
@RequestMapping("/demo/role")
//@Api(tags = "Swagger2 注解示例")
@Slf4j
public class SwaggerController {

    @Resource
    private RoleService roleService;


    @PostMapping
    public void testPost(String id) {
        System.out.println(id);
    }

    /**
     * _//@ApiOperation："用在请求的方法上，说明方法的作用"
     * value="说明方法的作用"
     * notes="方法的备注说明"
     * <p>
     * _@ApiImplicitParams：用在请求的方法上，包含一组参数说明
     * _    @ApiImplicitParam：用在 @ApiImplicitParams 注解中，指定一个请求参数的配置信息
     * name：参数名
     * value：参数的汉字说明、解释
     * required：参数是否必须传
     * paramType：参数放在哪个地方
     * · header --> 请求参数的获取：@RequestHeader
     * · query --> 请求参数的获取：@RequestParam
     * · path（用于restful接口）--> 请求参数的获取：@PathVariable
     * · body（不常用）
     * · form（不常用）
     * dataType：参数类型，默认String，其它值dataType="Integer"
     * defaultValue：参数的默认值
     * <p>
     * _@ApiResponses：用于请求的方法上，表示一组响应
     * _    @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
     * code：数字，例如400
     * message：信息，例如"请求参数没填好"
     * response：抛出异常的类
     */
    @GetMapping
    //@ApiOperation(value = "用户注册", notes = "手机号、密码都是必输项，年龄随边填，但必须是数字", response = Role.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, paramType = "form", dataType = "Integer"),
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public List<Role> getRoleList(@RequestParam() String mobile, String password, String age, Role role) {

        return roleService.list();
    }

    @GetMapping("swagger")
    public void swagger(Role role, Emp emp) {
        log.info(role.toString());
        log.info(emp.toString());
    }

}
