package com.wds.java.concurrency.chapter8.eighth;

import java.util.concurrent.LinkedTransferQueue;

import edu.umd.cs.mtc.MultithreadedTestCase;

/**
 * MultithreadedTC测试并发代码的事例代码
 * 1、创建ProducerConsumerTest类，继承MultithreadedTestCase类
 * @author wds
 *
 */
public class ProducerConsumerTest extends MultithreadedTestCase{
	
	//2、定义LinkedTransferQueue属性，泛型参数为：String
	private LinkedTransferQueue<String> queue;
	
	//3、实现initialize()方法，不接收任何参数，不带返回值，调用父类的initialize()方法，之后初始属性queue。
	@Override
	public void initialize(){
		super.initialize();
		queue = new LinkedTransferQueue<>();
		System.out.printf("Test: The test has been initialized\n");
	}
	
	//4、实现thread1()方法，实现第一个消费者逻辑，调用take()方法，控制台输出获取的值
	public void thread1() throws InterruptedException{
		String ret = queue.take();
		System.out.printf("THread 1: %s\n", ret);
	}
	
	//5、实现thread2()方法，实现第二个消费者的逻辑，首先使用awitForTick()
	//方法等待第一个线程在tak()方法中已睡眠，之后调用take()，然后控制台输出返回值。
	public void thread2() throws InterruptedException{
		waitForTick(1);
		String ret = queue.take();
		System.out.printf("Thread 2 : %s\n", ret);
	}
	
	//6、实现thread3()方法，生产者逻辑，使用waitForTick()方法一直等待两个消费者在task()方法阻塞
	//，之后调用put()方法，给队列插入两个字符
	public void thread3(){
		waitForTick(1);
		waitForTick(2);
		queue.put("Event 1");
		queue.put("Event 2");
		System.out.printf("Thread 3: Inserted two elements\n");
	}
	
	//7、最后实现finish()方法，控制台输出消息，测试完成，使用assertEquals方法检查两个事情已经被消费者消费了
	//（队列的size为0)
	@SuppressWarnings("deprecation")
	public void finish(){
		super.finish();
		System.out.printf("Test: End\n");
		assertEquals(true, queue.size()==0);
		System.out.printf("Test: Result: The queue is empty\n");
	}
}
