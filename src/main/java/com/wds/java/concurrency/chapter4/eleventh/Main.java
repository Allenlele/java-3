package com.wds.java.concurrency.chapter4.eleventh;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 9、实现主类Main及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//10、创建RejectedTaskController对象管理被拒绝的任务
		RejectedTaskController controller = new RejectedTaskController();
		//11、使用Executors类的newCachedThreadPool()方法创建ThreadPoolExecutor
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
		//12、建议执行者的拒绝任务控制器
		executor.setRejectedExecutionHandler(controller);
		//13、创建3个任务并提交给执行者
		System.out.printf("Main: Starting.\n");
		for (int i = 0; i < 3; i++) {
			Task task = new Task("Task" + i);
			executor.submit(task);
		}
		//14、使用shutDown()方法关闭执行者
		System.out.printf("Main: Shutting down the Executor.\n");
		executor.shutdown();
		//15、创建另一个任务并提交给执行者
		System.out.printf("Main: Sending another Task.\n");
		Task task = new Task("RejectedTask");
		executor.submit(task);
		//16、控制台输入信息表示程序运行结束
		System.out.println("Main: End");
		System.out.printf("Main: End.\n");
	}

}
