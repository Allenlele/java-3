package com.wds.java.concurrency.chapter4.eighth;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 2、实现Main类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//3、使用Executors类的newCachedThreadPool()方法创建ThreadPoolExecutor对象
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
		//4、创建Task对象
		Task task = new Task();
		//5、使用submit()方法将任务发送给执行者
		System.out.printf("Main: Executin the Task \n");
		Future<String> result = executor.submit(task);
		//6、将主线程睡眠2秒
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//7、使用由submit()方法返回的Future对象result的cancel()方法取消任务执行,
		//方法参数为true
		System.out.printf("Main: Cancelling the Task\n");
		result.cancel(true);
		//8、控制台输出isCancelled()方法的结果，isDone()检验任务是否已经被取消
		System.out.printf("Main: Canceled: %s\n",result.isCancelled());
		System.out.printf("Main: Done: %s\n",result.isDone());
		//9、调用shutDown()方法关闭执行者，并在控制台输出信息表示程序结束
		executor.shutdown();
		System.out.printf("Main: The executor has finished\n");
	}

}
