package com.wds.java.concurrency.chapter8.second;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 1、创建Task类，实现Runnable接口
 * @author wds
 *
 */
public class Task implements Runnable {
	
	//2、声明int类型的私有变量time
	private int time;
	
	//3、声明Phaser类型的属性phaser
	private Phaser phaser;
	
	//4、实现构造方法并初始化属性
	public Task(int time, Phaser phaser) {
		this.time = time;
		this.phaser = phaser;
	}
	
	//5、实现run()方法，使用phaser属性的arrive()方法开始执行
	@Override
	public void run() {
		phaser.arrive();
		
		//6、控制台输出 信息说明开始第一阶段，使用线程休眠由time属性指定的时间，第一阶段结束后输出信息，
		//使用phaser属性的arriveAndAwaitAdvance()方法同步余下的任务。
		System.out.printf("%s: Entering phase 1.\n", Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Finishing phase 1.\n", Thread.currentThread().getName());
		phaser.arriveAndAwaitAdvance();
		
		//7、第二、三阶段重复上述工作，在第三阶段结束时，使用arriveAndDeregister()方法，而不是arriveAndAwaitAdvane()方法
		System.out.printf("%s: Entering phase 2.\n", Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Finishing phase 2.\n", Thread.currentThread().getName());
		phaser.arriveAndAwaitAdvance();
		
		System.out.printf("%s: Entering phase 3.\n", Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Finishing phase 3.\n", Thread.currentThread().getName());
		phaser.arriveAndDeregister();
		
	}


}
