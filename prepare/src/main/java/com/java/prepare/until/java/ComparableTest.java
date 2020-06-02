package com.java.prepare.until.java;

import com.spring.common.model.model.vos.ScoreVO;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 比较接口测试
 *
 * @author xuweizhi
 * @since 2020/06/02 10:22
 */
public class ComparableTest {

    @Test
    public void orderTest() {
        List<ScoreVO> scoreVOS = Arrays.asList(
                new ScoreVO(11, "A"), new ScoreVO(1, "B"),
                new ScoreVO(12, "C"), new ScoreVO(15, "D"),
                new ScoreVO(13, "E"), new ScoreVO(1, "F"),
                new ScoreVO(1, "G"), new ScoreVO(1, "H"),
                new ScoreVO(8, "I"), new ScoreVO(1, "J")
        );
        Collections.sort(scoreVOS);
        scoreVOS.forEach(System.out::println);
    }
}
