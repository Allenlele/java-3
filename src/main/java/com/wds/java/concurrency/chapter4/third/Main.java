package com.wds.java.concurrency.chapter4.third;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 9、实现主类及main()方法
 * @author wds
 *
 */
public class Main {
	
	public static void main(String[] args) {
		//10、使用Executors的newFixedThreadPool()方法创建ThreadPoolExecutor运行任务，参数为2
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
		
		//11、创建Future<Integer>列表对象
		List<Future<Integer>> resultList = new ArrayList<>();
		
		//12、使用Random创建随机数
		Random random = new Random();
		
		//13、创建 1到10之间的随机数
		for(int i = 0; i < 10; i++){
			Integer number = random.nextInt(10);
			//14、创建FactorialCaculator对象，生成的随机数作为参数
			FactorialCalculator calculator = new FactorialCalculator(number);
			//15、调用执行斱submit()方法给执行者发送FactorialCaculator任务，该方法
			//返回一个Future<Integer>对象管理结果，最终获取结果
			Future<Integer> result = executor.submit(calculator);
			//16、将Future对象添加到创建的列表中
			resultList.add(result);
		}
		
		//17、创建一个do循环监控执行得状态
		do{
			//18、首先，调用执行者的getCompletedTaskNumber()方法在控制上输出完成任务数
			System.out.printf("Main: Number of Complete Tasks %d\n", executor.getCompletedTaskCount());
			//19、针对列表中的10个Future对象，使用isDone()方法输出信息展示任务是否完成
			for(int i = 0; i < resultList.size(); i++){
				Future<Integer> result = resultList.get(i);
				System.out.printf("Main: Task %d %s\n", i, result.isDone());
				//20、将线程休眠50毫秒
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		//21、当完成的任务数小于10时继续循环
		}while(executor.getCompletedTaskCount() < resultList.size());
		//22、控制台输出每个任务的结果，针对第一个Future对象，使用get()方法获取任务返回的Integer对象
		System.out.printf("Main: Results\n");
		for(int i = 0; i < resultList.size(); i++){
			Future<Integer> result = resultList.get(i);
			Integer number = null;
			try {
				number = result.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			//23、控制台输出结果
			System.out.printf("Main: Task %d: %d\n", i, number);
		}
		//23、最后，调用shutDown()释放资源
		executor.shutdown();
	}

}
