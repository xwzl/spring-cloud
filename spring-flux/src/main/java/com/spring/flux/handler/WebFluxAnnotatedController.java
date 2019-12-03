package com.spring.flux.handler;

import com.spring.flux.modle.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xuweizhi
 * @since 2019/12/03 18:11
 */
@Slf4j
@RestController
@RequestMapping("/annotated")
public class WebFluxAnnotatedController {

    @Resource
    private UserRepository userRepository;

    /**
     * 查询单个用户
     *
     * @param id
     * @return 返回Mono 非阻塞单个结果
     */
    @GetMapping("user/{id}")
    public Mono<UserVO> getUserByUserId(@PathVariable("id") int id) {
        return Mono.just(userRepository.getUserByUserId().get(id));
    }

    /**
     * @return 返回Flux 非阻塞序列
     */
    @GetMapping("users")
    public Flux<UserVO> getAll() {
        printlnThread("获取HTTP请求");
        //使用lambda表达式
        return Flux.fromStream(userRepository.getUsers().entrySet().stream().map(Map.Entry::getValue));
    }

    /**
     * 打印当前线程
     *
     * @param object
     */
    private void printlnThread(Object object) {
        String threadName = Thread.currentThread().getName();
        log.info("HelloWorldAsyncController[" + threadName + "]: " + object);
    }
}
