package com.spring.demo.model.vos;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 返回值可见分析
 *
 * @author xuweizhi
 * @since 2019/10/10 15:55
 */
@Data
@AllArgsConstructor
public class ReturnViewVO {

    @JsonView(Base.class)
    private String name;

    @JsonView(BaseDetails.class)
    private String password;


    public static interface Base {
    }

    public static interface BaseDetails extends Base {
    }
}
