package com.wds.java.concurrency.chapter5.fourth;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 10、创建主类Main及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//11、创建100个数字的数组
		int array[] = new int[100];
		
		//12、创建Task对象处理数组
		Task task = new Task(array, 0, 100);
		
		//13、使用默认构造方法创建ForkJoinPool对象
		ForkJoinPool pool = new ForkJoinPool();
		
		//14、使用execute()方法执行任务
		pool.execute(task);
		
		//15、使用shutDown()方法关闭ForkJoinPool
		pool.shutdown();
		
		//16、使用awaitTermination()方法等等任务结束。由想等待其结果，无论等待多长时间，传递超时时间为1天
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//17、使用isCompltedAbnormally()方法检查任务或子任务 是否抛出异常。
		//这种情况下，控制台输出招聘的异常信息，使用ForkJoinTask类的getException()方法获取异常
		if(task.isCompletedAbnormally()){
			System.out.printf("Main: An exception has ocurred\n");
			System.out.printf("Main: %s\n", task.getException());
		}
		System.out.printf("Main: Result: 5d", task.join());
	}

}
