package com.wds.java.concurrency.chapter4.seventh;

import java.util.Date;

/**
 * 1、创建Task类，实现Runnable接口
 * @author wds
 *
 */
public class Task implements Runnable {
	//2、定义私有String属性name，保存任务名称
	private String name;
	//3、实现构造方法，并初始属性
	public Task(String name) {
		super();
		this.name = name;
	}
	//4、实现run()方法，控制台输出当前日期，以检验任务是否在指定的周期内运行
	public String call() throws Exception {
		System.out.printf("%s: Starting at %s\n", name, new Date());
		return "Hello, World";
	}
	
	@Override
	public void run() {
		System.out.printf("%s: Starting at %s\n", name, new Date());		
	}

}
