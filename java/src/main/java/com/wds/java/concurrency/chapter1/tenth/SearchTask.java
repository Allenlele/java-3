package com.wds.java.concurrency.chapter1.tenth;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 1、首先创建SearchTask类，并实现Runnable接口
 * @author wds
 *
 */
public class SearchTask implements Runnable {

	//3、声明Result类型的变量，并在构造方法中初始化它
	private Result reslut;
	public SearchTask(Result r) {
		this.reslut = r;
	}
	
	/**
	 * 4、实现run()方法，调用doTask()，并等待其执行结束或者中断异常
	 * 输出线程的启动、结束及中断信息
	 */
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.printf("Thread %s: Start\n", name);
		try {
			doTask();
			this.reslut.setName(name);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 5、实现doTask()方法，创建Random对象，产生随机数，并使线程睡眠，睡眠时间为随机数
	 */
	private void doTask() throws InterruptedException{
		Random r = new Random(new Date().getTime());
		int value = (int)(r.nextDouble() * 100);
		System.out.printf("Thread %s: %d\n", Thread.currentThread().getName(), value);
		TimeUnit.SECONDS.sleep(value);
	}

}
