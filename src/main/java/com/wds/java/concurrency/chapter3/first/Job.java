package com.wds.java.concurrency.chapter3.first;

/**
 * 8、创建Job类，实现Runnable接口，该实现一个工作，发送文档给打印机
 * @author wds
 *
 */
public class Job implements Runnable {
	
	//9、定义PrintQueue对象
	private PrintQueue printQueue;
	
	//10、实现构造方法，初始化PrintQueue对象
	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}

	//11、实现run()方法
	@Override
	public void run() {
		//12、首先，控制台输出信息以示job已经开始执行
		System.out.printf("%s: Going to print a Job\n", Thread.currentThread().getName());
		//13、之后调用PrintQueue对象的printJob()方法
		printQueue.printJob(new Object());
		//14、最后，控制输出信息以示执行完成
		System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
	}

}
