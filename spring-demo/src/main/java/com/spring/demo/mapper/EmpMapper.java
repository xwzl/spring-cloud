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

    /**
     * 内连接查询
     *
     * @return 结果集
     */
    @Select("SELECT e.id,e.emp_name,e.emp_age,t.emp_id,t.company_id FROM emp AS e,temp AS t WHERE e.id = t.emp_id")
    List<EmpVO> inner();

    /**
     * 内连接查询 on ，当在内连接查询中加入条件是，无论是将它加入到join子句，还是加入到where子句，其效果是完全一样的，但对于外连接情况就不同了。
     *
     * @return 结果集
     */
    @Select("SELECT e.id,e.emp_name,e.emp_age,t.emp_id,t.company_id FROM emp AS e INNER JOIN temp AS t ON e.id = t.emp_id AND t.emp_id = 1")
    List<EmpVO> innerOn();

    /**
     * 内连接查询 where
     *
     * @return 结果集
     */
    @Select("SELECT e.id,e.emp_name,e.emp_age,t.emp_id,t.company_id FROM emp AS e INNER JOIN temp AS t ON e.id = t.emp_id WHERE t.emp_id = 1")
    List<EmpVO> innerWhere();


    /**
     * 当把条件加入到 join子句时，SQL Server、Informix会返回外连接表的全部行，然后使用指定的条件返回第二个表的行。
     *
     * @param id 1
     * @return 结果集
     */
    @Select("SELECT e.id,e.emp_name,e.emp_age,t.emp_id,t.company_id FROM emp AS e LEFT JOIN temp AS t ON e.id = t.emp_id AND t.emp_id = #{id}")
    List<EmpVO> leftOn(String id);


    /**
     * 如果将条件放到where子句中，SQL Server将会首先进行连接操作，然后使用where子句对连接后的行进行筛选。
     *
     * @param id 主键 1
     * @return 结果集
     */
    @Select("SELECT e.id,e.emp_name,e.emp_age,t.emp_id,t.company_id FROM emp AS e LEFT OUTER JOIN temp AS t ON e.id = t.emp_id where t.emp_id = #{id}")
    List<EmpVO> leftWhere(String id);

    /**
     * 自身连接
     *
     * @return 结果集
     */
    @Select("SELECT e.id,e.emp_name,e.emp_age as age FROM emp AS e JOIN emp AS m ON e.id = m.emp_age")
    List<EmpVO> joinSelf();

    /**
     * 结果集 IFNULL 判断，若为 NULL 则判断为默认字段，不为 NULL 返回数据
     *
     * @param id 主键
     * @return 结果集
     */
    @Select("SELECT IFNULL(use_department,\"hello\") as hello FROM computer where computer.office_id = #{id}")
    String ifNull(String id);

    /**
     * 结果集 IF 判断
     *
     * @param id 主键
     * @return 结果集
     */
    @Select("SELECT IF(use_department IS NULL,TRUE,FALSE) as hello FROM computer where computer.office_id = #{id}")
    Boolean isNull(String id);
}
