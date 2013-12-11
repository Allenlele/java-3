package com.wds.java.concurrency.chapter7.fourth;

import java.util.concurrent.TimeUnit;

/**
 * 15
 * @author wds
 *
 */
public class MyTask implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
