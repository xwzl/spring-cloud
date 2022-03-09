package com.java.elastic.controller;


import com.java.elastic.entity.EsProduct;
import com.java.elastic.entity.EsProductRelatedInfo;
import com.java.elastic.service.EsProductService;
import com.spring.common.model.common.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索商品管理
 */
@RestController
@RequestMapping("/esProduct")
@Api(tags = "EsProductController", description = "搜索商品管理")
public class EsProductController {

    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    public ResultVO<Integer> importAllList() {
        int count = esProductService.importAll();
        return ResultVO.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResultVO<Object> delete(@PathVariable Long id) {
        esProductService.delete(id);
        return ResultVO.success(null);
    }

    @ApiOperation(value = "根据id批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    public ResultVO<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return ResultVO.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public ResultVO<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct != null) {
            return ResultVO.success(esProduct);
        } else {
            return ResultVO.failed();
        }
    }

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.POST)
    public ResultVO<Page<EsProduct>> search(@RequestParam(required = false) String keyword,
                                            @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return ResultVO.success(esProductPage);
    }

    @ApiOperation(value = "综合搜索、筛选、排序")
    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低",
            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResultVO<Page<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false) Long brandId,
                                                      @RequestParam(required = false) Long productCategoryId,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                      @RequestParam(required = false, defaultValue = "0") Integer sort) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return ResultVO.success(esProductPage);
    }

    @ApiOperation(value = "根据商品id推荐商品")
    @RequestMapping(value = "/recommend/{id}", method = RequestMethod.GET)
    public ResultVO<Page<EsProduct>> recommend(@PathVariable Long id,
                                                         @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.recommend(id, pageNum, pageSize);
        return ResultVO.success(esProductPage);
    }

    @ApiOperation(value = "获取搜索的相关品牌、分类及筛选属性")
    @RequestMapping(value = "/search/relate", method = RequestMethod.GET)
    public ResultVO<EsProductRelatedInfo> searchRelatedInfo(@RequestParam(required = false) String keyword) {
        EsProductRelatedInfo productRelatedInfo = esProductService.searchRelatedInfo(keyword);
        return ResultVO.success(productRelatedInfo);
    }
}
