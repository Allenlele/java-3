package com.wds.java.concurrency.chapter4.tenth;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/**
 * 11、创建ReportProcessor类，实现Runnable接口
 * @author wds
 *
 */
public class ReportProcessor implements Runnable {
	//12、定义私有CompletionService属性service，泛型化参数为String类
	private CompletionService<String> service;
	//13、定义私有boolean属性end
	private boolean end;
	//14、实现构造方法，并初始化这两个属性
	public ReportProcessor(CompletionService<String> service) {
		this.service = service;
		this.end = false;
	}
	
	//15、实现run()方法，当属性end为false时，调用CompletionService接口的poll()方法获得Future对象
	@Override
	public void run() {
		while (!end){
			try {
				Future<String> result = service.poll(20, TimeUnit.SECONDS);
				//16、之后，使用Future对象的get()方法获取任务的结果，将这些结果输出到控制台上
				if (result != null) {
					String report = result.get();
					System.out.printf("ReportReceiver: Report Received:%s\n",report);
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("ReportSender: End\n");
	}
	
	//17、实现setEnd()方法，修改end属性的值
	public void setEnd(boolean end){
		this.end = end;
	}

}
