package com.wds.java.concurrency.chapter1.seventh;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 9、实现Main类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//10、创建使用Deque类创建queue对象以存放事件
		Deque<Event> deque = new ArrayDeque<>();
		
		//11、创建并启动3个WriterTask线程和一个CleanerTask
		WriterTask writer = new WriterTask(deque);
		for(int i = 0; i < 3; i++){
			Thread t = new Thread(writer);
			t.start();
		}
		
		CleanerTask cleaner = new CleanerTask(deque);
		cleaner.start();
	}

}
