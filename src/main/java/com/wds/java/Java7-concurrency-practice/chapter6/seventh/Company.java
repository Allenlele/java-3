package com.wds.java.concurrency.chapter6.seventh;

/**
 * 8
 * @author wds
 *
 */
public class Company implements Runnable {
	
	//9
	private Account account;
		
	//10
	public Company(Account account) {
		this.account = account;
	}

	//11
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			account.addAmount(1000);
		}
	}

}
