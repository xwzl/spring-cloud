package com.spring.demo.untils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.charset.StandardCharsets;

/**
 * 响应对象工具类
 *
 * @author xuweizhi
 * @since 2019/09/10 14:07
 */
public class ResponseUtils {

    /**
     * excel 响应头设置
     *
     * @param response 响应对象
     * @param fileName 文件名称
     */
    public static void excelResponse(@NotNull HttpServletResponse response, String fileName) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        fileName = new String(fileName.getBytes(), StandardCharsets.ISO_8859_1);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    }

    /**
     * 获取 zuul 中的 request 信息
     */
    @Nullable
    public static Long getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String id = request.getHeader("userId");
        return StringUtils.isNumeric(id) ? Long.valueOf(id) : null;
    }

}
