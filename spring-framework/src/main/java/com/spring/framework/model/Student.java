package com.spring.framework.model;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于 bean 加载阶段修改和注册 BeanDefinition 信息
 *
 * @author xuweizhi
 * @since 2020/12/04 21:49
 */
@Data
@Slf4j
@ToString
public class Student {

	private String username;

	private School school;

	/**
	 * 测试懒加载，默认加载初始化所有 bean
	 */
	public Student() {
		log.info("测试 Lazy 加载");
	}

	public void initMethod() {
		log.info("初始化调用该方法");
	}
}
