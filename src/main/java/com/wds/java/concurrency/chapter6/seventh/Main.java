package com.wds.java.concurrency.chapter6.seventh;


/**
 * 16
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//17
		Account account = new Account();
		account.setBalance(1000);
		
		//18
		Company compnay = new Company(account);
		Thread companyThread = new Thread(compnay);
		
		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank);
		
		//19
		//源码System.out.printf("Account : Initial Balance: %d\n", account.getBalance());
		System.out.printf("Account : Initial Balance: %d\n", account.getBalance().longValue());
		
		//20
		companyThread.start();
		bankThread.start();
		
		//21
		try {
			companyThread.join();
			bankThread.join();
			
			//源码System.out.printf("Account: Pinal Balance: %d\n", account.getBalance());
			System.out.printf("Account: Pinal Balance: %d\n", account.getBalance().longValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
