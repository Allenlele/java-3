package com.wds.java.concurrency.chapter8.seventh;

import java.util.concurrent.locks.Lock;

/**
 * 6、创建Task2，并实现Runnable接口
 * @author wds
 *
 */
public class Task2 implements Runnable {
	//7、定义Lock类型的lock1和lock2
	private Lock lock1, lock2;
	
	//8、实现构造方法，并初始化属性
	public Task2(Lock lock1, Lock lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	//9、实现run()方法，首先获得lock2对象的控制权，控制台输出信息表明已经获取
	@Override
	public void run() {
		lock2.lock();
		System.out.printf("Task 2: Lock 2 locked\n");
		
		//10、之后获取lock2对象的控制，控制台输出信息表明已经获取
		lock1.lock();
		System.out.printf("Task 2: Lock 1 locked\n");
		
		//11、最后释放，先是lock1，后是lock2
		lock1.unlock();
		lock2.unlock();
	}

}
