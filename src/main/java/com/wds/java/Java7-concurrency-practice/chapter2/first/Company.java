package com.wds.java.concurrency.chapter2.first;

/**
 * 8、实现Company类，模拟公司，调用addAmount()方法，增加balance的值
 * 实现Runnable接口
 * @author wds
 *
 */
public class Company implements Runnable {

	/*
	 * 9、声明Account类，并在构造方法中初始化
	 */
	private Account account;
	
	public Company(Account account) {
		this.account = account;
	}

	/*
	 * 10、调用addAmount()方法100次，增加balance的值 
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.addAmount(1000);
		}
	}

}
