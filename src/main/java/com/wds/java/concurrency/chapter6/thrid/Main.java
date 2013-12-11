package com.wds.java.concurrency.chapter6.thrid;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 13
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//14
		PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
		
		//15
		Thread taskThreads[] = new Thread[5];
		
		//16
		for(int i = 0; i < taskThreads.length; i++){
			Task task = new Task(i, queue);
			taskThreads[i] = new Thread(task);
		}
		
		//17
		for(int i = 0; i < taskThreads.length; i++){
			taskThreads[i].start();
		}
		
		//18
		for(int i =0; i < taskThreads.length; i++){
			try {
				taskThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//19
		System.out.printf("Main: Queue Size: %d\n", queue.size());
		for(int i = 0; i < taskThreads.length * 1000; i++){
			Event event = queue.poll();
			System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());
		}
		
		//20
		System.out.printf("Main: Queue Size: %d\n", queue.size());
		System.out.printf("Main: End of the program\n");
	}

}
