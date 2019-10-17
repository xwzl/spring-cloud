package com.spring.base.jdk8.json;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.spring.base.guava.Student;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author xuweizhi
 * @since 2019/10/17 10:57
 */
public class JsonUtil {

    private final static String SPLIT_COMMA = ",";

    private final static String SPLIT_COLON = ":";

    private final static String JSON_TAG = "\"";

    private final static String ARRAY_TAG_PREFIX = "[";

    private final static String ARRAY_TAG_SUFFIX = "]";

    private final static String JSON_TAG_PREFIX = "{";

    private final static String JSON_TAG_SUFFIX = "}";

    private final static String END_WITH_JSON = ",}";

    private final static String END_WITH_ARRAY = ",]";


    /**
     * json 字符串转换
     *
     * @param object 对象
     * @return json 字符串
     */
    @Contract("null -> !null; !null -> null")
    private static Object toJsonString(Object object) {
        StringBuffer sb = new StringBuffer();
        try {
            Class<?> clazz = object.getClass();
            if (Objects.isNull(object)) {
                return null;
            }
            // 解析字符串
            if (String.class.getName().equals(clazz.getTypeName())) {
                return (String) object;
            }
            // 解析数组
            if (clazz.isArray()) {
                Object[] array = (Object[]) object;
                sb.append(ARRAY_TAG_PREFIX);
                for (Object o : array) {
                    Object s = toJsonString(o);
                    arrayConvertValue(sb, o, s);
                }
                sb.append(ARRAY_TAG_SUFFIX);
                return spliceSuffix(sb);
            }
            // 解析集合
            if (object instanceof Collection) {
                Collection object1 = (Collection) object;
                sb.append(ARRAY_TAG_PREFIX);
                for (Object o : object1) {
                    Object s = toJsonString(o);
                    arrayConvertValue(sb, o, s);
                }
                sb.append(ARRAY_TAG_SUFFIX);
                return spliceSuffix(sb);
            }
            // 解析 map
            if (object instanceof Map) {
                Map<Object, Object> object1 = (Map) object;
                sb.append(JSON_TAG_PREFIX);
                for (Map.Entry<Object, Object> objectObjectEntry : object1.entrySet()) {
                    spliceColon(sb, toJsonString(objectObjectEntry.getKey()));
                    spliceColon(sb, toJsonString(objectObjectEntry.getValue()));
                }
                sb.append(JSON_TAG_SUFFIX);
                return spliceSuffix(sb);
            }
            // 解析数字
            if (object instanceof Number) {
                return object;
            }
            if (clazz.isPrimitive()) {
                return object;
            }
            // 处理常见类型
            //if (object instanceof Date) {
            //    return "时间类型";
            //}
            Map<String, Object> result = handlerCustomer(object);
            if (Objects.nonNull(result.get("data"))) {
                return result.get("data");
            }
            sb.append(JSON_TAG_PREFIX);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object o = field.get(object);
                if (Objects.isNull(o)) {
                    continue;
                }
                Object value = toJsonString(o);
                if (Objects.nonNull(value)) {
                    if (o instanceof String || o instanceof Date) {
                        spliceColon(sb, field.getName());
                        spliceComma(sb, value);
                    } else {
                        sb.append(JSON_TAG).append(field.getName()).append(JSON_TAG).append(SPLIT_COLON);
                        sb.append(value).append(SPLIT_COMMA);
                    }
                }
            }
            sb.append(JSON_TAG_SUFFIX);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spliceSuffix(sb);
    }

    /**
     * 数组在转换对象
     *
     * @param sb
     * @param o
     * @param s
     */
    private static void arrayConvertValue(StringBuffer sb, Object o, Object s) {
        if (o instanceof String || o instanceof Date) {
            spliceComma(sb, s);
        } else {
            sb.append(s).append(SPLIT_COMMA);
        }
    }

    @NotNull
    private static String spliceSuffix(StringBuffer sb) {
        return sb.toString().endsWith(END_WITH_ARRAY) ?
                sb.toString().replace(END_WITH_ARRAY, ARRAY_TAG_SUFFIX) :
                sb.toString().endsWith(END_WITH_JSON) ?
                        sb.toString().replace(END_WITH_JSON, JSON_TAG_SUFFIX) : sb.toString();
    }

    private static Map<String, Object> handlerCustomer(Object object) {
        Map<String, Object> result = new HashMap<>(1);
        if (object instanceof Boolean) {
            result.put("data", object);
        }
        if (object instanceof Character) {
            result.put("data", object);
        }
        if (object instanceof Date) {
            result.put("data", DateUtil.date((Date) object).toString("yyyy-MM-dd HH:mm:ss"));
        }
        return result;
    }

    @Test
    public void arrayTest() {
        JsonModel jsonModel = new JsonModel();
        jsonModel.setInts(new String[]{"111", "222"});
        System.out.println(toJsonString(jsonModel));
    }

    /**
     * key + :
     *
     * @param sb         StringBuilder
     * @param keyOrValue key
     * @return StringBuilder
     */
    @NotNull
    private static StringBuffer spliceColon(StringBuffer sb, Object keyOrValue) {
        return spliceQuote(sb, keyOrValue, true);
    }

    /**
     * value + ,
     *
     * @param sb         StringBuilder
     * @param keyOrValue value
     * @return StringBuilder
     */
    @NotNull
    private static StringBuffer spliceComma(StringBuffer sb, Object keyOrValue) {
        return spliceQuote(sb, keyOrValue, false);
    }

    /**
     * value + , and key + :
     *
     * @param sb         StringBuilder
     * @param keyOrValue value
     * @param flag       flag
     * @return StringBuilder
     */
    @NotNull
    private static StringBuffer spliceQuote(StringBuffer sb, Object keyOrValue, boolean flag) {
        sb.append(JSON_TAG).append(keyOrValue).append(JSON_TAG);
        return flag ? sb.append(SPLIT_COLON) : sb.append(SPLIT_COMMA);
    }

    @Test
    public void test() {
        Student student = new Student();
        student.setName("0");
        student.setScore(13);
        student.setAge(0);
        System.out.println(toJsonString(student));
    }

    @Test
    public void array() {
        System.out.println(JSONObject.toJSONString(null));
        JsonModel jsonModel = new JsonModel();
        jsonModel.setInts(new Object[]{new Student("12", 1), new Student("12", 1)});
        jsonModel.setList(Arrays.asList(new Student("12", 1), new Student("12", 1)));
        jsonModel.setAnInt(1);
        jsonModel.setAFloat(2);
        jsonModel.setABoolean(true);
        jsonModel.setInteger(1);
        jsonModel.setValue("121");
        jsonModel.setDate(new Date());
        jsonModel.setStudent(new Student("1", 12));
        long s = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            JSONObject.toJSONString(jsonModel);
        }
        long y = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            toJsonString(jsonModel);
        }
        long z = System.currentTimeMillis();
        System.out.println(y - s);
        System.out.println(z - y);
    }

    public static void main(String[] args) throws IllegalAccessException {
        Student student = new Student();
        student.setName("0");
        student.setScore(13);
        student.setAge(0);
        Class<? extends Student> aClass = student.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            System.out.println(type.equals(String.class));
            Object o = field.get(student);
        }
    }


}
