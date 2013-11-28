package com.wds.java.concurrency.chapter5.fifth;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 27
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//28
		ArrayGenerator generator = new ArrayGenerator();
		int array[] = generator.generateArray(1000);
		
		//29
		TaskManager manager = new TaskManager();
		
		//30
		ForkJoinPool pool = new ForkJoinPool();
		
		//31
		//源代码为Task，更正为SearchNumberTask
		SearchNumberTask task = new SearchNumberTask(array, 0, 1000, 5, manager);
		
		//32
		pool.execute(task);
		
		//33
		pool.shutdown();
		
		//34
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//35
		System.out.printf("Main: The program has finished\n");
		
	}

}
