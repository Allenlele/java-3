package com.wds.java.concurrency.chapter4.seventh;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 5、实现主类Main及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//6、使用Executors类的newScheduledThreadPool()方法创建ScheduledThreadPoolExecutor类，参数为1
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		//7、控制台输出当前日期
		System.out.printf("Main: Starting %s\n", new Date());
		//8、创建Task对象
		Task task = new Task("Task");
		//9、使用scheduleAtFixRate()方法将任务提交给执行者。参数为task,1,2及常量TimeUnit.SECONDS，
		//该方法返回一个ScheduleFuture对象，通过该对象可控制任务状态
		ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
		//10、创建10次的循环，输出任务下次执行的午剩余时间，使用ScheduleFuture对象的getDelay()方法获取到任务下一次执行的毫秒数
		for(int i = 0; i < 10; i++){
			System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));
			//线程睡眠500毫秒
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//11、使用shutDown()方法关闭执行者
		executor.shutdown();
		//12、线程睡眠5秒，检查周期性任务是否已经完成
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//13、输出信息表明程序已经结束
		System.out.printf("Main: Finished at %s\n", new Date());
	}

}
