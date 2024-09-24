package com.spring.demo.config;

import com.spring.starter.config.IpConfiguration;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * SpringBoot 完成后打印 swagger 文档地址
 *
 * @author xuweizhi
 * @date 2019/09/25 22:44
 */
@Slf4j
@Order(100)
@Component
public class ApplicationRunnerAfter implements ApplicationRunner {

    @Resource
    private IpConfiguration ipConfiguration;

    public static String ip = "127.0.0.1";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ip = getLocalIPv4();
        // log.info("Swagger github : http://" + ipConfiguration.getHostAddress() + ":" + ipConfiguration.getPort() + "/swagger-ui.html");
        log.info("Swagger github : http://" + ip + ":" + ipConfiguration.getPort() + "/swagger-ui.html");
        log.info("Swagger github boot-strap: http://" + ip + ":" + ipConfiguration.getPort() + "/doc.html");
    }

    /**
     * 获取本机IPv4地址
     * @return 本机IPv4地址字符串
     */
    public static String getLocalIPv4() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (!address.isLoopbackAddress() && address instanceof java.net.Inet4Address) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

}
