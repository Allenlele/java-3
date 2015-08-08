package com.wds.java.concurrency.chapter6.first;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 5
 * @author wds
 *
 */
public class PollTask implements Runnable {
	
	//6
	private ConcurrentLinkedDeque<String> list;
	
	//7
	public PollTask(ConcurrentLinkedDeque<String> list) {
		this.list = list;
	}

	//8
	@Override
	public void run() {
		for(int i = 0; i < 5000; i++){
			list.pollFirst();
			list.pollLast();
		}
	}

}
