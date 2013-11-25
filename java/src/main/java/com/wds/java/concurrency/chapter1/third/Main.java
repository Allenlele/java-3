package com.wds.java.concurrency.chapter1.third;

import java.util.concurrent.TimeUnit;

/**
 * 5、实现主类Main,及main方法
 * @author wds
 *
 */
public class Main {
	
	public static void main(String[] args) {
		//6、创建并启动PrimeGenerator对象
		Thread task = new PrimeGenerator();
		task.start();
		
		//7、等待和秒钟，并中断PrimeGenerator线程
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		task.interrupt();
	}

}
