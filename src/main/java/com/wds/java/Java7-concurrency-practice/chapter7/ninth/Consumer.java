package com.wds.java.concurrency.chapter7.ninth;

/**
 * 26
 * @author wds
 *
 */
public class Consumer implements Runnable {
	
	//27
	private MyPriorityTransferQueue<Event> buffer;
	
	//28
	public Consumer(MyPriorityTransferQueue<Event> buffer){
		this.buffer = buffer;
	}

	//29
	@Override
	public void run() {
		for(int i = 0; i < 1002; i++){
			try {
				Event value = buffer.take();
				System.out.printf("Consumer: %s: %d\n", value.getThread(), value.getPriority());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
