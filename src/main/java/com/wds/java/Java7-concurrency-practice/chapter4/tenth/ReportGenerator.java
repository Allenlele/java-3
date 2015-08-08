package com.wds.java.concurrency.chapter4.tenth;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 1、创建ReportGenerator类，实现Callable接口，泛型化参数为String类
 * @author wds
 *
 */
public class ReportGenerator implements Callable<String> {
	//2、定义两个属性String属性sender和title
	private String sender;
	private String title;
	//3、实现构造方法，并初始化这两个属性
	public ReportGenerator(String sender, String title) {
		this.sender = sender;
		this.title = title;
	}
	
	//4、实现call()方法，首先使线程休眠随机时间
	@Override
	public String call() throws Exception {
		try {
			Long duration = (long) (Math.random() * 10);
			System.out.printf("%s_%s: ReportGenerator: Generating areport during %d seconds\n",this.sender, this.title, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//5、之后，用sender和title产生报告，并返回
		String ret = sender + ": " + title;
		return ret;
	}

}
