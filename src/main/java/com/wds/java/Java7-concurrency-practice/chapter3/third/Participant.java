package com.wds.java.concurrency.chapter3.third;

import java.util.concurrent.TimeUnit;

/**
 * 12、创建Participant类，实现Runnable接口，代表视频会议系统的每一个参与者
 * @author wds
 *
 */
public class Participant implements Runnable {
	//13、定义Videoconference对象
	private Videoconference conference;
	//14、定义String类型的属性
	private String name;
	//15、实现构造方法并初始化属性
	public Participant(Videoconference conference, String name) {
		this.conference = conference;
		this.name = name;
	}

	//16、实现参与者的run()方法
	@Override
	public void run() {
		//17、第一将线程休眠随机时间
		long duration = (long)(Math.random() * 10);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//18、之后，调用Videoconference对象的arrive()方法表示参与者的到达
		conference.arrive(name);
	}

}
