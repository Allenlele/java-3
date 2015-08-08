package com.wds.java.concurrency.chapter7.third;

import java.util.concurrent.ThreadFactory;

/**
 * 10
 * @author wds
 *
 */
public class MyThreadFactory implements ThreadFactory {
	
	//11
	private int counter;
	
	//12
	private String prefix;
	
	//13
	public MyThreadFactory(String prefix) {
		this.prefix = prefix;
		counter = 1;
	}

	//14
	@Override
	public Thread newThread(Runnable r) {
		MyThread myThread = new MyThread(r, prefix + "-" + counter);
		counter++;
		return myThread;
	}

}
