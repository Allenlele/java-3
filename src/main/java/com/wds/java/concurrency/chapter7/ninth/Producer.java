package com.wds.java.concurrency.chapter7.ninth;

/**
 * 22
 * @author wds
 *
 */
public class Producer implements Runnable {
	
	//23
	private MyPriorityTransferQueue<Event> buffer;
	
	//24
	public Producer(MyPriorityTransferQueue<Event> buffer){
		this.buffer = buffer;
	}

	//25
	@Override
	public void run() {
		for(int i = 0; i < 100; i++){
			Event event = new Event(Thread.currentThread().getName(), 1);
			buffer.put(event);
		}
	}

}
