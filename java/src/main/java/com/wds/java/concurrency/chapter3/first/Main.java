package com.wds.java.concurrency.chapter3.first;

public class Main {

	public static void main(String[] args) {
		PrintQueue printQueue = new PrintQueue();
		
		Thread[] ts = new Thread[10];
		for(int i = 0; i < 10; i++){
			ts[i] = new Thread(new Job(printQueue), "Thread-" + i);
		}
		
		for (int i = 0; i < 10; i++) {
			ts[i].start();
		}
	}

}
