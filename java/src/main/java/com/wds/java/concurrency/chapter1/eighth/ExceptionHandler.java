package com.wds.java.concurrency.chapter1.eighth;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 1、首先实现一个类以处理非检查异常，该类实现UncaughtExceptionHandler接口，并实现
 * 接口中uncaughtException()方法，将类命名为ExceptionHandler，输出异常及线程信息
 * 代码如下
 * @author wds
 *
 */
public class ExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("An exception has been captured\n");
		System.out.printf("Thread:%s\n", t.getId());
		System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
		System.out.printf("Stack Trace: \n");
		e.printStackTrace(System.out);
		System.out.printf("Thread status:%s\n", t.getState());
	}

}
