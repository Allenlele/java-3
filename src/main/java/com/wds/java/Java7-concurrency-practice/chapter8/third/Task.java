package com.wds.java.concurrency.chapter8.third;

import java.util.concurrent.TimeUnit;

/**
 * 1、创建Task，实现Runnable接口。
 * @author wds
 *
 */
public class Task implements Runnable {
	
	//2、定义long类型属性milliseconds
	private long milliseconds;
	
	//3、实现构造方法，并初始化属性
	public Task(long milliseconds){
		this.milliseconds = milliseconds;
	}

	//4、实现run()方法，使用线程休眠由milliseconds指定的时间
	@Override
	public void run() {
		System.out.printf("%s: Begin\n", Thread.currentThread().getName());
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: End\n", Thread.currentThread().getName());
	}

}
