package com.spring.demo.controller;

import com.spring.demo.mapper.EmpMapper;
import com.spring.common.model.common.ApiResult;
import com.spring.demo.model.vos.EmpVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * mysql sql 测试
 *
 * @author xuweizhi
 * @since 2019/12/05 17:06
 */
@Api(tags = "mysql 测试类")
@RestController
@RequestMapping("mysql")
public class MysqlController {

    @Resource
    private EmpMapper empMapper;

    /**
     * 内连接
     *
     * @return 结果集
     */
    @GetMapping("inner")
    @ApiOperation(value = "内连接", notes = "默认连接方式")
    public ApiResult<List<EmpVO>> inner() {
        return new ApiResult<>(empMapper.inner());
    }

    /**
     * 内连接 on
     *
     * @return 结果集
     */
    @GetMapping("innerOn")
    @ApiOperation(value = "内连接 on", notes = "on")
    public ApiResult<List<EmpVO>> innerOn() {
        return new ApiResult<>(empMapper.innerOn());
    }

    /**
     * 内连接 where
     *
     * @return 结果集
     */
    @GetMapping("innerWhere")
    @ApiOperation(value = "内连接 where", notes = "where")
    public ApiResult<List<EmpVO>> innerWhere() {
        return new ApiResult<>(empMapper.innerWhere());
    }

    /**
     * 左连接 where
     *
     * @param id 主键 1
     * @return 结果集
     */
    @GetMapping("leftOn")
    @ApiOperation(value = "左连接 on", notes = "on")
    public ApiResult<List<EmpVO>> leftOn(String id) {
        return new ApiResult<>(empMapper.leftOn(id));
    }

    /**
     * 左连接 where
     *
     * @param id 主键 1
     * @return 结果集
     */
    @GetMapping("leftWhere")
    @ApiOperation(value = "左连接 where", notes = "where")
    public ApiResult<List<EmpVO>> leftWhere(String id) {
        return new ApiResult<>(empMapper.leftWhere(id));
    }

    /**
     * 自连接
     *
     * @return 结果集
     */
    @GetMapping("joinSelf")
    @ApiOperation(value = "自连接")
    public ApiResult<List<EmpVO>> joinSelf() {
        return new ApiResult<>(empMapper.joinSelf());
    }

    /**
     * IFNULL 判断
     *
     * @return 结果集
     */
    @GetMapping("ifNull")
    @ApiOperation(value = " IFNULL 判断")
    public ApiResult<String> ifNull(String id) {
        return new ApiResult<>(empMapper.ifNull(id));
    }

    /**
     * if 条件判断
     *
     * @return 结果集
     */
    @GetMapping("isNull")
    @ApiOperation(value = "if 条件判断")
    public ApiResult<Boolean> isNull(String id) {
        return new ApiResult<>(empMapper.isNull(id));
    }
}
