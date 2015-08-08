package com.wds.java.concurrency.chapter4.ninth;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 1、创建ExecutableTask类，实现Callable接口，泛型化参数是String类
 * @author wds
 *
 */
public class ExecutableTask implements Callable<String> {
	//2、定义属性的String属性名为name，保存任务名称
	//实现getName()方法，返回name属性的值
	private String name;
	public String getName() {
		return name;
	}
	
	//3、实现构造方法，并初始化name属性
	public ExecutableTask(String name) {
		this.name = name;
	}

	//4、实现call()方法，将任务休眠随机时间，并返回包括任务名称的信息
	@Override
	public String call() throws Exception {
		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Waiting %d seconds for results.\n",this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
		}
		return "Hello, world. I'm " + name;
	}
	
}
