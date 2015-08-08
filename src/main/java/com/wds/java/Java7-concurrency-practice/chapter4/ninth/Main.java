package com.wds.java.concurrency.chapter4.ninth;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 9、实现主类Main及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//10、使用Executors类的newCachedThreadPool()方法创建ExecutorService
		ExecutorService executor = (ExecutorService)Executors.newCachedThreadPool();
		//11、创建一个数组保存ResultTask对象
		ResultTask resultTasks[] = new ResultTask[5];
		//12、初始化ResultTask对象，数组中的每一个下标，首先，创建ExecutableTask和使用这个对象创建ResultTask
		//之后，将其提交给执行者
		for(int i = 0; i < 5; i++){
			ExecutableTask executableTask = new ExecutableTask("Task-" + i);
			resultTasks[i] = new ResultTask(executableTask);
			executor.submit(resultTasks[i]);
		}
		//13、主线程休眠5秒钟
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//14、取消已经提交给执行者的任务
		for(int i = 0; i < resultTasks.length; i++){
			resultTasks[i].cancel(true);
		}
		//15、控制台输出使用ResultTask对象的get()方法获取未被取消任务
		for(int i = 0; i < resultTasks.length; i++){
			try {
				if(!resultTasks[i].isCancelled()){
					System.out.printf("re%s\n",resultTasks[i].get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
	}

}
