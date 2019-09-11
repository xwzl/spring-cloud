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


# 2  人者见仁

- @ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
- @ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
- @ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
- @ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
- @ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
- @ConditionalOnNotWebApplication（不是web应用）
