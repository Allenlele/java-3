package com.wds.java.concurrency.chapter3.sixth;

import java.util.concurrent.Phaser;

/**
 * 1、创建MyPhaser类，继承Phaser类
 * @author wds
 *
 */
public class MyPhaser extends Phaser {
	
	/*
	 * 2、重写onAdvance()方法。根据phaser属性值，调用不同的辅助方法。如果phase的值为0，调用studentsArrived()方法，
	 * 如果phase的值为1,调用finishFirstExercise()方法
	 * 如果phase的值为2，调用finishSecondExercise()方法
	 * 如果phase的值为3，调用finishExam()方法，否则返回true表明阶段已经结束
	 */
	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		switch (phase) {
		case 0:
			return studentsArrived();
		case 1:
			return finishFirstExercise();
		case 2:
			return finishSecondExercise();
		case 3:
			return finishExam();
		default:
			return true;
		}
	}
	
	//3、实现辅助方法studentsArrived()，向控制台输出两条日志信息，返回false表明阶段继续
	private boolean studentsArrived() {
		System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
		System.out.printf("Phaser: We have %d students.\n",getRegisteredParties());
		return false;
	}
	
	//4、实现辅助方法finishFirstExercise()方法，输出两条日志信息并返回false值表明阶段继续
	private boolean finishFirstExercise() {
		System.out.printf("Phaser: All the students have finished the first exercise.\n");
		System.out.printf("Phaser: It's time for the second one.\n");
		return false;
	}
	
	//5、实现辅助方法inishSecondExercise(),向控制台输出两条日志信息，并返回false值表明阶段继续
	private boolean finishSecondExercise() {
		System.out.printf("Phaser: All the students have finished the second exercise.\n");
		System.out.printf("Phaser: It's time for the third one.\n");
		return false;
	}

	//6、实现辅助方法finishExam()，向控制台输出两条日志信息，返回true，表明阶段完成其工作
	private boolean finishExam() {
		System.out.printf("Phaser: All the students have finished the exam.\n");
		System.out.printf("Phaser: Thank you for your time.\n");
		return true;
	}

	

	

}
