package com.wds.java.concurrency.chapter1.sixth;

import java.util.Date;

/**
 * 4、创建主类，并实现main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//5、创建DataSourcesLoader类及线程
		DataSourcesLoader dsL = new DataSourcesLoader();
		Thread t = new Thread(dsL, "DataSourceLoader");
		
		//6、创建NetWorkConnectionsLoader类及线程
		NetWorkConnectionsLoader netL = new NetWorkConnectionsLoader();
		Thread t2 = new Thread(netL, "NetWork");
		
		//7、调用其start()方法，启动线程
		t.start();
		t2.start();
		
		//8、调用线程的join()方法，等待线程执行结束，该方法会抛出
		//InterruptedException异常，捕获它
		try {
			t.join();
			t2.join(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//9、输出执行结束的消息
		System.out.printf("Main: Configuration has been loaded. %s\n", new Date());
	}

}
