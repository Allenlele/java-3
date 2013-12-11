package com.wds.java.concurrency.chapter7.first;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 8
 * @author wds
 *
 */
public class SleepTwoSecondsTask implements Callable<String> {

	@Override
	public String call() throws Exception {
		TimeUnit.SECONDS.sleep(2);
		return new Date().toString();
	}

}
