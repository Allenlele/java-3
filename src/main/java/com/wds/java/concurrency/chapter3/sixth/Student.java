package com.wds.java.concurrency.chapter3.sixth;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 7、创建Student类，实现Runnable接口，该类模拟学生考试
 * @author wds
 *
 */
public class Student implements Runnable {
	
	//8、定义Phaser对象phaser
	private Phaser phaser;
	
	//9、实现构造方法并初始化Phaser对象
	public Student(Phaser phaser) {
		this.phaser = phaser;
	}

	//10、实现run()方法，模拟实现考试
	@Override
	public void run() {
		//11、首先，向控制台写信息表示学生已经抵达考试，调用arriveAndAwaitAdvance()方法等待其它线程
		System.out.printf("%s: Has arrived to do the exam. %s\n",Thread.currentThread().getName(),new Date());
		phaser.arriveAndAwaitAdvance();
		
		//12、之后，控制台输出信息，调用私有方法doExercise1()，模拟考试的第一个测验，输出
		//另外的信息调用阶段类的arriveAndAwaitAdvance()方法等待其它纯种完成第一阶段考试
		System.out.printf("%s: Is going to do the first exercise. %s\n",Thread.currentThread().getName(),new Date());
		doExercise1();
		System.out.printf("%s: Has done the first exercise. %s\n",Thread.currentThread().getName(),new Date());
		phaser.arriveAndAwaitAdvance();
		
		//13、以同样的方式实现测验2和测验3
		System.out.printf("%s: Is going to do the second exercise.%s\n",Thread.currentThread().getName(),new Date());
		doExercise2();
		System.out.printf("%s: Has done the second exercise. %s\n",Thread.currentThread().getName(),new Date());
		
		phaser.arriveAndAwaitAdvance();
		
		System.out.printf("%s: Is going to do the third exercise. %s\n",Thread.currentThread().getName(),new Date());
		doExercise3();
		System.out.printf("%s: Has finished the exam. %s\n",Thread.currentThread().getName(),new Date());
	}

	//14、实现辅助方法doExercise1()，该方法使线程休眠随机时间
	private void doExercise1() {
		try {
			long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	//15、实现辅助方法doExercise2()，该方法使线程休眠随机时间
	private void doExercise2() {
		try {
			long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	//15、实现辅助方法doExercise3()，该方法使线程休眠随机时间
	private void doExercise3() {
		try {
			long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
