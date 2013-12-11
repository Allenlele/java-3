package com.wds.java.concurrency.chapter7.second;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 8
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//9
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
		
		//10
		for(int i = 0 ; i < 4; i++){
			MyPriorityTask task = new MyPriorityTask("Task " + i, i);
			executor.execute(task);
		}
		
		//11
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//12
		for(int i = 4; i < 8; i++){
			MyPriorityTask task = new MyPriorityTask("Task " + i, i);
			executor.execute(task);
		}
		
		//13
		executor.shutdown();
		
		//14
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//15
		System.out.printf("Main: End of the program.\n");
	}

}
