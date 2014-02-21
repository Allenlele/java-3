package com.wds.java.concurrency.chapter4.fifth;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 4、创建Task类，实现Callable接口，泛型参数为Result
 * @author wds
 *
 */
public class Task implements Callable<Result> {
	//5、定义私有的String类型属性name
	private String name;
	//6、实现构造方法，并初始化属性
	public Task(String name) {
		this.name = name;
	}
	//7、实现call()方法，该方法返回Result对象
	@Override
	public Result call() throws Exception {
		//8、首先，控制台输出信息表明任务已经开发
		System.out.printf("%s: Staring\n", this.name);
		//9、之后等待随机时间
		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Waiting %d seconds for results.\n",this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//10、生成一个int值，计算数5个数的和，并保存到Result中
		int value = 0;
		for(int i = 0; i < 5; i++){
			value += (int)(Math.random() * 100);
		}
		//11、创建一个Result对象，使用任务的名称及刚才数的和初始化该对象
		Result result = new Result();
		result.setName(name);
		result.setValue(value);
		//12、控制台输入信息表明任务已经完成
		System.out.println(this.name+": Ends");
		//13、返回Result对象
		return result;
	}

}
