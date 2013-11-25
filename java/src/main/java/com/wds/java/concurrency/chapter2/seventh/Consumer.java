package com.wds.java.concurrency.chapter2.seventh;

import java.util.Random;

/**
 * 16、下面轮到消费者了，实现Consumer并实现Runnable接口
 * @author wds
 *
 */
public class Consumer implements Runnable {
	
	/*
	 * 17、声明Buffer对象及构造方法，并初始化
	 */
	private Buffer buffer;
	
	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}

	/*
	 * 18、实现run()方法，当buffer中有元素时，将一直处理
	 */
	@Override
	public void run() {
		while(buffer.hasPendingLines()){
			String line = buffer.get();
			processLine(line);
		}
	}

	/*
	 * 19、实现processLine方法，仅睡觉10毫秒，模仿一下处理功能
	 */
	private void processLine(String line) {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
