package com.wds.java.concurrency.chapter7.fifth;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 19
 * 代码运行报错，翻译时，再验证
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{
		
		//20
		MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(2);
	
		//21
		Task task = new Task();
		System.out.printf("Main: %s\n", new Date());
		
		//22
		executor.schedule(task, 1, TimeUnit.SECONDS);
		
		//23
		TimeUnit.SECONDS.sleep(3);
		
		//24
		task = new Task();
		System.out.printf("Main: %s\n", new Date());
		
		//25
		executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
		
		//26
		TimeUnit.SECONDS.sleep(10);
		
		//27
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		//28
		System.out.printf("Main: End of the program.\n");
	}

}
