package com.spring.common.model.model.picture;

import lombok.Data;
import lombok.ToString;

/**
 * 字符集
 *
 * @author xuweizhi
 * @since 2019/09/20 17:16
 */
@Data
@ToString
public class WordsResult {

    /**
     * 识别结果字符串
     */
    private String words;

    /**
     * 行置信度信息；如果输入参数 probability = true 则输出
     */
    private Probability probability;
}
