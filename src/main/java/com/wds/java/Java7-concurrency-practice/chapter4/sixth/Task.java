package com.wds.java.concurrency.chapter4.sixth;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * 1、创建Task类，实现Callable接口，泛型参数为String类
 * @author wds
 *
 */
public class Task implements Callable<String>{
	//2、定义私有String类型的name，保存任务名称
	private String name;
	//3、实现构造方法，并初始化name属性
	public Task(String name) {
		this.name = name;
	}
	//4、实现call()方法，控制台输出当前日期、返回的文本，例如Hello, world。
	@Override
	public String call() throws Exception {
		System.out.printf("%s: Starting at : %s\n", name, new Date());
		return "Hello, world";
	}

}
