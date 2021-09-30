# 1. 注册阶段

- BeanFactoryPostProcessor: BeanDefinition 注册完成后触发
- BeanDefinitionRegistryPostProcessor: BeanDefinition 添加自定义 Bean

# 2. BeanPostProcessor

该接口我们也叫后置处理器，作用是在Bean对象在实例化和依赖注入完毕后，在显示调用初始化方法的前后添加我们自己的逻辑。注意是Bean实例化完毕后及依赖注入完成后触发的。