package com.wds.java.concurrency.chapter6.fourth;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * 6
 * @author wds
 *
 */
public class Task implements Runnable {
	
	//7
	private int id;
	
	//8
	private DelayQueue<Event> queue;
	
	//9
	public Task(int id, DelayQueue<Event> queue) {
		this.id = id;
		this.queue = queue;
	}

	//10
	@Override
	public void run() {
		Date now = new Date();
		Date delay = new Date();
		delay.setTime(now.getTime() + (id * 1000));
		System.out.printf("Thread %s: %s\n", id, delay);
		
		//11
		for(int i = 0; i < 100; i++){
			Event event = new Event(delay);
			queue.add(event);
		}
	}

}
