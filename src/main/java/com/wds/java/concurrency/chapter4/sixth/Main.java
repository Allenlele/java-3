package com.wds.java.concurrency.chapter4.sixth;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 5、实现主类Main及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//6、使用Executors类的 newScheduledThreadPool()方法创建ScheduledThreadPoolExecutor类，参数为1
		ScheduledExecutorService executor = (ScheduledExecutorService)Executors.newScheduledThreadPool(1);
		//7、初始化并使用ScheduledThreadPoolExecutor类的schedule()方法启动少量任务（这个示例中5个任务）
		System.out.printf("Main: Starting at: %s\n", new Date());
		
		
//		try {
//			TimeUnit.SECONDS.sleep(3);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		for (int i = 0; i < 5; i++) {
			Task task = new Task("Task " + i);
			executor.schedule(task, i + 1, TimeUnit.SECONDS);
		}
		//8、使用shutDown()方法请求释放执行者
		executor.shutdown();
		//9、使用awaitTermination()方法，等等任务完成
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//10、程序完成时输出信息
		System.out.printf("Main: Ends at: %s\n",new Date());
	}

}
