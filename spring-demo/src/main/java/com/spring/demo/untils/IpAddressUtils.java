package com.spring.demo.untils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * ip 地址工具类
 *
 * @author xuweizhi
 * @date 2019/09/25 22:53
 */
public class IpAddressUtils {

    /**
     * 获取完整的 ip 地址
     *
     * @return 返回值
     * @throws UnknownHostException 异常
     */
    public static InetAddress getIpAddress() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }

    /**
     * 获取 ip 地址
     *
     * @return 返回值
     * @throws UnknownHostException 异常
     */
    public static String getHostAddress() throws UnknownHostException {
        return getIpAddress().getHostAddress();
    }

    /**
     * 获取.....
     *
     * @return 返回值
     * @throws UnknownHostException 异常
     */
    public static String getHostName() throws UnknownHostException {
        return getIpAddress().getHostName();
    }

    /**
     * 获取 ip 地址
     *
     * @return 返回值
     * @throws UnknownHostException 异常
     */
    public static String getCanonicalHostName() throws UnknownHostException {
        return getIpAddress().getCanonicalHostName();
    }

    /**
     * 获取ip 地址
     *
     * @return 返回值
     * @throws UnknownHostException 异常
     */
    public static String getAddress() throws UnknownHostException {
        return Arrays.toString(getIpAddress().getAddress());
    }
}
