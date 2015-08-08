package com.wds.java.concurrency.chapter4.ninth;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 5、实现ResultTask类，继承FutureTask类，泛型化参数为String类
 * @author wds
 *
 */
public class ResultTask extends FutureTask<String> {
	//6、定义属性String属性name，保存任务的名称
	private String name;
	//7、实现构造方法，接受一个Callable对象作为参数，调用父类的构造方法并以接受收到的任务名称初始化name属性
	public ResultTask(Callable<String> callable) {
		super(callable);
		this.name = ((ExecutableTask)callable).getName();
	}
	
	//8、重写done()方法，检查isCancelled()方法的值，基于得到的返回值，向控制台输出不同的信息
	@Override
	protected void done() {
		if (isCancelled()) {
			System.out.printf("%s: Has been canceled\n", name);
		} else {
			System.out.printf("%s: Has finished\n", name);
		}
	}

}
