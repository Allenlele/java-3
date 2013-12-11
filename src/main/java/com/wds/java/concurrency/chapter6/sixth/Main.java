package com.wds.java.concurrency.chapter6.sixth;

/**
 * 4
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//5
		Thread threads[] = new Thread[3];
		
		//6
		for(int i = 0; i < 3; i++){
			TaskLocalRandom task = new TaskLocalRandom();
			threads[i] = new Thread(task);
			threads[i].start();
		}

	}

}
