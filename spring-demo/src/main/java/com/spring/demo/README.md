java -jar xxx-spring-boot.jar

通过 Spring-boot 插件打包，java -jar 命令会启动 JarLauncher 类型启动，加载 Jar 中引入的jar包，最终通过反射的方式调用 main 方法。
