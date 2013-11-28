package com.wds.java.concurrency.chapter5.third;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 17、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//18、使用默认构造方法创建ForkJoinPool对象
		ForkJoinPool pool = new ForkJoinPool();
		
		//19、创建FolderProcessor任务，使用不同的文件路径初始它们
		FolderProcessor system = new FolderProcessor("C:\\Windows", "log");
		FolderProcessor apps = new FolderProcessor("C:/Program Files", "log");
		FolderProcessor documents = new FolderProcessor("C:/Documents And Settings", "log");
		
		//20、调用execute()方法执行
		pool.execute(system);
		pool.execute(apps);
		pool.execute(documents);
		
		//21、每隔一秒，向控制台输出fork/join池的状态信息，直到执行结束
		do{
			System.out.printf("*****************\n");
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
			System.out.printf("*****************\n");
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while((!system.isDone()) || (!apps.isDone()) || (!documents.isDone()));
		
		
		//22、调用shutdown()方法关闭
		pool.shutdown();
		
		//23、控制台输出每个任务执行的结果
		List<String> results;
		results = system.join();
		System.out.printf("System: %d files found.\n", results.size());
		
		results = apps.join();
		System.out.printf("System: %d files found.\n", results.size());
		
		results = documents.join();
		System.out.printf("System: %d files found.\n", results.size());
	}

}
