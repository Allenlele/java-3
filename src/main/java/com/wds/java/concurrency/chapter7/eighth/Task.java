package com.wds.java.concurrency.chapter7.eighth;

import java.util.concurrent.TimeUnit;

/**
 * 15
 * @author wds
 *
 */
public class Task implements Runnable {
	
	//16
	private MyLock lock;
	
	//17
	private String name;
	
	//18
	public Task(String name, MyLock lock){
		this.lock = lock;
		this.name = name;
	}

	//19
	@Override
	public void run() {
		lock.lock();
		System.out.printf("Task: %s: Take the lock \n", name);
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.printf("Task: %s: Free the lock\n", name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}

}
