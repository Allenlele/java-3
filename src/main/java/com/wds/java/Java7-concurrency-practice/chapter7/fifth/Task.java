package com.wds.java.concurrency.chapter7.fifth;

import java.util.concurrent.TimeUnit;

/**
 * 17
 * @author wds
 *
 */
public class Task implements Runnable {

	//18
	@Override
	public void run() {
		
		System.out.printf("Task: Begin.\n");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Task: End.\n");

	}

}
