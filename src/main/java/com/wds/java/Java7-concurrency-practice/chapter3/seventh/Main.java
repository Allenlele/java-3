package com.wds.java.concurrency.chapter3.seventh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 15、实现主类Main及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//16、创建两个缓冲区分别用于生产者、消费者
		List<String> buffer1 = new ArrayList<>();
		List<String> buffer2 = new ArrayList<>();
		//17、创建Exchanger对象，用于同步生产者和消费者
		Exchanger<List<String>> exchanger = new Exchanger<>();
		//18、创建生产者、消费者对象
		Producer producer = new Producer(buffer1, exchanger);
		Consumer consumer = new Consumer(buffer2, exchanger);
		//19、创建线程并执行它们
		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);
		
		threadProducer.start();
		threadConsumer.start();
	}

}
