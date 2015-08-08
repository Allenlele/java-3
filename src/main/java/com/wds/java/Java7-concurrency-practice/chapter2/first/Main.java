package com.wds.java.concurrency.chapter2.first;

/**
 * 11、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//12、创建Account对象，并将balance的值初始化为1000
		Account account = new Account();
		account.setBalance(1000);
		
		//13、创建Company对象及线程，运行
		Company company = new Company(account);
		Thread companyThread = new Thread(company);
		
		//14、创建Bank对象和线程并运行
		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank);
		
		//15、控制台输出balance初始值
		System.out.printf("Account : Initial Balance: %f\n", account.getBalance());
		companyThread.start();
		bankThread.start();
		
		//16、调用线程的join()方法，等待线程执行结束，并输出balance的值
		try {
			companyThread.join();
			bankThread.join();
			System.out.printf("Account : Final Balance: %f\n", account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
