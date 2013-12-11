package com.wds.java.concurrency.chapter7.ninth;

import java.util.concurrent.TimeUnit;

/**
 * 30
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		//31
		MyPriorityTransferQueue<Event> buffer = new MyPriorityTransferQueue<>();
		
		//32
		Producer producer = new Producer(buffer);
		Thread producerThreads[] = new Thread[10];
		for(int i = 0; i < producerThreads.length; i++){
			producerThreads[i] = new Thread(producer);
			producerThreads[i].start();
		}
		
		//33
		Consumer consumer = new Consumer(buffer);
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		
		//34
		System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());
		
		//35
		Event myEvent = new Event("Core Event", 0);
		buffer.transfer(myEvent);
		System.out.printf("Main: My Event has been transfered.\n");
		
		//36
		for(int i = 0; i < producerThreads.length; i++){
			//书中代码有try-catch，因方法中已经throws，所以去掉了
			producerThreads[i].join();
		}
		
		//37
		TimeUnit.SECONDS.sleep(1);
		
		//38
		System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());
		
		//39
		myEvent = new Event("Core Event 2", 0);
		buffer.transfer(myEvent);
		
		//40
		consumerThread.join();
		
		//41
		System.out.printf("Main: End of the program\n");
	}

}
