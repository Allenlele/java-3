package com.wds.java.concurrency.chapter1.fourth;

import java.util.concurrent.TimeUnit;

/**
 * 6、实现Main类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//7、创建FileSearch对象及线程并执行任务
		FileSearch searcher = new FileSearch("D:\\foxmail\\Template", "offline.html");
		Thread thread = new Thread(searcher);
		thread.start();
		
		//8、等待10秒，并中断线程
		try {
			TimeUnit.MICROSECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread.interrupt();
	}

}
