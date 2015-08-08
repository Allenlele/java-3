package com.wds.java.concurrency.chapter6.first;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 9
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//10
		ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
		
		//11
		Thread[] threads = new Thread[100];
		
		//12
		for(int i = 0; i < threads.length; i++){
			AddTask task = new AddTask(list);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		System.out.printf("Main: %d Add Task threads have been launched\n", threads.length);
		
		//13
		for(int i = 0; i < threads.length; i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//14
		System.out.printf("Main: Size of the List: %d\n", list.size());
		
		//15
		for(int i = 0; i < threads.length; i++){
			PollTask task = new PollTask(list);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		System.out.printf("Main: %d PollTask threads have been launched\n", threads.length);
		
		//16
		for(int i = 0; i < threads.length; i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//17
		System.out.printf("Main: Size of the List: %d\n", list.size());
		
	}

}
