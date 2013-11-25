package com.wds.java.concurrency.chapter2.fifth;

/**
 * 8、创建Reader类，并实现Runnable接口，实现读取PricesInfo信息功能
 * @author wds
 *
 */
public class Reader implements Runnable {
	
	/*
	 * 9、声明PricesInfo对象，并在构造方法初始化
	 */
	private PricesInfo pricesInfo;
	
	public Reader(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	/*
	 * 10、实现run()方法，读取10次price1和price2的值
	 */
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice1());
			System.out.printf("%s: Price 2: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice2());
		}
	}

}
