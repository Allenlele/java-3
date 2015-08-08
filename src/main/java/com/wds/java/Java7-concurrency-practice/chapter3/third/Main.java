package com.wds.java.concurrency.chapter3.third;

/**
 * 19、实现主类及主方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//20、创建Videoconference对象名为conference，要等待10个参与者
		Videoconference conference = new Videoconference(10);
		//21、创建线程运行Videoconference对象并开始
		Thread threadConference = new Thread(conference);
		threadConference.start();
		//22、创建10 个Participant对象，产为每个创建Thread对象，启动所有线程
		for(int i = 0; i < 10; i++){
			Participant p = new Participant(conference, "Participant-" + i);
			Thread t = new Thread(p);
			t.start();
		}
	}
}
