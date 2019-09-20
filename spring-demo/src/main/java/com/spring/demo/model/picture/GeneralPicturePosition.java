package com.spring.demo.model.picture;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 通用识别对象(包含位置)
 *
 * @author xuweizhi
 * @since 2019/09/20 17:16
 */
@Data
@ToString
public class GeneralPicturePosition {


    /**
     * 唯一的log id，用于问题定位
     */
    private String log_id;

    private String language;

    /**
     * 图像方向，当detect_direction=true时存在。
     * - -1:未定义，
     * - 0:正向，
     * - 1: 逆时针90度，
     * - 2:逆时针180度，
     * - 3:逆时针270度
     */
    private String direction;

    /**
     * 定位和识别结果数组
     */
    private List<WordsResult> words_result;
    /**
     * 识别结果数，表示words_result的元素个数
     */
    private String words_result_num;

}
