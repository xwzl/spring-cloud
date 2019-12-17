package com.spring.demo.model.vos;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试视图分组
 *
 * @author xuweizhi
 * @since 2019/12/17 22:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleVO {

    @JsonView(Simple.class)
    private String simple;

    @JsonView(Details.class)
    private String details;

    public interface Simple {

    }

    public interface Details extends Simple {

    }
}
