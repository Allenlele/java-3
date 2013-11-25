package com.wds.java.concurrency.chapter1.evelenth;

/**
 * 1、创建MyThreadGroup类，继承ThreadGroup类，使用name参数声明构造方法
 * @author wds
 *
 */
public class MyThreadGroup extends ThreadGroup {
	
	public MyThreadGroup(String name) {
		super(name);
	}
	
	/**
	 * 2、重写uncaughtException()方法，当线程组中的任一线程抛出异常时，将会调用该方法，
	 * 输出有关异常、抛出异常的线程和被中断的余下线程的信息
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("The thread %s has thrown an Exception\n", t.getId());
		e.printStackTrace(System.out);
		System.out.printf("Terminating the rest of the Threads\n");
		interrupt();
	}
}
