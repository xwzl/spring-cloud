package com.spring.starter.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * 获取当前项目的端口号
 *
 * @author xuweizhi
 * @since 2019-09-25
 */
@Configuration
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

    public int getPort() {
        return this.serverPort;
    }

    /**
     * 获取完整的 ip 地址
     *
     * @return 返回值
     * @throws UnknownHostException
     *             异常
     */
    public  InetAddress getIpAddress() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }

    /**
     * 获取 ip 地址
     *
     * @return 返回值
     * @throws UnknownHostException
     *             异常
     */
    public String getHostAddress() throws UnknownHostException {
        return getIpAddress().getHostAddress();
    }

    /**
     * 获取.....
     *
     * @return 返回值
     * @throws UnknownHostException
     *             异常
     */
    public String getHostName() throws UnknownHostException {
        return getIpAddress().getHostName();
    }

    /**
     * 获取 ip 地址
     *
     * @return 返回值
     * @throws UnknownHostException
     *             异常
     */
    public String getCanonicalHostName() throws UnknownHostException {
        return getIpAddress().getCanonicalHostName();
    }

    /**
     * 获取ip 地址
     *
     * @return 返回值
     * @throws UnknownHostException
     *             异常
     */
    public String getAddress() throws UnknownHostException {
        return Arrays.toString(getIpAddress().getAddress());
    }
}
