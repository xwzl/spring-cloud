java -jar xxx-spring-boot.jar

通过 Spring-boot 插件打包，java -jar 命令会启动 JarLauncher 类型启动，加载 Jar 中引入的jar包，最终通过反射的方式调用 main 方法。

-javaagent:/Users/xuweizhi/Documents/projects/docker-container/skywalking-agent/skywalking-agent.jar
-Dskywalking.agent.service_name=spring-cloud
-Dskywalking.collector.backend_service=1.15.19.68:11800

-Dskywalking.collector.backend_service=1.15.19.68:11800
