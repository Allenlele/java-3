package com.wds.java.concurrency.chapter1.ninith;

import java.util.concurrent.TimeUnit;

/**
 * 3、现在实现有问题程序的主类，创建Main类及main()方法，创建UnSafeTask对象，
 * 启动线程，两线程之间睡眠两秒
 * @author wds
 *
 */
public class Test {

	public static void main(String[] args) {
		UnSafeTask task = new UnSafeTask();
		for(int i = 0; i < 10; i++){
			Thread t = new Thread(task);
			t.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		SafeTask sTask = new SafeTask();
		for(int i = 0; i < 10; i++){
			Thread t = new Thread(sTask);
			t.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
