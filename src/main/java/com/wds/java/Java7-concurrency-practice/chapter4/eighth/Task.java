package com.wds.java.concurrency.chapter4.eighth;

import java.util.concurrent.Callable;

/**
 * 1、创建Task类，并实现Callable接口，泛型化参数为String类，实现call()方法，
 * 在一个无限循环中向控制台输出信息并使线程睡眠100毫秒
 * @author wds
 *
 */
public class Task implements Callable<String> {

	@Override
	public String call() throws Exception {
		while(true){
			System.out.printf("Task: Test\n");
			Thread.sleep(100);
		}
	}

}
