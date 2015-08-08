package com.wds.java.concurrency.chapter1.evelenth;

import java.util.Random;

/**
 * 3、创建Task类，并实现Runnable接口
 * @author wds
 *
 */
public class Task implements Runnable {

	/**
	 * 4、实现run()方法，抛出AritmethicException异常，所以用1000除以随机数，
	 * 直到产生的随机数为零，然后抛出异常
	 */
	@Override
	public void run() {
		int result;
		Random random = new Random(Thread.currentThread().getId());
		while(true){
			result = 1000/((int)random.nextDouble() * 1000);
			System.out.printf("%s : %f\n", Thread.currentThread().getId(), result);
			if(Thread.currentThread().isInterrupted()){
				System.out.printf("%d : Interrupted\n", Thread.currentThread().getId());
				return;
			}
		}
	}

}
