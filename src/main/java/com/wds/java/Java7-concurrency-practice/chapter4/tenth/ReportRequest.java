package com.wds.java.concurrency.chapter4.tenth;

import java.util.concurrent.CompletionService;

/**
 * 6、创建ReportRequest类,实现Runnable接口，该类模拟报告请求
 * @author wds
 *
 */
public class ReportRequest implements Runnable {
	//7、定义String私有属性name，保存ReportRequest的名称
	private String name;
	//8、定义私有CompletionService属性service，该接口是一个泛型化接口，参数为String类
	private CompletionService<String> service;
	//9、实现构造方法，并初始化这两个属性
	public ReportRequest(String name, CompletionService<String> service) {
		this.name = name;
		this.service = service;
	}
	//10、实现run()方法，创建ReportGenerator对象，使用submit()方法将它们发送给CompletionService对象
	@Override
	public void run() {
		ReportGenerator reportGenerator=new ReportGenerator(name,"Report");
		service.submit(reportGenerator);
	}
		

}
