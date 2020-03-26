package com.spring.base.spring;

import com.spring.base.spring.model.Book;
import com.spring.base.spring.model.ModifyClass;
import com.spring.base.spring.model.NaturalBooks;
import com.spring.base.spring.model.TreeBookNaturalBook;
import com.spring.common.model.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Enumeration;

/**
 * Java 基本类型字节码文件测试
 *
 * @author xuweizhi
 * @since 2020/01/10 10:40
 */
@Slf4j
public class ReflectTest {

    public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";

    @Test
    public void classType() {
        log.info(String.class.toString());
        log.info(String[].class.toString());
        log.info(int.class.toString());
        log.info(Integer.class.toString());
    }

    @Test
    public void classLoader() {
        Class<ReflectTest> clazz = ReflectTest.class;
        ClassLoader classLoader = clazz.getClassLoader();
        for (; ; ) {
            log.info(classLoader.toString());
            if (classLoader.getParent() == null) {
                break;
            }
            classLoader = classLoader.getParent();
        }
    }

    @Test
    public void getResources() throws IOException {
        ClassLoader classLoader = null;
        Enumeration<URL> urls = (classLoader != null ?
                classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
                ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
    }

    /**
     * {@link org.springframework.util.Assert#isAssignable(Class, Class)} 判断类是否可转换
     */
    @Test
    public void assignType() {
        Class<Book> superClazz = Book.class;
        Class<NaturalBooks> subClazz = NaturalBooks.class;
        Class<TreeBookNaturalBook> subSubClazz = TreeBookNaturalBook.class;
        // 判断是否为超类超类？
        log.info(superClazz.isAssignableFrom(subClazz) + "");
        log.info(superClazz.isAssignableFrom(subSubClazz) + "");
        log.info(this.getClass().isAssignableFrom(subClazz) + "");
        Assert.isAssignable(superClazz, this.getClass());
    }

    /**
     * 权限修饰符测试
     *
     */
    @Test
    public void modifyTest() throws NoSuchMethodException {
        Class<ModifyClass> clazz = ModifyClass.class;
        // 为了代码不报黄
        Class<?>[] parameterTypes = new Class<?>[]{};
        Constructor<ModifyClass> ctor = clazz.getDeclaredConstructor(parameterTypes);
        log.info(BeanUtils.objToString(Modifier.isPublic(ctor.getModifiers())));
        log.info(BeanUtils.objToString(Modifier.isPublic(ctor.getDeclaringClass().getModifiers())));
        ctor.setAccessible(true);
        log.info(BeanUtils.objToString(ctor.isAccessible()));
    }
}
