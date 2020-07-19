package com.java.prepare.until.java;

import org.junit.Test;

/**
 * 摘要加密: md5 sha1 sha2 sha1 256
 *
 * @author xuweizhi
 * @since 2020/07/19 14:42
 */
public class SecretCode {

    public void hello() {
        ideaShortCut("xx");
    }

    private String ideaShortCut(String txt) {
        System.out.println("xxx");
        System.out.println("xxx");
        System.out.println("xxx");
        System.out.println("xxx");
        return "";
    }

    /**
     * 对称加密，秘钥两次异或相同密钥转换为原值
     */
    @Test
    public void test1() {
        int x1 = 255;
        x1 = 255 ^ 1000;
        System.out.println(x1);
        System.out.println(x1 ^ 1000);
    }

    /**
     * 非对称加密：
     * <p>
     * 使用公钥加密，则私钥解密;这种方式安全
     * 使用私钥加密，则公钥解密;这种方式不安全
     * <p>
     * Tips:
     * <p>
     * 它有两个秘钥，一个叫公钥，一个叫私钥。两个秘钥是不同的，公钥可以公开个任何人使用，而私钥必须严格保密。
     * 非对称加密可以解决秘钥交换的问题。
     * <p>
     * 网站秘密保管私钥，在网上任意分发公钥，你想要登录网站只要用公钥加密进行了，密文只能由私钥持有者才能解密。
     * 而黑客没有私钥，所以就无法破解密文。
     * <p>
     * 非对称秘钥加密系统通常需要大量的数学运算，比较慢。
     */
    @Test
    public void test2() {

    }
}
