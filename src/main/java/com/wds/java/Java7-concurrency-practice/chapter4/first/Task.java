package com.wds.java.concurrency.chapter4.first;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 1、首先实现一个任务，由服务器执行，创建类Task，实现Runnable接口
 * @author wds
 *
 */
public class Task implements Runnable {
	//2、创建Date属性initDate保存任务创建时间，String属性name保存任务名称
	private Date initDate;
	private String name;
	//3、实现构造方法并初始化这两个属性
	public Task(String name) {
		this.name = name;
		initDate = new Date();
	}
	//4、实现run()方法
	@Override
	public void run() {
		//5、首先，向控制台输入initDate属性的值及任务的启动日期
		System.out.printf("%s: Task %s: Created on: %s\n",Thread.currentThread().getName(),name,initDate);
		System.out.printf("%s: Task %s: Started on: %s\n",Thread.currentThread().getName(),name,new Date());
		//6、之后，将线程休眠随机时间
		try {
			Long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Task %s: Doing a task during %d seconds\n", Thread.currentThread().getName(), name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//7、控制台输出任务完成日期
		System.out.printf("%s: Task %s: Finished on: %s\n",Thread.currentThread().getName(),name,new Date());
	}

}
