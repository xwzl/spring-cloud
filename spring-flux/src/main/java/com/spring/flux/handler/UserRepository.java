package com.spring.flux.handler;

import com.spring.flux.modle.UserVO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户仓储
 *
 * @author xuweizhi
 * @since 2019/12/03 17:19
 */
@Repository
public class UserRepository {

    /**
     * 模拟数据库存储
     */
    private static Map<Integer, UserVO> userMap = new HashMap<>();

    //初始化仓储数据
    static {
        UserVO user1 = new UserVO();
        user1.setId(1);
        user1.setName("用户1");
        userMap.put(1, user1);
        UserVO user2 = new UserVO();
        user2.setId(2);
        user2.setName("用户2");
        userMap.put(2, user2);
    }

    public Map<Integer, UserVO> getUserByUserId() {
        printlnThread("调用getUserByUserId");
        return userMap;
    }


    public Map<Integer, UserVO> getUsers() {
        printlnThread("调用getUsers");
        return userMap;
    }

    /**
     * 打印当前线程
     *
     * @param object
     */
    private void printlnThread(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("HelloWorldAsyncController[" + threadName + "]: " + object);
    }
}
