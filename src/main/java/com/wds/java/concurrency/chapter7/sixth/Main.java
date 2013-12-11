package com.wds.java.concurrency.chapter7.sixth;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 15
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		//16
		MyWorkerThreadFactory facotry = new MyWorkerThreadFactory();
		
		//17
		ForkJoinPool pool = new ForkJoinPool(4, facotry, null, false);
		
		//18
		int array[] = new int[100000];
		
		//19
		MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length);
		
		//20
		pool.execute(task);
		
		//21
		task.join();
		
		//22
		pool.shutdown();
		
		//23
		pool.awaitTermination(1, TimeUnit.DAYS);
		
		//24
		System.out.printf("Main: Result: %d\n", task.get());
		
		//25
		System.out.printf("Main: End of the program\n");
	}

}
