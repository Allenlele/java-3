package com.wds.java.concurrency.chapter3.first;

import java.util.concurrent.Semaphore;

public class PrintQueue {
	
	private final Semaphore semaphore;
	
	public PrintQueue() {
		semaphore = new Semaphore(1);
	}
	
	public void printJob(Object document){
		try {
			semaphore.acquire();
			
			long duration = (long)(Math.random() * 10);
			
			System.out.printf("%s: PrintQueue: Printing a job during %d second\n", Thread.currentThread().getName(), duration);
			
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			semaphore.release();
		}
	}
}
