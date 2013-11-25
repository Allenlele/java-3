package com.wds.java.concurrency.chapter2.first;

/**
 * 1、创建Account类，模拟我们的银行帐户，只有一个名为balance的属性
 * @author wds
 *
 */
public class Account {
	
	private double balance;

	/*
	 * 2、为balance生成get和set方法，以读写balance的值
	 */
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/*
	 * 3、实现addAmount()方法，将balance的值增加amount，
	 * 因只有一个线程可以改变balance的值，所以使用synchronized关键字将方法转换成临界区
	 */
	public synchronized void addAmount(double amount){
		double tmp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp += amount;
		balance = tmp;
	}
	
	/*
	 * 4、实现subtractAmount()方法，将balance的值减去amount，
	 * 因只有一个线程访问，需要用synchronized关键字将其转成临界区
	 */
	public synchronized void subtractAmount(double amount){
		double tmp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp -= amount;
		balance = tmp;
	}
}
