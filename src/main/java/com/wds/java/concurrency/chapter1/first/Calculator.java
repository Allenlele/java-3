package com.wds.java.concurrency.chapter1.first;

/**
 * 1、创建名为Calculator的类，并实现Runnable接口
 * @author wds
 *
 */
public class Calculator implements Runnable {

	/**
	 * 2、定义一个private int的变量number，并在构造方法中初始化
	 */
	private int number;
	
	/**
	 * 构造方法
	 * @param number
	 */
	public Calculator(int number) {
		this.number = number;
	}

	/**
	 * 3、该方法将执行创建我们在线程中创建的指令（或代码），因此该方法是计算number的乘法
	 */
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++){
			System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, i * number);
		}
	}

}
