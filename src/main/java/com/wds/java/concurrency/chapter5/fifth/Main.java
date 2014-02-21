package com.wds.java.concurrency.chapter5.fifth;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 27、实现主类Main和main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//28、使用ArrayGenerator类创建有1000个大小的数组
		ArrayGenerator generator = new ArrayGenerator();
		int array[] = generator.generateArray(1000);
		
		//29、创建一个TaskManager对象
		TaskManager manager = new TaskManager();
		
		//30、使用默认构造方法创建一个ForkJoinPool对象
		ForkJoinPool pool = new ForkJoinPool();
		
		//31、创建一个任务对象处理之前创建的数组
		//源代码为Task，更正为SearchNumberTask
		SearchNumberTask task = new SearchNumberTask(array, 0, 1000, 5, manager);
		
		//32、使用execute()方法异步执行池中的任务
		pool.execute(task);
		
		//33、使用shutDown()方法关闭池
		pool.shutdown();
		
		//34、使用ForkJoinPool类的awaitTermination()方法等待任务结束
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//35、控制台输出信息表示程序结束
		System.out.printf("Main: The program has finished\n");
		
	}

}
