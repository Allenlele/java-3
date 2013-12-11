package com.wds.java.concurrency.chapter8.fifth;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 15、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//16、定义Logger对象logger，使用MyLogger类的getLogger()方法初始化它，参数为Core
		Logger logger=MyLogger.getLogger("Core");
		
		//17、使用entering()方法输出信息标识主程序开始执行
		logger.entering("Core", "main()", args);
		
		//18、创建5个线程的数据
		Thread threads[] = new Thread[5];
		
		//19、创建5个Task对象和5个线程执行它们，输出日志信息标识即将发布一个新的线程和已经创建的线程
		for(int i = 0; i < threads.length; i++){
			logger.log(Level.INFO, "Launching thread: " + i);
			Task task = new Task();
			threads[i] = new Thread(task);
			logger.log(Level.INFO, "Thread created: " + threads[i].getName());
			threads[i].start();
		}
		
		//20、输出日志信息标识已经创建完线程
		logger.log(Level.INFO, "Ten Threads created." + "Waiting for its finalization");
		
		//21、使用join()方法等待线程执行结束，每一个线程结束之后，输出日志信息标识线程已经结束
		for(int i = 0; i < threads.length; i++){
			try {
				threads[i].join();
				logger.log(Level.INFO, "Thread has finished its execution", threads[i]);
			} catch (InterruptedException e) {
				logger.log(Level.SEVERE, "Exception", e);
			}
		}
		
		//22、使用exiting()方法输出日志信息标识主程序执行结束
		logger.exiting("Core", "main()");
	}

}
