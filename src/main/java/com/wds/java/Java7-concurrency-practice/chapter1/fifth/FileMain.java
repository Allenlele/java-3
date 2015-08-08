package com.wds.java.concurrency.chapter1.fifth;

import java.util.concurrent.TimeUnit;

/**
 * 4、已经有了实现的线程类，接下来创建FileMain类，并实现main()方法
 * @author wds
 *
 */
public class FileMain {

	public static void main(String[] args) {
		
		//5、创建FileClock对象和线程，并启动执行
		FileClock clock = new FileClock();
		Thread thread = new Thread(clock);
		thread.start();
		
		//6、调用TimeUnit.SECONDS.sleep()方法使线程睡眠5秒钟
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//7、中断FileClock的线程
		thread.interrupt();
	}

}
