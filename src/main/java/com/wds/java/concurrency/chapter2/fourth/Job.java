package com.wds.java.concurrency.chapter2.fourth;

/**
 * 7、创建Job类并实现Runnable接口
 * @author wds
 *
 */
public class Job implements Runnable {

	/*
	 * 8、定义PrintQueue对象，并在构造方法中初始化
	 */
	private PrintQueue printQueue;
	
	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}
	
	/*
	 * 9、实现run()方法，使用PrintQueue对象派送打印工作 
	 */
	@Override
	public void run() {
		System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
		printQueue.printJob(new Object());
		System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
	}

}
