package com.spring.demo.model.vos;

import lombok.Data;
import lombok.ToString;

/**
 * 三表查询对象
 *
 * @author xuweizhi
 * @since 2019/12/03 15:46
 */
@Data
@ToString
public class EmpVO {

    private Integer id;

    private String empName;

    private String empAge;

    private String companyId;

    private String companyName;

}
