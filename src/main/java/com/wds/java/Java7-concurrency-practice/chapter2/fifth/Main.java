package com.wds.java.concurrency.chapter2.fifth;

/**
 * 14、创建主类并实现main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//15、创建PricesInfo对象
		PricesInfo pricesInfo = new PricesInfo();
		
		//16、创建5个Reader和线程，并运行
		Reader[] readers = new Reader[5];
		Thread[] tr = new Thread[5];
		for (int i = 0; i < 5; i++) {
			readers[i]= new Reader(pricesInfo);
			tr[i] = new Thread(readers[i]);
		}
		
		//17、创建Writer对象和线程并运行
		Writer writer = new Writer(pricesInfo);
		Thread tw = new Thread(writer);
		
		//18、启动线程
		for (int i = 0; i < 5; i++) {
			tr[i].start();
		}
		tw.start();
	}

}
