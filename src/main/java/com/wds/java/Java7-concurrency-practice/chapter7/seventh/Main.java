package com.wds.java.concurrency.chapter7.seventh;

import java.util.concurrent.ForkJoinPool;

/**
 * 15
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//16
		int array[] = new int[10000];
		
		//17
		ForkJoinPool pool = new ForkJoinPool();
		
		//18
		Task task = new Task("Task", array, 0, array.length);
		
		//19
		pool.invoke(task);
		
		//20
		pool.shutdown();
		
		//21
		System.out.printf("Main: End of the program.\n");
	}

}
