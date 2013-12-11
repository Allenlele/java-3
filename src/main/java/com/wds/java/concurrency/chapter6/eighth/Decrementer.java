package com.wds.java.concurrency.chapter6.eighth;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 5
 * @author wds
 *
 */
public class Decrementer implements Runnable {
	
	//6
	private AtomicIntegerArray vector;
	
	//7
	public Decrementer(AtomicIntegerArray vector){
		this.vector = vector;
	}

	//8
	@Override
	public void run() {
		for(int i = 0; i < vector.length(); i++){
			vector.getAndDecrement(i);
		}
	}

}
