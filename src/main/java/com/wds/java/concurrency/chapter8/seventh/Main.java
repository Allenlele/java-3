package com.wds.java.concurrency.chapter8.seventh;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 12、实现主类及方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//13、创建两个Lock对象lock1,lock2
		Lock lock1, lock2;
		lock1 = new ReentrantLock();
		lock2 = new ReentrantLock();
		
		//14、创建Task对象task1
		Task1 task1 = new Task1(lock1, lock2);
		
		//15、创建Task对象task2
		Task2 task2 = new Task2(lock1, lock2);
		
		//16、创建线程并启动它们
		Thread thread1 = new Thread(task1);
		Thread thread2 = new Thread(task2);
		thread1.start();
		thread2.start();
		
		//17、当两个任务还未完成执行时，每隔500毫秒控制台输出信息，使用isAlive()方法检查线程是否已经执行结束
		while((thread1.isAlive() && (thread2.isAlive()))){
			System.out.printf("Main: The example is running");
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
