package com.wds.java.concurrency.chapter3.fifth;

import java.util.concurrent.Phaser;

/**
 * 24、实现样例的主类Main及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//25、创建Phaser类，有3个参与者
		Phaser phaser = new Phaser(3);
		//26、创建FileSearch对象，有三个不同的初始化路径，查找以log为后缀的文件
		FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
		FileSearch apps= new FileSearch("C:\\Program Files","log",phaser);
		FileSearch documents= new FileSearch("C:\\Documents And Settings","log",phaser);
		//27、创建线程执行第一个FileSearch对象
		Thread systemThread = new Thread(system, "system");
		systemThread.start();
		//28、创建线程执行第二个FileSearch对象
		Thread appsThread = new Thread(apps, "apps");
		appsThread.start();
		//29、创建线程执行第三个FileSearch对象
		Thread documentThread = new Thread(documents, "documents");
		documentThread.start();
		//30、等待3个线程执行结束
		try {
			systemThread.join();
			appsThread.join();
			documentThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//31、调用isTerminated()方法输出最终的标记
		System.out.println("Terminated: " + phaser.isTerminated());
	}

}
