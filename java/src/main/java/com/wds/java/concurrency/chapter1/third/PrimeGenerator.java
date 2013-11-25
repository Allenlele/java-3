package com.wds.java.concurrency.chapter1.third;

/**
 * 1、创建名为PrimeGenerator的类，继承Thread
 * @author wds
 *
 */
public class PrimeGenerator extends Thread {

	/**
	 * 2、重写run方法，方法中有一个无限循环。在循环体中，我们计算从1开始的连继的数字，
	 * 对于每个数字，如果是素数时，我们将进行计算，在此种情况下，将其打印到控制台上
	 */
	@Override
	public void run() {
		long number = 1L;
		while(true){
			if(isPrime(number)){
				System.out.printf("Number %d is Prime\n", number);
			}
			
			/*
			 * 3、判断完一个数字之后，检查当前线程是否由于调用isInterrupted()方
			 * 法而被中断，如果该方法返回true，则打印信息并终止线程执行
			 */
			if(isInterrupted()){
				System.out.println("The Prime Generator has been interrupted.");
				return;
			}
			number++;
		}
	}

	/**
	 * 4、实现isPrime方法，其返回值的类型为boolean
	 * 如果number为素数返回true，否则为false
	 */
	private boolean isPrime(long number) {
		if(number <= 2){
			return true;
		}
		for(long i = 2; i < number; i++){
			if( (number % i) == 0){
				return false;
			}
		}
		return true;
	}
}
