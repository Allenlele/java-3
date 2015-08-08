package com.wds.java.concurrency.chapter6.eighth;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 1
 */
public class Incrementer implements Runnable {
	
	//2
	private AtomicIntegerArray vector;
	
	//3
	public Incrementer(AtomicIntegerArray vector){
		this.vector = vector;
	}

	//4
	@Override
	public void run() {
		for(int i = 0; i < vector.length(); i++){
			vector.getAndIncrement(i);
		}
	}

}
