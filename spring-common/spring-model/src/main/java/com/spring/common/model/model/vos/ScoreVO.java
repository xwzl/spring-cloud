package com.spring.common.model.model.vos;

import lombok.Data;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

/**
 * compare 测试
 *
 * @author xuweizhi
 * @since 2020/06/02 10:23
 */
@Data
@ToString
public class ScoreVO implements Comparable<ScoreVO> {

    private int score;

    private String name;

    private int order;

    public ScoreVO(int score, String name) {
        this.score = score;
        this.name = name;
    }

    @Override
    public int compareTo(@NotNull ScoreVO that) {
        String str = this.score + "\t" + that.score + "\t" + (this.score - that.score);
        System.out.println(str);
        return this.score - that.score;
    }
}
