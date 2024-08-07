package com.spring.demo.controller;

import com.spring.common.model.common.ResultVO;
import com.spring.demo.mapper.EmpMapper;
import com.spring.demo.model.vos.EmpVO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * mysql sql 测试
 *
 * @author xuweizhi
 * @since 2019/12/05 17:06
 */
//@Api(tags = "mysql 测试类")
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
    @Operation(summary   = "内连接")
    public ResultVO<List<EmpVO>> inner() {
        return new ResultVO<>(empMapper.inner());
    }

    /**
     * 内连接 on
     *
     * @return 结果集
     */
    @GetMapping("innerOn")
    @Operation(summary   = "内连接 on")
    public ResultVO<List<EmpVO>> innerOn() {
        return new ResultVO<>(empMapper.innerOn());
    }

    /**
     * 内连接 where
     *
     * @return 结果集
     */
    @GetMapping("innerWhere")
    @Operation(summary   = "内连接 where")
    public ResultVO<List<EmpVO>> innerWhere() {
        return new ResultVO<>(empMapper.innerWhere());
    }

    /**
     * 左连接 where
     *
     * @param id 主键 1
     * @return 结果集
     */
    @GetMapping("leftOn")
    @Operation(summary   = "左连接 on")
    public ResultVO<List<EmpVO>> leftOn(String id) {
        return new ResultVO<>(empMapper.leftOn(id));
    }

    /**
     * 左连接 where
     *
     * @param id 主键 1
     * @return 结果集
     */
    @GetMapping("leftWhere")
    @Operation(summary   = "左连接 where")
    public ResultVO<List<EmpVO>> leftWhere(String id) {
        return new ResultVO<>(empMapper.leftWhere(id));
    }

    /**
     * 自连接
     *
     * @return 结果集
     */
    @GetMapping("joinSelf")
    @Operation(summary   = "自连接")
    public ResultVO<List<EmpVO>> joinSelf() {
        return new ResultVO<>(empMapper.joinSelf());
    }

    /**
     * IFNULL 判断
     *
     * @return 结果集
     */
    @GetMapping("ifNull")
    @Operation(summary   = " IFNULL 判断")
    public ResultVO<String> ifNull(String id) {
        return new ResultVO<>(empMapper.ifNull(id));
    }

    /**
     * if 条件判断
     *
     * @return 结果集
     */
    @GetMapping("isNull")
    @Operation(summary   = "if 条件判断")
    public ResultVO<Boolean> isNull(String id) {
        return new ResultVO<>(empMapper.isNull(id));
    }
}
