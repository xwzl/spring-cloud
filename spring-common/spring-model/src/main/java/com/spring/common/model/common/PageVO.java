package com.spring.common.model.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页")
public class PageVO<T> implements Serializable {
    private static final long serialVersionUID = 913178003194448540L;
    @Schema(description = "当前页数")
    private Integer pageNum;
    @Schema(description = "总页数")
    private Integer pageSize;
    @Schema(description = "总记录数")
    private Long totalNum;
    @Schema(description = "集合")
    private List<T> list;
}
