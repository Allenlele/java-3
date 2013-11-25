package com.wds.java.concurrency.chapter1.seventh;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * 2、创建WriterTask类，并实现Runnable接口
 * @author wds
 *
 */
public class WriterTask implements Runnable {
	
	/**
	 * 3、声明deque，存放事件
	 * 实现构造方法并初始化deque
	 */
	private Deque<Event> deque;
	public WriterTask(Deque<Event> deque) {
		this.deque = deque;
	}

	/**
	 * 4、实现run()方法，拥有100次迭代的循环，每一次迭代中，会创建一个新的Event，保存到deque，并睡眠一秒
	 */
	@Override
	public void run() {
		for(int i = 0; i < 100; i++){
			Event event = new Event();
			event.setDate(new Date());
			event.setEvent(String.format("The thread %s has generated as event", Thread.currentThread().getId()));
			deque.addFirst(event);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
