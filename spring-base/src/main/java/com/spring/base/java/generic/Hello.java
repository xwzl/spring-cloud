package com.spring.base.java.generic;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试类
 *
 * @author xuweizhi
 * @since 2019/12/31 14:19
 */
@Slf4j
class Hello<E, K> {

    public static <E, K> void consume(E e, K k, GenericInterface<E, K> genericInterface) {
        log.info(genericInterface.getK(e, k).toString());
    }

    public static void main(String[] args) {
        //Hello.consume("12", new HashMap<String, String>() {
        //    {
        //        put("11", "11");
        //    }
        //}, (s, hashMap) -> {
        //    hashMap.put(s, s);
        //    return hashMap;
        //});
        UUID uuid = UUID.randomUUID(true);
        System.out.println(uuid.toString().substring(0, 30));
    }
}
