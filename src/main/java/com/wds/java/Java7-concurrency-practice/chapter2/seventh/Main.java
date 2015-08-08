package com.wds.java.concurrency.chapter2.seventh;

/*
 * 20、实现主类及main()方法
 */
public class Main {

	public static void main(String[] args) {
		//21、创建FileMock对象
		FileMock mock = new FileMock(100, 10);
		//22、创建Buffer对象
		Buffer buffer = new Buffer(20);
		//23、创建Producer对象及线程，并运行
		Producer producer = new Producer(mock, buffer);
		Thread threadProducer = new Thread(producer, "Producer");
		//24、创建3个消费者及线程并运行
		Consumer consumers[] = new Consumer[3];
		Thread threadConsumers[] = new Thread[3];
		for(int i = 0; i < 3; i++){
			consumers[i] = new Consumer(buffer);
			threadConsumers[i] = new Thread(consumers[i], "Consumer-" + i);
		}
		
		//25、启动生产者和消费者
		threadProducer.start();
		for(int i = 0; i < 3; i++){
			threadConsumers[i].start();
		}
	}

}
