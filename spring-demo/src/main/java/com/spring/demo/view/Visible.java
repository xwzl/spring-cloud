package com.spring.demo.view;

import com.spring.demo.model.vos.TakeValidatedVO;

/**
 * 根据注入的接口判断 VO 对象返回值是否可见 {@link TakeValidatedVO}
 *
 * @author xuweizhi
 * @since 2019/09/14 16:00
 */
public class Visible {

    /**
     * JsonView 指定为此类返回值可见 {@link TakeValidatedVO#game}
     */
    public interface GameView {

    }

    /**
     * JsonView 指定为此类返回值可见 {@link TakeValidatedVO#basketBall}
     */
    public interface BasketBallView {

    }

}
