package com.java.elastic.vo;


import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * @author xuweizhi
 */
@Data
public class RequestParam {

    /**
     * 页面传递过来的全文匹配关键字
     */
    private String keyword;

    /**
     * 品牌id,可以多选
     */
    private List<Long> brandId;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 排序条件：sort=price/salecount/hotscore_desc/asc
     */
    private String sort;

    /**
     * 销量
     */
    private Long salecount;

    /**
     * 上架时间
     */
    private Date putawayDate;

    /**
     * 是否显示有货  1代表有货
     */
    private Integer hasStock;

    /**
     * 价格区间查询
     */
    private String price;

    /**
     * 按照属性进行筛选
     */
    private List<String> attrs;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 原生的所有查询条件
     */
    private String queryString;

}
