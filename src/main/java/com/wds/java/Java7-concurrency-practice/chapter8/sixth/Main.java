package com.wds.java.concurrency.chapter8.sixth;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 5、创建主类及main()方法。
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//6、定义ReentrantLock对象lock
		ReentrantLock lock = new ReentrantLock();
		
		//7、创建10个任务，10个线程运行这些途程，调用run()方法启用线程
		for(int i = 0; i < 10; i++){
			Task task = new Task(lock);
			Thread thread = new Thread(task);
			thread.run();
		}

	}

}
