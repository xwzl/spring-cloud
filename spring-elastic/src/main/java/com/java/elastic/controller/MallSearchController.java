package com.java.elastic.controller;

/*
@author 图灵学院-白起老师
*/

import com.java.elastic.service.TulingMallSearchService;
import com.java.elastic.vo.RequestParam;
import com.java.elastic.vo.ResponseResult;
import com.spring.common.model.common.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class MallSearchController {


    @Resource
    private TulingMallSearchService tulingMallSearchService;



    /**
     * 自动将页面提交过来的所有请求参数封装成我们指定的对象
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchList")
    public ResultVO<ResponseResult> listPage(RequestParam param, HttpServletRequest request) {

        //1、根据传递来的页面的查询参数，去es中检索商品
        ResponseResult searchResult = tulingMallSearchService.search(param);

        return ResultVO.success(searchResult);
    }

}
