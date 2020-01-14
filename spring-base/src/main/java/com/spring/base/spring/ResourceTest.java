package com.spring.base.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 资源路径的获取
 *
 * @author xuweizhi
 * @since 2020/01/14 9:48
 */
@Slf4j
public class ResourceTest {

    private static final String XML_FILE_EXTENSION = ".xml";

    Class<?> clazz = this.getClass();

    String springPath = "META-INF/spring.factories";

    String path = "";

    ClassLoader classLoader = clazz.getClassLoader();

    /**
     * 1. class 的 getResource 不加“/”，会从 class 这个类所在的路径开始往下搜索资源，如果加上 “/” ,会从工程的根目录，
     * 也就是截图里的 target/classes 这个路径下开始搜索资源
     * 2. classloader 的 getResource 不加“/”，会从工程的根目录，也就是截图里的 target/classes 这个路径下开始搜索资
     * 源，如果加上“/”，就会返回null。
     * 3. classLoader 的 getSystemResource 方法类似于 classLoader 的方法，相当于使用系统的 classLoader 然后调用 getResource.
     */
    @Test
    public void resourceTest() {
        //if (this.clazz != null) {
        //    log.info(this.clazz.getResource(this.path).toString());
        //} else if (this.classLoader != null) {
        //    log.info(this.classLoader.getResource(this.path).toString());
        //} else {
        //    log.info(ClassLoader.getSystemResource(this.path).toString());
        //}
        //log.info(this.clazz.getResource(this.path).toString());
        log.info(this.classLoader.getResource(this.path).toString());
        //log.info(ClassLoader.getSystemResource(this.path).toString());

    }

    /**
     * ClassLoader.getResources("customize.properties") 获取 class 路径下的资源文件,这个 class 路径包含所有引入的 jar 包
     */
    @Test
    public void loadSelfProperties() throws IOException {
        Enumeration<URL> resources = this.getClass().getClassLoader().getResources("customize.properties");
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            UrlResource resource = new UrlResource(url);
            Properties props = fillProperties(new Properties(), resource);
            for (Map.Entry<?, ?> entry : props.entrySet()) {
                log.info(entry.getValue().toString());
            }
        }
    }

    private Properties fillProperties(Properties props, UrlResource resource) throws IOException {
        try (InputStream is = resource.getInputStream()) {
            String filename = resource.getFilename();
            if (filename != null && filename.endsWith(XML_FILE_EXTENSION)) {
                props.loadFromXML(is);
            } else {
                props.load(is);
            }
        }
        return props;
    }

    /**
     * 加载 Spring Jars
     *
     * @throws IOException e
     */
    @Test
    public void loadSpringJars() throws IOException {
        ResourceTest resourceTest = new ResourceTest();
        Enumeration<URL> urls = ClassLoader.getSystemResources(resourceTest.springPath);
        LinkedMultiValueMap<Object, Object> result = new LinkedMultiValueMap<>();
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            UrlResource resource = new UrlResource(url);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            for (Map.Entry<?, ?> entry : properties.entrySet()) {
                String factoryTypeName = ((String) entry.getKey()).trim();
                for (String factoryImplementationName : StringUtils.commaDelimitedListToStringArray((String) entry.getValue())) {
                    result.add(factoryTypeName, factoryImplementationName.trim());
                }
            }
        }
        for (Map.Entry<Object, List<Object>> objectListEntry : result.entrySet()) {
            log.info(objectListEntry.getValue().toString());
        }
    }

}
