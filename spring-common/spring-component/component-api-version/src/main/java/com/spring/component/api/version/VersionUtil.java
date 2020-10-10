package com.spring.component.api.version;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author xuweizhi
 */
public class VersionUtil {

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    private VersionUtil() {

    }

    public static String getCurrentApiVersion() {
        String version = holder.get();
        if (StringUtils.isNotBlank(version)) {
            return version;
        }

        RequestAttributes attr = RequestContextHolder.getRequestAttributes();
        if (attr instanceof ServletRequestAttributes) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) attr;
            return attributes.getRequest().getHeader(VersionContent.VERSION_HEADER_KEY);
        }
        return null;
    }

    public static void remove() {
        holder.remove();
    }

    public static void setVersionContext(String version) {
        holder.set(version);
    }
}
