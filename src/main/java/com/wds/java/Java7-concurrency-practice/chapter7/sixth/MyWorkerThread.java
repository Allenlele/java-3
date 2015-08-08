package com.wds.java.concurrency.chapter7.sixth;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * 1
 * @author wds
 *
 */
public class MyWorkerThread extends ForkJoinWorkerThread {
	
	//2
	private static ThreadLocal<Integer> taskCounter = new ThreadLocal<>();
	
	//3
	protected MyWorkerThread(ForkJoinPool pool) {
		super(pool);
	}
	
	//4
	@Override
	protected void onStart() {
		super.onStart();
		System.out.printf("MyWorkerThread %d: Initializing task counter.\n", getId());
		taskCounter.set(0);
	}
	
	//5
	@Override
	protected void onTermination(Throwable exception) {
		System.out.printf("MyWorkerThread %d: %d\n",getId(), taskCounter.get());
		super.onTermination(exception);
	}

	//6
	public void addTask(){
		int counter = taskCounter.get().intValue();
		counter++;
		taskCounter.set(counter);
	}
	
}
