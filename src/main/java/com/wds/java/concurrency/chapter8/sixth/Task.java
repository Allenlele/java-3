package com.wds.java.concurrency.chapter8.sixth;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、创建Task类，实现Runnable接口
 * @author wds
 *
 */
public class Task implements Runnable {
	
	//2、定义私有ReentrantLock类型的属性lock
	private ReentrantLock lock;
	
	//3、实现构造方法
	public Task(ReentrantLock lock){
		this.lock = lock;
	}

	//4、实现run()方法，获取对锁的控制，使用线程睡眠2秒，然后释放
	@Override
	public void run() {
		lock.lock();
		try {
			TimeUnit.SECONDS.sleep(1);
			lock.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
