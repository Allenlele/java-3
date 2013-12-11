package com.wds.java.concurrency.chapter7.sixth;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * 7
 * @author wds
 *
 */
public class MyWorkerThreadFactory implements ForkJoinWorkerThreadFactory {

	@Override
	public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
		return new MyWorkerThread(pool);
	}

	
}
