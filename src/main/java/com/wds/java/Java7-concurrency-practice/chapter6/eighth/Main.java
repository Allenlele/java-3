package com.wds.java.concurrency.chapter6.eighth;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 9
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//10
		final int THREADS = 100;
		AtomicIntegerArray vector = new AtomicIntegerArray(1000);
		
		//11
		Incrementer incrementer = new Incrementer(vector);
		
		//12
		Decrementer decrementer = new Decrementer(vector);
		
		//13
		Thread threadIncrementer[] = new Thread[THREADS];
		Thread threadDecrementer[] = new Thread[THREADS];
		
		//14
		for(int i = 0; i < THREADS; i++){
			threadIncrementer[i] = new Thread(incrementer);
			threadDecrementer[i] = new Thread(decrementer);
			
			threadIncrementer[i].start();
			threadDecrementer[i].start();
		}
		
		//15
		for(int i = 0; i < 100; i++){
			try {
				threadIncrementer[i].join();
				threadDecrementer[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//16
		for(int i = 0; i < vector.length(); i++){
			if(vector.get(i) != 0){
				System.out.println("Vector[" + i + "]:" + vector.get(i));
			}
		}
		
		System.out.println("Main: End of the example");
	}

}
