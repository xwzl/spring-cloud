package com.spring.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.demo.model.dos.Emp;
import com.spring.demo.model.vos.EmpVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 三表查询 one
     *
     * @param company 公司名称
     * @param age     年轻
     * @return 返回数据
     */
    @Select("SELECT e.id AS id, e.emp_name, e.emp_age, c.id AS company_id, c.company_name  FROM " +
            "( emp AS e LEFT JOIN temp AS t ON e.id = t.emp_id ) LEFT JOIN company AS c ON c.id = t.company_id  " +
            "WHERE c.company_name = #{company}  AND e.emp_age < #{age};")
    List<EmpVO> listOne(@Param("company") String company, @Param("age") Integer age);

    /**
     * 三表查询 two
     *
     * @param company 公司名称
     * @param age     年轻
     * @return 返回数据
     */
    @Select("SELECT emp.id AS id, emp.emp_name, emp.emp_age, company.id AS company_id, company.company_name  FROM " +
            "emp INNER JOIN temp INNER JOIN company ON emp.id = temp.emp_id AND company.id = temp.company_id  " +
            "AND company.company_name = #{company} AND emp.emp_age < #{age};")
    List<EmpVO> listTwo(@Param("company") String company, @Param("age") Integer age);
}
