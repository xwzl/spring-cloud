package com.spring.demo.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.spring.demo.config.BeanConfig;
import com.spring.demo.untils.QrCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

/**
 * 前端控制器
 *
 * @author xuweizhi
 * @since 2019-06-28
 */
@RestController
@Slf4j
@RequestMapping("/qrCode")
public class QrCodeController {

    /**
     * 二维码带图片
     */
    @GetMapping("/qrCodeNoPicture")
    public void qrCodeNoPicture(HttpServletRequest request, HttpServletResponse response, String content) {


        StringBuffer url = request.getRequestURL();
        // 域名
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();

        // 再加上请求链接
        String requestUrl = tempContextUrl + "/index";
        try {
            OutputStream os = response.getOutputStream();
            QrCodeUtils.encode(StringUtils.isNotEmpty(content) ? content : "https://www.baidu.com", os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 二维码带图片
     */
    @GetMapping("/qrCode")
    public void qrCode(HttpServletRequest request, HttpServletResponse response, String content) {


        StringBuffer url = request.getRequestURL();
        // 域名
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();

        // 再加上请求链接
        String requestUrl = tempContextUrl + "/index";
        try {
            OutputStream os = response.getOutputStream();
            QrCodeUtils.encode(StringUtils.isNotEmpty(content) ? content : "https://www.baidu.com", "/static/images/logo.png", os, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public void printDate() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        Map<String, Date> map = applicationContext.getBeansOfType(Date.class);
        System.out.println(map);
    }

}
