package com.wds.java.concurrency.chapter7.eighth;

import java.util.concurrent.TimeUnit;

/**
 * 20
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//21
		MyLock lock = new MyLock();
		
		//22
		for(int i = 0; i < 10; i++){
			Task task = new Task("Task-" + i, lock);
			Thread thread = new Thread(task);
			thread.start();
		}
		
		//23
		boolean value;
		do {
			try {
				value = lock.tryLock(1, TimeUnit.SECONDS);
				if(!value){
					System.out.printf("Main: Trying to get the lock\n");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				value = false;
			}
		} while (!value);
	}

}
