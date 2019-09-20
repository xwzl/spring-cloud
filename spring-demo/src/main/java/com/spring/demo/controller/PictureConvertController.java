package com.spring.demo.controller;

import com.baidu.aip.ocr.AipOcr;
import com.spring.demo.model.vos.ApiResult;
import com.spring.demo.model.vos.PictureResultVO;
import com.spring.demo.untils.PictureUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * http://ai.baidu.com/docs#/OCR-Java-SDK/top
 *
 * @author xuweizhi
 * @since 2019/09/20 14:28
 */
@Api(tags = "图片文字转换")
@RestController
@RequestMapping("picture")
public class PictureConvertController {

    @Autowired
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

}
