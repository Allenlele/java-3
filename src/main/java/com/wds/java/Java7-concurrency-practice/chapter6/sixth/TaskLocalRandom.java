package com.wds.java.concurrency.chapter6.sixth;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 1
 * @author wds
 *
 */
public class TaskLocalRandom implements Runnable {
	
	//2
	public TaskLocalRandom() {
		ThreadLocalRandom.current();
	}

	//3
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for(int i = 0; i < 10; i++){
			System.out.printf("%s: %d\n", name, ThreadLocalRandom.current().nextInt(10));
		}
		
	}

}
