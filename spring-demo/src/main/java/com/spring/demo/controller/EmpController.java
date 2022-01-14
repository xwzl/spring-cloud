package com.spring.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.spring.demo.annotation.AopSample;
import com.spring.demo.mapper.ComputerMapper;
import com.spring.demo.mapper.EmpMapper;
import com.spring.demo.model.dos.Computer;
import com.spring.demo.model.dos.Emp;
import com.spring.common.model.common.ApiResult;
import com.spring.demo.model.vos.DateVO;
import com.spring.demo.model.vos.EmpVO;
import com.spring.demo.service.ComputerService;
import com.spring.demo.service.EmpService;
import com.spring.demo.untils.pool.ThreadPoolUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 前端控制器
 *
 * @author xuweizhi
 * @since 2019-08-01
 */
@Slf4j
@AopSample
@RestController
@RequestMapping("/emp")
@Api("aop 注解测试，代理整个类或者单个方法")
public class EmpController {

    private final EmpService empService;

    private final ComputerMapper computerMapper;

    private final ComputerService computerService;

    private final RedisTemplate<String, Object> redisTemplate;

    private final ValueOperations<String, Object> opsForValue;

    @Resource
    private EmpMapper empMapper;

    @Contract(pure = true)
    public EmpController(EmpService empService, ComputerMapper computerMapper, ComputerService computerService, RedisTemplate<String, Object> redisTemplate, ValueOperations<String, Object> opsForValue) {
        this.empService = empService;
        this.computerMapper = computerMapper;
        this.computerService = computerService;
        this.redisTemplate = redisTemplate;
        this.opsForValue = opsForValue;
    }

    @PostMapping("/mvc")
    @ApiOperation("时间类格式转换测试")
    public void mvc(@Valid DateVO dateVO) {
        dateVO.setValue("我们是一个好孩子!");
        redisTemplate.opsForValue().set("haha11", dateVO);
        log.info(dateVO.toString());
        DateVO data = (DateVO) redisTemplate.opsForValue().get("haha11");
        log.info(Objects.requireNonNull(data).toString());
    }

    /**
     * redis 中 key 加 ： 相当于创建一层目录
     */
    @GetMapping("/list")
    @ApiOperation("redis key 中的 ： 相当于一层文件")
    public ApiResult<List<Computer>> list() {
        List<Computer> list = (List<Computer>) opsForValue.get("fuck_you");
        if (list == null) {
            list = computerService.list();
            //opsForValue.set("fuck_you", list, 100, TimeUnit.SECONDS);
            opsForValue.set("fuck:you:god", list);
        }
        return new ApiResult<>(list);
    }

    /**
     * 巨坑，这个转换器要哪个啥？如果不传参数，必须保证 required 为 false
     */
    @GetMapping("/check")
    @ApiOperation("巨坑，这个转换器要哪个啥？如果不传参数，必须保证 required 为 false")
    public LocalDateTime check(@RequestParam(required = false) LocalDateTime localDateTime) {
        return localDateTime;
    }

    @GetMapping("getList")
    @ApiOperation("那个啥呢？")
    public List<Computer> getList(String empName, String empLevel) {
        return computerMapper.getList(empLevel, empName);
    }

    @DeleteMapping
    @ApiOperation("全局异常处理拦截")
    public void delete() {
        // empService.delete(new Emp());
    }

    @GetMapping("/conditionalList")
    @ApiOperation("mybatis plus 构造器之条件判断")
    public List<Computer> conditionalList(Computer computer) {
        QueryWrapper<Computer> query = new QueryWrapper<>();
        query.eq(StringUtils.isNotEmpty(computer.getAssetType()), "asset_type", computer.getAssetType())
                .eq(StringUtils.isNotEmpty(computer.getBrand()), "brand", computer.getBrand())
                .like(StringUtils.isNotEmpty(computer.getOwner()), "owner", computer.getOwner())
                .like(StringUtils.isNotEmpty(computer.getContact()), "contact", computer.getContact());
        return computerService.list(query);
    }

    /**
     * 史上大 bug 集合
     * <p>
     * 期望 sql 结果： brand = ? and ( assert_number = ? or computer_source = ?)
     * <p>
     * sql 结果： brand = ? and assert_number = ? or computer_source = ?
     *
     * @param computer 最坑的一次 bug
     */
    @GetMapping("/list2")
    @ApiOperation("mybatis plus 条件构成器器值 or SQL 拼接问题")
    public List<Computer> orSelectBug(Computer computer, String keyWord) {
        QueryWrapper<Computer> query = new QueryWrapper<>();
        query.eq(StringUtils.isNotEmpty(computer.getBrand()), "brand", computer.getBrand());
        query.and(StringUtils.isNotEmpty(keyWord), i -> i.like("asset_number", keyWord).or().like("computer_source", keyWord));
        return computerService.list(query);
    }

    @GetMapping("/paramProviderReference")
    @ApiOperation("provider 对象参数在注入")
    public List<Computer> paramProviderReference(Computer computer) {
        return computerMapper.paramProviderReference(computer);
    }

    /**
     * mysql 查询参数字符串拼接
     */
    @GetMapping("sequenceAppend")
    @ApiOperation("注解 sql 条件参数拼接")
    public List<Computer> sequenceAppend(String keyWord) {
        return computerMapper.sequenceAppend(keyWord);
    }

    /**
     * 实际开发中线程池不用关闭
     */
    @GetMapping("/thread")
    public String pool() {
        ThreadPoolExecutor executor = ThreadPoolUtils.extensionThreadPool();
        executor.execute(() -> {
            log.info("如果不中断线程池，会有返回信息吗？");
        });
        return "如果不中断线程池，会有返回信息吗?";
    }

    /**
     * 集合校验
     *
     * @param list 参数集合加注解
     * @return 返回值
     */
    @GetMapping("/empList")
    public List<Emp> empList(@RequestParam(value = "list", required = false) List<Integer> list) {
        return empMapper.listByEmp(list);
    }

    /**
     * 三表查询 一
     */
    @ApiOperation("三表查询一")
    @GetMapping("/threeTableSelect")
    public List<EmpVO> listOne(String company, Integer age) {
        return empMapper.listOne(company, age);
    }

    /**
     * 三表查询二
     */
    @ApiOperation("三表查询一")
    @GetMapping("/threeTableSelectTwo")
    public List<EmpVO> listTwo(String company, Integer age) {
        return empMapper.listTwo(company, age);
    }
}
