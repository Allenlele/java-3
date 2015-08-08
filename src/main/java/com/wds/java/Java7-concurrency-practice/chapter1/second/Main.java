package com.wds.java.concurrency.chapter1.second;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.State;

/**
 * 4、实现主类，名为Main，包含main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//5、创建有10个元素线程的数组，以及10个元素线程状态的数组
		Thread threads[] = new Thread[10];
		Thread.State status[] = new Thread.State[10];
		
		/*
		 * 6、创建10个Calculator的对象，使用不同的数字初始化并运行，将
		 * 其中五个线程的优先级设置为最低，另五个设置为最高
		 */
		for(int i = 0; i < 10; i++){
			threads[i] = new Thread(new Calculator(i));
			if(i % 2 == 0){
				threads[i].setPriority(Thread.MAX_PRIORITY);
			}else{
				threads[i].setPriority(Thread.MIN_PRIORITY);;
			}
			threads[i].setName("Thread" + i);
			
		}
		
		//7、创建PrintWriter对象将线程状态的变化写到文件中
		FileWriter file;
		PrintWriter pw = null;
		try {
			file = new FileWriter("log\\log.txt", true);
			pw = new PrintWriter(file);
			
			//8、写入10个线程当前状态，现在已经变成NEW
			for (int i = 0; i < 10; i++) {
				pw.println("Main: Status of Thread" + i + " : " + threads[i].getState());
				status[i] = threads[i].getState();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//9、启动线程执行
		for(int i = 0; i < 10; i ++){
			threads[i].start();
		}
		
		//10、一直检查线程状态直到10个线程结束，如果检测到线程状态的变更，将其写到日志文件中
		boolean finish = false;
		while(!finish){
			for(int i = 0; i < 10; i++){
				if(threads[i].getState() != status[i]){
					writeThreadInfo(pw, threads[i], status[i]);
					status[i] = threads[i].getState();
				}
			}
			finish = true;
			for(int i = 0; i < 10; i++){
				finish = finish && (threads[i].getState() == State.TERMINATED);
			}
		}
		
		pw.flush();
		pw.close();
	}

	/**
	 * 11、实现writeThreadInfo方法，该方法记将线程ID、名称、优先级，旧状态、新状态写入志文件
	 */
	private static void writeThreadInfo(PrintWriter pw, Thread thread,
			State state) {
		pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
		pw.printf("Main : Priority: %d\n",thread.getPriority());
		pw.printf("Main : Old State: %s\n",state);
		pw.printf("Main : New State: %s\n",thread.getState());
		pw.printf("Main : ************************************\n");
	}

}
