package com.wds.java.concurrency.chapter2.first;

/**
 * 5、实现Bank类，模拟ATM机，调用subtractAmount()方法，扣除balance的值，
 * 实现Runnable接口
 * @author wds
 *
 */
public class Bank implements Runnable {

	/*
	 * 6、声明Account对象，并在构造方法中初始化
	 */
	private Account account;
	public Bank(Account account) {
		this.account = account;
	}

	/*
	 * 7、实现run()方法，调用100次subtractAmount()方法 
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.subtractAmount(1000);
		}
	}

}
