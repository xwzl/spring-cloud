package com.java.prepare.controller;

import com.baidu.aip.ocr.AipOcr;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.spring.common.model.common.ApiResult;
import com.spring.common.model.model.vos.PictureResultVO;
import com.spring.common.model.utils.PictureUtils;
import com.spring.common.model.utils.QrCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * http://ai.baidu.com/docs#/OCR-Java-SDK/top
 *
 * @author xuweizhi
 * @since 2019/09/20 14:28
 */
@Api(tags = "图片文字转换")
@RestController
@RequestMapping("picture")
public class PictureController {

    @Resource
    private AipOcr aipOcr;

    /**
     * 简单通用图片文字提取
     *
     * @param file 文件
     * @return json 字符串
     * @throws IOException 异常
     */
    @PostMapping("basicGeneral")
    @ApiOperation("简单通用图片文字提取")
    public ApiResult<PictureResultVO> basicGeneral(@Param("file") MultipartFile file) throws IOException {
        PictureResultVO pictureResultVO = PictureUtils.basicGeneral(aipOcr, file.getBytes());
        return new ApiResult<>(pictureResultVO);
    }

    /**
     * 通用文字识别（含位置信息版）
     *
     * @param file 文件
     * @return json 字符串
     * @throws IOException 异常
     */
    @PostMapping("general")
    @ApiOperation("通用文字识别（含位置信息版）")
    public ApiResult<PictureResultVO> general(@NotNull @Param("file") MultipartFile file) throws IOException {
        PictureResultVO pictureResultVO = PictureUtils.general(aipOcr, file.getBytes());
        return new ApiResult<>(pictureResultVO);
    }

    /**
     * 精准文字识别
     *
     * @param file 文件
     * @return json 字符串
     * @throws IOException 异常
     */
    @PostMapping("basicAccurateGeneral")
    @ApiOperation("精准文字识别")
    public ApiResult<PictureResultVO> basicAccurateGeneral(@NotNull @Param("file") MultipartFile file) throws IOException {
        PictureResultVO pictureResultVO = PictureUtils.basicAccurateGeneral(aipOcr, file.getBytes());
        return new ApiResult<>(pictureResultVO);
    }

    /**
     * 通用文字识别（含位置高精度版）
     *
     * @param file 文件
     * @return json 字符串
     * @throws IOException 异常
     */
    @PostMapping("accurateGeneral")
    @ApiOperation("通用文字识别（含位置高精度版）")
    public ApiResult<PictureResultVO> accurateGeneral(@NotNull @Param("file") MultipartFile file) throws IOException {
        PictureResultVO pictureResultVO = PictureUtils.accurateGeneral(aipOcr, file.getBytes());
        return new ApiResult<>(pictureResultVO);
    }

    /**
     * 二维码不带图片
     */
    @GetMapping("/qrCodeNoPicture")
    @ApiOperation("二维码不带图片")
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
    @ApiOperation("二维码带图片")
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

    /**
     * 获取
     */
    @GetMapping
    public void printDate() {
        //AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        //Map<String, Date> map = applicationContext.getBeansOfType(Date.class);
        //System.out.println(map);
    }

}
