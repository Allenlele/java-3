package com.wds.java.concurrency.chapter8.third;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 5、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{
		//6、调用Executors的newCachedThreadPool()方法创建一个新的Executor对象
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		
		//7、创建10个任务，并提交到Executor，使用随机数初始化Task对象
		Random random = new Random();
		for(int i = 0; i < 10; i++){
			Task task = new Task(random.nextInt(10000));
			executor.submit(task);
		}
		
		//8、创建5个循环，每一次迭代调用showLog()方法，并使线程睡眠1秒
		for(int i = 0; i < 5; i++){
			showLog(executor);
			TimeUnit.SECONDS.sleep(1);
		}
		
		//9、关键Executor
		executor.shutdown();
		
		//10、创建另外一个5次循环，每次调用showLog()方法输出信息，并使线程睡眠1秒
		for(int i = 0; i < 5; i++){
			showLog(executor);
			TimeUnit.SECONDS.sleep(1);
		}
		
		//11、调用awaitTermination()方法等Executor执行结束
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		//12、显示程序执行结束的信息
		System.out.printf("Main: End of the program.\n");
	}

	/*
	 * 13、实现showLog()方法，接受一个Executor作为参数，输出线程池的大小、任务数及Executor的状态信息
	 */
	private static void showLog(ThreadPoolExecutor executor) {
		System.out.printf("*********************");
		System.out.printf("Main: Executor Log\n");
		System.out.printf("Main: Executor: Core Pool Size: %d\n",executor.getCorePoolSize());
		System.out.printf("Main: Executor: Pool Size: %d\n",executor.getPoolSize());
		System.out.printf("Main: Executor: Active Count: %d\n",executor.getActiveCount());
		System.out.printf("Main: Executor: Task Count: %d\n",executor.getTaskCount());
		System.out.printf("Main: Executor: Completed Task Count:%d\n",executor.getCompletedTaskCount());
		System.out.printf("Main: Executor: Shutdown: %s\n",executor.isShutdown());
		System.out.printf("Main: Executor: Terminating:%s\n",executor.isTerminating());
		System.out.printf("Main: Executor: Terminated: %s\n",executor.isTerminated());
		System.out.printf("*********************\n");
	}

}
