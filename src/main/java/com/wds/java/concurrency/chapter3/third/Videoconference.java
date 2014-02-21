package com.wds.java.concurrency.chapter3.third;

import java.util.concurrent.CountDownLatch;

/**
 * 1、创建Videoconference类，实现Runnable接口，该类将实现视频会议系统
 * @author wds
 *
 */
public class Videoconference implements Runnable {
	
	//2、定义CountDownLatch对象controller
	private final CountDownLatch controller;
	
	//3、实现构造方法，初始化CountDownLatch属性，Videoconference类将等待由接收的参数所设置的参数者
	public Videoconference(int number) {
		controller = new CountDownLatch(number);
	}
	
	//4、实现arrive()方法，当参与者到达视频会议系统时将会调用此方法，接收String类型的参数
	public void arrive(String name){
		//5、第一，参与到达时输出信息
		System.out.printf("%s has arrive\n", name);
		//6、之后调用CountDownLatch对象的countDown()方法
		controller.countDown();
		//7、最后，输出参与者数量，依赖CountDownLatch类的getCount()方法
		System.out.printf("VideoConference2: Waiting for %d participants.\n",controller.getCount());
	}

	//8、实现视频会议系统的主要方法，每个Runnable接口都必须有的run()方法
	@Override
	public void run() {
		//9、第一，调用getCount()方法输出视频会议系统中的参与者数量
		System.out.printf("VideoConference: Wainting for %d participants.\n", controller.getCount());
		try {
			//10、之后调用await()方法，等待所有参与者，该方法会抛出InterruptedException异常，使用catch代码处理
			controller.await();
			//11、最后，输出信息表明所有参与者都已经到达
			System.out.printf("VideoConference: All the participants have come\n");
			System.out.printf("VideoConference: Let's start...\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
