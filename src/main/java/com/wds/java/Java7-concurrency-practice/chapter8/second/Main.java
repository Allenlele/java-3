package com.wds.java.concurrency.chapter8.second;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 8、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{
		
		//9、创建Phaser对象，名为phaser，有三个参与者
		Phaser phaser = new Phaser(3);
		
		//10、创建发布3个线程执行任务
		for(int i = 0; i < 3; i++){
			Task task = new Task(i+1, phaser);
			Thread thread = new Thread(task);
			thread.start();
		}
		
		//11、创建10次循环，输出phaser对象的信息
		for(int i = 0; i < 10; i++){
			
			//12、输出关于已经注册的参与方、阶段、到达阶段、未到达的信息
			System.out.printf("********************\n");
			System.out.printf("Main: Phaser Log\n");
			System.out.printf("Main: Phaser: Phase: %d\n",phaser.getPhase());
			System.out.printf("Main: Phaser: Registered Parties: %d\n",phaser.getRegisteredParties());
			System.out.printf("Main: Phaser: Arrived Parties: %d\n",phaser.getArrivedParties());
			System.out.printf("Main: Phaser: Unarrived Parties: %d\n",phaser.getUnarrivedParties());
			System.out.printf("********************\n");
			
			//13、每交循环之间休息1秒
			TimeUnit.SECONDS.sleep(1);
		}
		
	}

}
