package com.wds.java.concurrency.chapter8.seventh;

import java.util.concurrent.locks.Lock;

/**
 * 1、创建Task1类，实现Runnable接口
 * @author wds
 *
 */
public class Task1 implements Runnable {
	
	//2、定义Lock类型的属性lock1,lock2
	private Lock lock1, lock2;
	
	//3、实现构造方法，并初始化属性
	public Task1(Lock lock1, Lock lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	//4、实现run()方法，首先获取lock1的控制权，并在控制台输出信息表明已经获取
	@Override
	public void run() {
		lock1.lock();
		System.out.printf("Task 1 : Lock 1 locked\n");
		
		//5、之后获取lock2的控制，控制台输出信息表明已经获得
		lock2.lock();
		System.out.printf("Task 1: Lock 2 locked\n");
		//最后释放锁
		lock2.unlock();
		lock1.unlock();
	}

}
