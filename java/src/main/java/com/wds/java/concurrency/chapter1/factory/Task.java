package com.wds.java.concurrency.chapter1.factory;

import java.util.concurrent.TimeUnit;

/**
 * 5、创建Task类，并实现Runnable接口，该类的功能就是睡眠1秒钟
 * @author wds
 *
 */
public class Task implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
