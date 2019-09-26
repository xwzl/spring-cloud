package com.spring.demo.config.async.http;

/**
 * 队列监听器，初始化启动所有监听任务
 * <p>
 * 从Java EE5规范开始，Servlet增加了两个影响Servlet生命周期的注解（Annotation）：@PostConstruct和@PreConstruct。这两个注解被用来修饰
 * 一个非静态的void()方法.而且这个方法不能有抛出异常声明。
 *
 * @author xuweizhi
 */
//@Component
//@Slf4j
public class QueueListener {

    //@Autowired
    //private OrderTask orderTask;
    //
    ///**
    // * PostConstruct 注释用于在依赖关系注入完成之后需要执行的方法上，以执行任何初始化。此方法必须在将类放入服务之前调用。支持依赖关系
    // * 注入的所有类都必须支持此注释。即使类没有请求注入任何资源，用 PostConstruct 注释的方法也必须被调用。只有一个方法可以用此注释进行
    // * 注释。应用 PostConstruct 注释的方法必须遵守以下所有标准：该方法不得有任何参数，除非是在 EJB 拦截器 (interceptor) 的情况下，根
    // * 据 EJB 规范的定义，在这种情况下它将带有一个 InvocationContext 对象 ；该方法的返回类型必须为 void；该方法不得抛出已检查异常；
    // * 应用 PostConstruct 的方法可以是 public、protected、package private 或 private；除了应用程序客户端之外，该方法不能是 static；
    // * 该方法可以是 final；如果该方法抛出未检查异常，那么不得将类放入服务中，除非是能够处理异常并可从中恢复的 EJB。
    // * <p>
    // * 初始化时启动监听请求队列
    // */
    //@PostConstruct
    //public void init() {
    //    log.info("Asynchronous call order, the listen queue has started");
    //    orderTask.start();
    //}
    //
    ///**
    // * 销毁容器时停止监听任务
    // */
    //@PreDestroy
    //public void destroy() {
    //    log.info("订单监听队列停止");
    //    orderTask.setRunning(false);
    //}

}