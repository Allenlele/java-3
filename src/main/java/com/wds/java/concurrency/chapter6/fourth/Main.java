package com.wds.java.concurrency.chapter6.fourth;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 12
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{
		
		//13
		DelayQueue<Event> queue = new DelayQueue<>();
		
		//14
		Thread threads[] = new Thread[5];
		
		//15
		for(int i = 0; i < threads.length; i++){
			Task task = new Task(i + 1, queue);
			threads[i] = new Thread(task);
		}
		
		//16
		for(int i = 0; i < threads.length; i++){
			threads[i].start();
		}
		
		//17
		for(int i = 0; i < threads.length; i++){
			threads[i].join();
		}
		
		//18
		do {
			int counter = 0;
			Event event;
			do {
				event = queue.poll();
				if(event != null) counter++;
			} while (event != null);
			
			System.out.printf("At %s you have read %d events\n", new Date(), counter);
			TimeUnit.MILLISECONDS.sleep(500);
		} while (queue.size() > 0);
	}

}
