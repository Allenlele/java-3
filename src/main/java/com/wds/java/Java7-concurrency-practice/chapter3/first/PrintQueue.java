package com.wds.java.concurrency.chapter3.first;

import java.util.concurrent.Semaphore;

/**
 * 1、创建PrintQueue类，实现打印队列
 * @author wds
 *
 */
public class PrintQueue {
	
	//2、定义Semaphore对象semaphore
	private final Semaphore semaphore;
	
	//3、实现构造方法，初始化semaphore对象，用于保护对打印队列的访问
	public PrintQueue() {
		semaphore = new Semaphore(1);
	}
	
	//4、实现printJob()方法，模拟文档打印，接受一个Object类型的参数document
	public void printJob(Object document){
		
		//5、在方法里面，首先，必须要通过acquire()方法获得信号，
		//该方法会抛出InterruptedException异常，因此需要一些代码处它
		try {
			semaphore.acquire();
			
			//6、之后，实现行模拟文档打印，等待随机时间
			long duration = (long)(Math.random() * 10);
			
			System.out.printf("%s: PrintQueue: Printing a job during %d second\n", Thread.currentThread().getName(), duration);
			
			Thread.sleep(duration);
		//7、最后，调用release()方法释放信号
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			semaphore.release();
		}
	}
}
