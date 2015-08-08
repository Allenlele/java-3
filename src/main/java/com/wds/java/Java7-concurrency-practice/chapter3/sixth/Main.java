package com.wds.java.concurrency.chapter3.sixth;

/**
 * 17、实现主类Main及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//18、创建MyPhaser对象
		MyPhaser phaser = new MyPhaser();
		//19、创建5个Student对象，调用register()方法将其注册到phaser中
		Student students[] = new Student[5];
		for(int i = 0; i < students.length; i++){
			students[i] = new Student(phaser);
			phaser.register();
		}
		
		//20、创建5个线程运行并启动学生
		Thread threads[] = new Thread[students.length];
		for(int i = 0; i < students.length; i++){
			threads[i] = new Thread(students[i], "Student-" + i);
			threads[i].start();
		}
		
		//21、等待5个线程完成
		for(int i = 0; i < threads.length; i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//22、调用isTerminated()方法输出阶段类处于终结状态
		System.out.printf("Main: The phaser has finished: %s.\n",phaser.isTerminated());
	}

}
