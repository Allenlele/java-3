package com.wds.java.concurrency.chapter1.ninith;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 6、创建SafeTask，并实现Runnable接口
 * @author wds
 *
 */
public class SafeTask implements Runnable{
	
	/**
	 * 7、声明ThreadLocal<Date>对象，实现initialValue()方法，返回当前日期
	 */
	private static ThreadLocal<Date> startDate = new ThreadLocal<Date>(){
		protected Date initialValue() {
			return new Date();
		}
	};

	
	/**
	 * 8、实现run()方法，与UnSafeTask的run()是相同的功能，但是获取startDate的属性值是不相同的
	 */
	@Override
	public void run() {
		System.out.printf("Starting Thread %s: %s\n", Thread.currentThread().getId(), startDate.get());
		
		try {
			TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Finished Thread %s: %s\n", Thread.currentThread().getId(), startDate.get());
	}

}
