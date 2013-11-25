package com.wds.java.concurrency.chapter2.fifth;

/**
 * 11、创建Writer类，并实现Runnable接口，实现修改PricesInfo属性功能
 * @author wds
 *
 */
public class Writer implements Runnable {
	
	/*
	 * 12、声明PricesInfo对象并在构造方法中初始化
	 */
	private PricesInfo pricesInfo;

	public Writer(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	/*
	 * 13、实现run()方法，修改3次PricesInfo的值，每次修改之后，睡眠2秒钟
	 */
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.printf("Writer: Attempt to modify the prices.\n");
			pricesInfo.setPrices(Math.random()*10, Math.random()*8);
			System.out.printf("Writer: Prices have been modified.\n");
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
