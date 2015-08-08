package com.wds.java.concurrency.chapter2.third;

/**
 * 8、创建Consumer类，并实现Runnable接口，它是消费者
 * @author wds
 *
 */
public class Consumer implements Runnable {
	
	/*
	 * 10、定义EventStorage对象，并在构造方法中初始化
	 */
	public EventStorage storage;
	
	public Consumer(EventStorage storage) {
		this.storage = storage;
	}
	
	
	/*
	 * 11、实现run()方法，调用100次get() 
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.get();
		}
	}

}
