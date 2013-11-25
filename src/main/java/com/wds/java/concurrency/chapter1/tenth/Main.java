package com.wds.java.concurrency.chapter1.tenth;

import java.util.concurrent.TimeUnit;

/**
 * 6、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//7、创建线程组
		ThreadGroup threadGroup = new ThreadGroup("Searcher");
		
		//8、创建SearchTask对象及Result对象
		Result result = new Result();
		SearchTask searchTask = new SearchTask(result);
		
		//9、使用SearchTask创建10个线程对象，使用Thread对象构造线程
		for(int i = 0; i < 5; i++){
			Thread thread = new Thread(threadGroup, searchTask);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//10、list()方法输出线程组信息
		System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
		System.out.printf("Information about the Thread Group\n");
		threadGroup.list();
		
		//11、调用activeCount()和enumerate()方法了解有多少个线程对象与线程组关联，可获取它们列表
		//代码如下：
		Thread[] threads = new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);
		for(int i = 0; i< threadGroup.activeCount(); i++){
			System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
		}
		
		//12、调用waitFinish，稍后将会实现，等待线程组中的一个线程结束
		waitFinish(threadGroup);
		
		//13、中断线程组中余下的线程
		threadGroup.interrupt();
	}

	/**
	 * 14、实现waitFinish()方法，调用activeCount()方法控制其中一个线程结束
	 */
	private static void waitFinish(ThreadGroup threadGroup) {
		while(threadGroup.activeCount() > 9){
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
