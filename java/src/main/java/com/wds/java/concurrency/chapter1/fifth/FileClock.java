package com.wds.java.concurrency.chapter1.fifth;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 1、创建FileClock类，实现Runnable接口
 * @author wds
 *
 */
public class FileClock implements Runnable{

	/**
	 * 2、实现run()方法
	 */
	@Override
	public void run() {
		
		/*
		 * 3、编写一个10次迭代的循环，在每一次迭代中，创建Date对象，将其输出到控制台上，并调用
		 * TimeUnit的SECONDS属性的sleep()方法使线程挂起1秒，该方法使线程睡眠一秒，由于sleep()
		 * 方法会抛出InterruptedException异常，得将其捕获
		 * 
		 */
		for(int i = 0; i < 10; i++){
			System.out.printf("%s\n", new Date());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("The FileClock has been interrupted");
			}
		}
	}

}
