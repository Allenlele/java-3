package com.wds.java.concurrency.chapter1.ninith;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 1、首先，实现一个有刚才所述问题的程序，创建UnSafeTask类并实现Runnable接口，
 * 声明私有java.util.Date类型的变量
 * @author wds
 *
 */
public class UnSafeTask implements Runnable {

	private Date startDate;
	
	/**
	 * 2、实现UnSafeTask的run()方法，初始化startDate属性，并将其值打印到控制中，
	 * 之后使线程睡眠，睡眠时间随机，之后再次输出startDate的值
	 */
	@Override
	public void run() {
		startDate = new Date();
		System.out.printf("Starting Thread %s: %s\n", Thread.currentThread().getId(), startDate);
		
		try {
			TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Finished Thread %s: %s\n", Thread.currentThread().getId(), startDate);
	}

}
