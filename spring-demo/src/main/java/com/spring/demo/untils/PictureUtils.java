package com.spring.demo.untils;

import com.baidu.aip.ocr.AipOcr;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.spring.demo.model.picture.GeneralPicture;
import com.spring.demo.model.picture.GeneralPicturePosition;
import com.spring.demo.model.picture.WordsResult;
import com.spring.demo.model.vos.PictureResultVO;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 图片文字提取工具类(http://ai.baidu.com/docs#/OCR-Java-SDK/08232781)
 *
 * @author xuweizhi
 * @since 2019/09/20 14:47
 */
public class PictureUtils {

    /**
     * 字符串截取位置
     */
    private final static int MODE = 2;

    /**
     * 连接超时时间
     */
    private final static int CONNECTION_TIMEOUT_IN_MILLIS = 2000;

    /**
     * socket 超时时间
     */
    private final static int SOCKET_TIMEOUT_IN_MILLIS = 6000;

    /**
     * 通用文字识别(试用版本)，每次免费调用 50000 次
     *
     * @param aipDcr aipDcr
     * @param bytes  字节数组
     */
    public static void sample(@NotNull AipOcr aipDcr, byte[] bytes) throws IOException {// 可选：设置网络连接参数

        aipDcr.setConnectionTimeoutInMillis(CONNECTION_TIMEOUT_IN_MILLIS);
        aipDcr.setSocketTimeoutInMillis(SOCKET_TIMEOUT_IN_MILLIS);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //aipDcr.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //aipDcr.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        //
        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        //
        // 调用接口
        //String path = "spring-demo/test.png";
        //JSONObject res = aipDcr.basicGeneral(path, new HashMap<String, String>());
        JSONObject res = aipDcr.basicGeneral(bytes, new HashMap<String, String>());
        System.out.println(res.toString(MODE));
    }

    /**
     * 通用文字识别，每次免费调用 50000 次
     *
     * @param aipDcr aipDcr
     * @param bytes  字节数组
     */
    public static PictureResultVO basicGeneral(@NotNull AipOcr aipDcr, byte[] bytes) {

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        // 参数为本地路径
        //String image = "test.jpg";
        //JSONObject res = aipDcr.basicGeneral(image, options);
        //System.out.println(res.toString(MODE));

        // 参数为二进制数组
        //byte[] file = readFile("test.jpg");
        JSONObject res = aipDcr.basicGeneral(bytes, options);
        //System.out.println(res.toString(MODE));
        // 通用文字识别, 图片参数为远程url图片
        //JSONObject res = client.basicGeneralUrl(url, options);
        //System.out.println(res.toString(MODE));
        if (StringUtils.isEmpty(res.toString(MODE))) {
            return new PictureResultVO();
        }
        return convertResult(res.toString(MODE));
    }

    /**
     * 简单对象图片识别转换
     *
     * @param json Json 字符串
     */
    @NotNull
    @Contract("_ -> new")
    private static PictureResultVO convertResult(String json) {
        GeneralPicture generalPicture = JsonUtils.parseJsonObject(json, GeneralPicture.class);

        StringBuilder wrap = new StringBuilder();
        StringBuilder noWrap = new StringBuilder();
        List<WordsResult> results = generalPicture.getWords_result();

        if (CollectionUtils.isEmpty(results)) {
            return new PictureResultVO();
        }
        for (WordsResult wordsResult : results) {
            wrap.append(wordsResult.getWords()).append("\n");
            noWrap.append(wordsResult.getWords());
        }
        System.out.println(wrap.toString());
        return new PictureResultVO(wrap.toString(), noWrap.toString());
    }

    /**
     * 简单对象图片(包含位置信息)识别转换
     *
     * @param json Json 字符串
     */
    @NotNull
    @Contract("_ -> new")
    private static PictureResultVO convertResultPosition(String json) {

        GeneralPicturePosition generalPicturePosition = JsonUtils.parseJsonObject(json, GeneralPicturePosition.class);

        List<WordsResult> results = generalPicturePosition.getWords_result();
        StringBuilder wrap = new StringBuilder();

        if (CollectionUtils.isEmpty(results)) {
            return new PictureResultVO();
        }

        StringBuilder noWrap = new StringBuilder();

        for (WordsResult wordsResult : results) {
            wrap.append(wordsResult.getWords()).append("\n");
            noWrap.append(wordsResult.getWords());
        }
        System.out.println(wrap.toString());
        return new PictureResultVO(wrap.toString(), noWrap.toString());
    }

    /**
     * 通用文字识别（含位置信息版）
     * <p>
     * 用户向服务请求识别某张图中的所有文字，并返回文字在图中的位置信息。每次免费调用 500 次
     *
     * @param aipDcr aipDcr
     * @param bytes  字节数组
     */
    public static PictureResultVO general(@NotNull AipOcr aipDcr, byte[] bytes) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        // 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
        options.put("recognize_granularity", "big");
        // CHN_ENG：中英文混合；
        options.put("language_type", "CHN_ENG");
        // 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/MODE70度。可选值包括:
        options.put("detect_direction", "true");
        // 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
        options.put("detect_language", "true");
        // 	是否返回文字外接多边形顶点位置，不支持单字位置。默认为false
        options.put("vertexes_location", "true");
        // 是否返回识别结果中每一行的置信度
        options.put("probability", "true");


        // 参数为本地路径
        //String image = "test.jpg";
        //JSONObject res = aipDcr.general(image, options);
        //System.out.println(res.toString(MODE));

        // 参数为二进制数组
        //byte[] file = readFile("test.jpg");
        JSONObject res = aipDcr.general(bytes, options);
        //System.out.println(res.toString(MODE));

        // 通用文字识别（含位置信息版）, 图片参数为远程url图片
        //JSONObject res = aipDcr.generalUrl(url, options);
        //System.out.println(res.toString(MODE));

        if (StringUtils.isEmpty(res.toString(MODE))) {
            return new PictureResultVO();
        }
        return convertResultPosition(res.toString(MODE));
    }

    /**
     * 精准文字识别
     * 用户向服务请求识别某张图中的所有文字，相对于通用文字识别该产品精度更高，但是识别耗时会稍长。每日500次免费调用量，免费额度用尽后开始计费。
     *
     * @param aipDcr aipDcr
     * @param bytes  字节数组
     */
    public static PictureResultVO basicAccurateGeneral(@NotNull AipOcr aipDcr, byte[] bytes) throws IOException {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("probability", "true");

        // 参数为本地路径
        //String image = "spring-demo/test.png";
        //JSONObject res = aipDcr.basicAccurateGeneral(image, options);
        //System.out.println(res.toString(MODE));

        //// 参数为二进制数组
        //byte[] file = readFile("test.jpg");
        //byte[] files = file.getBytes();
        JSONObject res = aipDcr.basicAccurateGeneral(bytes, options);
        if (StringUtils.isEmpty(res.toString(MODE))) {
            return new PictureResultVO();
        }
        return convertResult(res.toString(MODE));
    }

    /**
     * 通用文字识别（含位置高精度版）
     * <p>
     * 用户向服务请求识别某张图中的所有文字，并返回文字在图片中的坐标信息，相对于通用文字识别（含位置信息版）该产品精度更高，但是识别耗时会稍长。
     *
     * @param aipDcr aipDcr
     * @param bytes  字节数组
     */
    public static PictureResultVO accurateGeneral(@NotNull AipOcr aipDcr, byte[] bytes) {

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("recognize_granularity", "big");
        options.put("detect_direction", "true");
        options.put("vertexes_location", "true");
        options.put("probability", "true");

        // 参数为本地路径
        //String image = "test.jpg";
        //JSONObject res = aipDcr.accurateGeneral(image, options);
        //System.out.println(res.toString(MODE));

        // 参数为二进制数组
        //byte[] file = readFile("test.jpg");
        JSONObject res = aipDcr.accurateGeneral(bytes, options);
        //System.out.println(res.toString(MODE));

        if (StringUtils.isEmpty(res.toString(MODE))) {
            return new PictureResultVO();
        }
        return convertResultPosition(res.toString(MODE));
    }

    /**
     * 通用文字识别（含位置高精度版）
     * <p>
     * 用户向服务请求识别某张图中的所有文字，并返回文字在图片中的坐标信息，相对于通用文字识别（含位置信息版）该产品精度更高，但是识别耗时会稍长。
     *
     * @param aipDcr aipDcr
     * @param bytes  字节数组
     */
    public static String webImage(@NotNull AipOcr aipDcr, byte[] bytes) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_language", "true");

        // 参数为本地路径
        //String image = "test.jpg";
        //JSONObject res = aipDcr.webImage(image, options);
        //System.out.println(res.toString(MODE));

        // 参数为二进制数组
        //byte[] file = readFile("test.jpg");
        JSONObject res = aipDcr.webImage(bytes, options);
        //System.out.println(res.toString(MODE));
        return res.toString(MODE);

        // 网络图片文字识别, 图片参数为远程url图片
        //JSONObject res = aipDcr.webImageUrl(url, options);
        //System.out.println(res.toString(MODE));

    }
}
