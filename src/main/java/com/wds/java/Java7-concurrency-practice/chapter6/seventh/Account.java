package com.wds.java.concurrency.chapter6.seventh;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 1
 * @author wds
 *
 */
public class Account {
	
	//2
	private AtomicLong balance;
	
	//3
	public Account() {
		balance = new AtomicLong();
	}

	//4
	public AtomicLong getBalance() {
		return balance;
	}

	//5
	public void setBalance(long balance) {
		this.balance.set(balance);
	}
	
	//6
	public void addAmount(long amount){
		this.balance.addAndGet(amount);
	}
	
	//7
	public void subtractAmount(long amount){
		this.balance.getAndAdd(-amount);
	}
	
	
	
}
