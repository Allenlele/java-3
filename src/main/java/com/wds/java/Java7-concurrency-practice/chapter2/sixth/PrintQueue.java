package com.wds.java.concurrency.chapter2.sixth;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2、在PrintQueue类中，修改锁的构造方法，如下
 * @author wds
 *
 */
public class PrintQueue {
	private final Lock queueLock = new ReentrantLock(true);
	
	/*
	 * 3、修改PrintJob()方法，分别在两个代码块中单独模拟打印功能
	 */
	public void printJob(Object document){
		queueLock.lock();
		try {
			Long duration = (long)(Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + " PrintQueue: Print a job during " + (duration / 1000));
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			queueLock.unlock();
		}
		
		try {
			queueLock.lock();
			Long duration = (long)(Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + " PrintQueue: Print a job during2 " + (duration / 1000));
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			queueLock.unlock();
		}
	}
}
