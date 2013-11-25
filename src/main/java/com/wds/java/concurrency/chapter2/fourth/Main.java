package com.wds.java.concurrency.chapter2.fourth;

/**
 * 10、创建主类并实现main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//11、创建PrintQueue对象
		PrintQueue printQueue = new PrintQueue();
		//12、创建10个Job对象和10个线程
		Thread[] ts = new Thread[10];
		for (int i = 0; i < 10; i++) {
			ts[i] = new Thread(new Job(printQueue), "thread-" + i);
		}
		//13、启动这10个线程
		for (int i = 0; i < 10; i++) {
			ts[i].start();
		}
	}

}
