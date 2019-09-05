## 1 常见注解解释　　

- @ConditionalOnBean ：匹配给定的class类型或者Bean的名字是否在SpringBeanFactory中存在
- @ConditionalOnClass：匹配给定的class类型是否在类路径(classpath)中存在
- @ConditionalOnExpression ： 匹配给定springEL表达式的值返回true时
- @ConditionalOnJava ：匹配JDK的版本，其中range属性是枚举类型有两个值可以选择 
    - EQUAL_OR_NEWER 不小于
    - OLDER_THAN 小于
    - value属性用于设置jdk版本
- @ConditionalOnMissingBean：spring上下文中不存在指定bean时
- @ConditionalOnWebApplication：在web环境下创建

注意：Conditional 只有在所有配置类被加载完的时候被评估是否要创建，因此Conditional不能在配置类里根据其他创建的方法进行判断


