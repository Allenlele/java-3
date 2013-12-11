package com.wds.java.concurrency.chapter6.thrid;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 8
 * @author wds
 *
 */
public class Task implements Runnable {
	
	//9
	private int id;
	
	//10
	private PriorityBlockingQueue<Event> queue;
	
	//11
	public Task(int id, PriorityBlockingQueue<Event> queue) {
		this.id = id;
		this.queue = queue;
	}

	/*
	 * 12
	 */
	@Override
	public void run() {
		for(int i = 0; i < 1000; i++){
			Event event = new Event(id, i);
			queue.add(event);
		}
	}

}
