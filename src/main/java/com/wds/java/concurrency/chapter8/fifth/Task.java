package com.wds.java.concurrency.chapter8.fifth;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * 10、创建Task类，实现Runnable接口，用于测试Logger对象
 * @author wds
 *
 */
public class Task implements Runnable {

	//11、实现run()方法
	@Override
	public void run() {
		
		//12、首先，定义Logger对象，名为logger，通过将本类的名称作为参数使用MyLogger类的getLogger()方法初始
		Logger logger = MyLogger.getLogger(this.getClass().getName());
		
		//13、使用entering()方法，输出日志信息标识线程执行开始
		logger.entering(Thread.currentThread().getName(), "run()");
		//Sleep the thread for two seconds.
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//14、使用exiting()方法，标识线程执行结束
		logger.exiting(Thread.currentThread().getName(),"run()",Thread.currentThread());
	}

}
