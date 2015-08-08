package com.wds.java.concurrency.chapter2.third;

/**
 * 11、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//12、创建EventStorage对象
		EventStorage storage = new EventStorage();
		
		//13、创建生产者及线程，并运行
		Producer p = new Producer(storage);
		Thread tp = new Thread(p);
		
		//14、创建消费者及线程，并运行
		Consumer c = new Consumer(storage);
		Thread tc = new Thread(c);
		
		//15、启动它们
		tp.start();
		tc.start();
	}

}
