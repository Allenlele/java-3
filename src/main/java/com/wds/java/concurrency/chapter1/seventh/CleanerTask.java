package com.wds.java.concurrency.chapter1.seventh;

import java.util.Date;
import java.util.Deque;

/**
 * 5、创建CleanerTask，继承Thread类
 * @author wds
 *
 */
public class CleanerTask extends Thread {
	
	/**
	 * 6、声明deque，存放事件
	 * 实现构造方法并初始化deque，并将线程设置为守护线程
	 */
	private Deque<Event> deque;
	public CleanerTask(Deque<Event> deque) {
		this.deque = deque;
		setDaemon(true);
	}
	
	/**
	 * 7、实现run()方法，无限循环，获取当前日期，并调用clean()方法
	 */
	@Override
	public void run() {
		while(true){
			Date date = new Date();
			clean(date);
		}
	}

	/**
	 * 8、实现clean()方法，获取最后一个事件，如果事件于10秒前创建，则删除并检查下一个事件，
	 * 当删除一下事件时，输出事件信息及deque大小，以便观察其变化
	 */
	private void clean(Date date) {
		long difference;
		boolean delete;
		
		if(deque.size() == 0){
			return;
		}
		
		delete = false;
		
		do {
			Event e = deque.getLast();
			difference = date.getTime() - e.getDate().getTime();
			if(difference > 10000){
				System.out.printf("Cleaner: %s\n", e.getEvent());
				deque.removeLast();
				delete = true;
			}
		} while (difference > 10000);
		
		if(delete){
			System.out.printf("Cleaner: Size of the deque %d\n", deque.size());
		}
	}
}
