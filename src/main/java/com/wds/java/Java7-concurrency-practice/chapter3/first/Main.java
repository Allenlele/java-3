package com.wds.java.concurrency.chapter3.first;

/**
 * 15、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//16、创建PrintQueue对象，名为printQueue
		PrintQueue printQueue = new PrintQueue();
		
		//17、创建10个线程，每个线程执行Job对象，将发送文件给打印队列
		Thread[] ts = new Thread[10];
		for(int i = 0; i < 10; i++){
			ts[i] = new Thread(new Job(printQueue), "Thread-" + i);
		}
		
		//18、最后，启动线程
		for (int i = 0; i < 10; i++) {
			ts[i].start();
		}
	}

}
