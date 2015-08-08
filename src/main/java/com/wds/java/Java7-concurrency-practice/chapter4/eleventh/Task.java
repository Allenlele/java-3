package com.wds.java.concurrency.chapter4.eleventh;

import java.util.concurrent.TimeUnit;

/**
 * 2、定义Task类，实现Runnable接口
 * @author wds
 *
 */
public class Task implements Runnable {
	//3、定义私有String属性name，保存任务名称
	private String name;
	//4、实现构造方法，初始化属性
	public Task(String name) {
		this.name = name;
	}
	
	//5、实现run()方法，控制台输出信息表示方法已经启动
	@Override
	public void run() {
		System.out.println("Task " + name + ": Starting");
		//6、等等待随机时间
		try {
			long duration = (long) (Math.random() * 10);
			System.out
					.printf("Task %s: ReportGenerator: Generating areport during %d seconds\n",
							name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//7、控制台输出信息表示方法结束
		System.out.printf("Task %s: Ending\n",name);
	}
	//8、重写toString()方法，返回任务名称
	public String toString(){
		return name;
	}

}
