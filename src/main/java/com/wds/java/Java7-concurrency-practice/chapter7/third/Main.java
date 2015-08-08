package com.wds.java.concurrency.chapter7.third;


/**
 * 16
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{
		//17
		MyThreadFactory myFactory = new MyThreadFactory("MyThreadFactory");
		
		//18
		MyTask task = new MyTask();
		
		//19
		Thread thread = myFactory.newThread(task);
		
		//20
		thread.start();
		thread.join();
		
		//21
		System.out.printf("Main: Thread information.\n");
		System.out.printf("%s\n", thread);
		System.out.printf("Main: Enf of the Example\n");
	}

}
