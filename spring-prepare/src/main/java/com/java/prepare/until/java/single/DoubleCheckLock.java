package com.java.prepare.until.java.single;

import lombok.Data;

/**
 * 双重锁优缺点
 *
 * @author xuweizhi
 * @since 2020/05/26 14:11
 */
@Data
public class DoubleCheckLock {

    private volatile static Instance instance;

    /**
     *
     *
     * http://note.youdao.com/noteshare?id=2322111334cfc7c0e7fc4eeea249a8f5&sub=DA140695DB19401F9BAF2B6A971FB702
     */
    public static Instance getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLock.class) {
                if (instance == null) {
                    instance = new Instance();
                    Instance s=instance;
                }
            }
        }
        return instance;
    }

}
