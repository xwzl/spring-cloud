package com.spring.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.demo.model.dos.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author xuweizhi
 * @since 2019-08-01
 */
public interface EmpMapper extends BaseMapper<Emp> {

    /**
     * 查询员工信息，注意事项
     * xml 中入参类型为 List , 返回参数类型为映射对象
     *
     * @param ids 员工
     * @return 员工集合
     */
    List<Emp> listByEmp(@Param("ids") List<Integer> ids);

}
