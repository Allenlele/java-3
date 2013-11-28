package com.wds.java.concurrency.chapter5.fourth;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 10
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//11
		int array[] = new int[100];
		
		//12
		Task task = new Task(array, 0, 100);
		
		//13
		ForkJoinPool pool = new ForkJoinPool();
		
		//14
		pool.execute(task);
		
		//15
		pool.shutdown();
		
		//16
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//17
		if(task.isCompletedAbnormally()){
			System.out.printf("Main: An exception has ocurred\n");
			System.out.printf("Main: %s\n", task.getException());
		}
		System.out.printf("Main: Result: 5d", task.join());
	}

}
