package com.wds.java.concurrency.chapter2.sixth;

import com.wds.java.concurrency.chapter2.sixth.Job;

/**
 * 4、修改主类中代码块，启动线程，新的代码如下
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		PrintQueue printQueue = new PrintQueue();
		Thread[] ts = new Thread[10];
		for (int i = 0; i < 10; i++) {
			ts[i] = new Thread(new Job(printQueue), "thread-" + i);
		}
		
		for (int i = 0; i < 10; i++) {
			ts[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
