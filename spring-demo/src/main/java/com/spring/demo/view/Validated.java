package com.spring.demo.view;

import com.spring.demo.model.vos.TakeValidatedVO;

/**
 * 验证分组演示 {@link TakeValidatedVO}
 *
 * @author xuweizhi
 * @since 2019/09/14 15:55
 */
public class Validated {

    /**
     * Validated 注解制定此类值验证 {@link TakeValidatedVO#game}
     */
    public interface GameValidated {

    }

    /**
     * Validated 注解制定此类值验证 {@link TakeValidatedVO#basketBall}
     */
    public interface BasketBallValidated {

    }

}
