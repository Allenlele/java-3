package com.wds.java.concurrency.chapter8.first;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 4、创建Task类，实现Runnable接口
 * @author wds
 *
 */
public class Task implements Runnable {
	
	//5、声明Lock类的属性lock
	private Lock lock;
	
	//6、实现构造方法并初始化lock
	public Task(Lock lock){
		this.lock = lock;
	}

	//7、实现run()方法，创建5次的for循环
	@Override
	public void run() {
		for(int i = 0; i < 5; i++){
			
			//8、使用lock()方法获取锁，打印信息
			lock.lock();
			System.out.printf("%: Get the Lock.\n", Thread.currentThread().getName());
			
			
			//9、使用线程休眠500毫秒，使用unlock()方法释放锁并打印信息
			try {
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.printf("%s: Free the Lock.\n", Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
		}
	}

}
