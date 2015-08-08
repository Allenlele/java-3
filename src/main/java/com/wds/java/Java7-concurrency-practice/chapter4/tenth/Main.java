package com.wds.java.concurrency.chapter4.tenth;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 18、实现主类Main及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//19、使用Executors类的newCachedThreadPool()方法创建ThreadPoolExecutor
		ExecutorService executor=(ExecutorService)Executors.newCachedThreadPool();
		//20、使用刚才已经创建的executor做为构造参数创建CompletionService
		CompletionService<String> service = new ExecutorCompletionService<>(executor);
		//21、创建两个ReportRequest对象及线程，并运行
		ReportRequest faceRequest = new ReportRequest("Face", service);
		ReportRequest onlineRequest = new ReportRequest("Online", service);
		Thread faceThread = new Thread(faceRequest);
		Thread onlineThread = new Thread(onlineRequest);
		//22、创建ReportProcessor对象及线程并运行
		ReportProcessor processor = new ReportProcessor(service);
		Thread senderThread = new Thread(processor);
		//23、启动3个线程
		System.out.printf("Main: Starting the Threads\n");
		faceThread.start();
		onlineThread.start();
		senderThread.start();
		//24、等待ReportRequest线程结束
		try {
			System.out.printf("Main: Waiting for the reportgenerators.\n");
			faceThread.join();
			onlineThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//25、使用shutDown()方法结束执行者，使用awaitTermination()方法等待任务结束
		System.out.printf("Main: Shutting down the executor.\n");
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//26、设置其end属性的值，结束ReportSender执行
		processor.setEnd(true);
		System.out.println("Main: Ends");
	}

}
