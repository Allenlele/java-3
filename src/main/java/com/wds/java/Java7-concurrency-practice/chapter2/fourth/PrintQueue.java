package com.wds.java.concurrency.chapter2.fourth;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、创建PrintQueue类
 * @author wds
 *
 */
public class PrintQueue {
	
	/*
	 * 2、定义Lock对象，并初始为ReentrantLock
	 */
	private final Lock queueLock = new ReentrantLock();
	
	/*
	 * 3、实现printJob()方法，接收一个Object对象有参数，无返回值
	 */
	public void printJob(Object document){
		//4、在printJob()方法中，调用lock()方法获取锁对象
		queueLock.lock();
		//5、try...catch中的代码模拟文档打印
		try {
			Long duration = (long)(Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + " PrintQueue: Print a job during " + (duration / 1000));
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		//6、调用unlock()方法释放锁
		}finally{
			queueLock.unlock();
		}
	}
}
