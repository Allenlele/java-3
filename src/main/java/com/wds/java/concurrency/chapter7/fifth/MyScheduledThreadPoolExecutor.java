package com.wds.java.concurrency.chapter7.fifth;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 13
 * @author wds
 *
 */
public class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
	
	//14
	public MyScheduledThreadPoolExecutor(int corePoolSize) {
		super(corePoolSize);
	}
	
	//15
	@Override
	protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable,
			RunnableScheduledFuture<V> task) {
		MyScheduledTask<V> myTask = new MyScheduledTask<V>(runnable, null, task, this);
		return myTask;
	}
	
	//16
	@Override
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
			long initialDelay, long period, TimeUnit unit) {
		ScheduledFuture<?> task = super.scheduleAtFixedRate(command, initialDelay, period, unit);
		MyScheduledTask<?> myTask = (MyScheduledTask<?>)task;
		myTask.setPeriod(TimeUnit.MILLISECONDS.convert(period, unit));
		return task;
	}
	
}
