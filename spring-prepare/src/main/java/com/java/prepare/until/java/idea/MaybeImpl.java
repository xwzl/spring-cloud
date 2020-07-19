package com.java.prepare.until.java.idea;

/**
 * 实现类过多，提取代理层
 * <p>
 * Refactor Extract Delegate 提取代理代码
 *
 * @author xuweizhi
 * @since 2020/07/19 17:38
 */
public class MaybeImpl implements Maybe {

    private final MaybeDelegate maybeDelegate = new MaybeDelegate();

    @Override
    public void a() {
        maybeDelegate.a();
    }

    @Override
    public void b() {
        maybeDelegate.b();
    }

    @Override
    public void c() {
        maybeDelegate.c();
    }
}
