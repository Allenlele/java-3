package com.wds.java.concurrency.chapter6.seventh;

/**
 * 12
 * @author wds
 *
 */
public class Bank implements Runnable {
	
	//13
	private Account account;
		
	//14
	public Bank(Account account) {
		this.account = account;
	}

	//15
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			account.subtractAmount(1000);
		}
	}

}
