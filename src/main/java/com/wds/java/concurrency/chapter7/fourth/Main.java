package com.wds.java.concurrency.chapter7.fourth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 2
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{
		
		//3
		MyThreadFactory threadFactory = new MyThreadFactory("MyThreadFactory");
		
		//4
		ExecutorService executor = Executors.newCachedThreadPool(threadFactory);
		
		//5
		MyTask task = new MyTask();
		executor.submit(task);
		
		//6
		executor.shutdown();
		
		//7
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		//8
		System.out.printf("Main: End of the program.");
		
		//源文中的截图有Thread:MyThreadFacotry-1的输入信息
		//实则只会输出End of the program，无其它信息输出
	}

}
