package com.java.elastic.service;


import com.java.elastic.vo.RequestParam;
import com.java.elastic.vo.ResponseResult;

/**
 * @author xuweizhi
 */
public interface TulingMallSearchService {


    /**
     * @param param 检索的所有参数
     * @return 返回检索的结果，里面包含页面需要的所有信息
     */
    ResponseResult search(RequestParam param);


}


