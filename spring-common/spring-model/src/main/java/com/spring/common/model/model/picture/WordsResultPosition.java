package com.spring.common.model.model.picture;

import lombok.Data;
import lombok.ToString;

/**
 * 字符集(包含位置信息)
 *
 * @author xuweizhi
 * @since 2019/09/20 17:16
 */
@Data
@ToString
public class WordsResultPosition {


    /**
     * 识别结果字符串
     */
    private String words;

    /**
     * 位置数组（坐标0点为左上角）
     */
    private String location;

    /**
     * ......
     */
    private String finegrained_vertexes_location;

    /**
     * 当前为四个顶点: 左上，右上，右下，左下。当vertexes_location=true时存在
     */
    private String vertexes_location;

    /**
     * 最小什么 ......
     */
    private String min_finegrained_vertexes_location;

    /**
     * 行置信度信息；如果输入参数 probability = true 则输出
     */
    private Probability probability;


}
