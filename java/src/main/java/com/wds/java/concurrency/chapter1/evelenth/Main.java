package com.wds.java.concurrency.chapter1.evelenth;

/**
 * 5、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//6、创建MyThreadGroup对象
		MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
		//7、创建Task对象
		Task task = new Task();
		//8、创建两个线程对象启动它们
		for(int i = 0; i < 2; i++){
			Thread t = new Thread(threadGroup, task);
			t.start();
		}
	}

}
