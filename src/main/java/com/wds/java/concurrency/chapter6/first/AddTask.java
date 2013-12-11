package com.wds.java.concurrency.chapter6.first;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 1
 */
public class AddTask implements Runnable {
	
	//2
	private ConcurrentLinkedDeque<String> list;
	
	//3
	public AddTask(ConcurrentLinkedDeque<String> list) {
		this.list = list;
	}

	//4
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for(int i = 0; i < 10000; i++){
			list.add(name + " : Element " + i);
		}
	}

}
