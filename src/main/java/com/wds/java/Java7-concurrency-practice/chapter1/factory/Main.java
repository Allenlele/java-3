package com.wds.java.concurrency.chapter1.factory;

/**
 * 6、创建主类并实现main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//7、创建MyThreadFactory对象及Task对象
		MyThreadFactory factory = new MyThreadFactory("MyThreadFacotry");
		Task task = new Task();
		
		//8、使用MyThreadFactory创建10个线程并启动
		Thread thread;
		System.out.printf("Starting the threads\n");
		for(int i = 0; i < 10; i++){
			thread = factory.newThread(task);
			thread.start();
		}
		
		//9、输出统计信息
		System.out.printf("Factory stats:\n");
		System.out.printf("%s\n", factory.getStats());
	}

}
