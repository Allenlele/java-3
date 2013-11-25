package com.wds.java.concurrency.chapter1.eighth;

/**
 * 3、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//4、创建Task对象及线程，启动运行，调用setUncaughtExceptionHandler()方法设置 
		//非检查异常的处理器，执行线程
		Task task = new Task();
		Thread t = new Thread(task);
		t.setUncaughtExceptionHandler(new ExceptionHandler());
		t.start();
	}

}
