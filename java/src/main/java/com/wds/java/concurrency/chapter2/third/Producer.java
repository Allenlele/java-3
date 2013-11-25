package com.wds.java.concurrency.chapter2.third;

/**
 * 5、创建Producer类，并实现Runnable接口，是生产者
 * @author wds
 *
 */
public class Producer implements Runnable {
	
	/*
	 * 6、定义EventStorage对象，并在构造方法中初始化
	 */
	private EventStorage storage;
	
	public Producer(EventStorage storage) {
		this.storage = storage;
	}

	/*
	 * 7、实现run()方法，调用100次set方法，存储EventStorage对象
	 */
	@Override
	public void run() {
		for(int i = 0; i < 100; i++){
			storage.set();
		}
	}

}
