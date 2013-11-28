package com.wds.java.concurrency.chapter5.second;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 29、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//30、创建Document对象，100行，第行1000个单词
		//书中是DocumentMock，但创建的类为Document，所以使用Document
		Document mock = new Document();
		String[][] doc = mock.generateDocument(100, 1000, "the");
		
		//31、创建DocumentTask对象，更新文档中的产品，start为0，end为100
		DocumentTask task = new DocumentTask(doc, 0, 100, "the");
		
		//32、创建ForkJoinPool对象，使用无参的构造方法，并执行任务
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		
		//33、实现展示信息的代码块，在执行结束之前，每隔1秒输出线程池的相关信息
		do {
			System.out.printf("******************\n");
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
			System.out.printf("******************\n");
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while(!task.isDone());
		
		//34、关闭线程池
		pool.shutdown();
		
		//35、等待任务执行结束
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//36、输出与字符相同的数量，并与Document中的计算器相比，是否相同
		try {
			System.out.printf("Main: The word appears %d in the document", task.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
