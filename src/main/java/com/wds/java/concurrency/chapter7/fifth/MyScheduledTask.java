package com.wds.java.concurrency.chapter7.fifth;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1
 * @author wds
 *
 * @param <V>
 */
public class MyScheduledTask<V> extends FutureTask<V> implements
		RunnableScheduledFuture<V> {
	
	//2
	private RunnableScheduledFuture<V> task;
	
	//3
	private ScheduledThreadPoolExecutor executor;
	
	//4
	private long period;

	//5
	private long startDate;
	
	//6
	public MyScheduledTask(Runnable runnable, V result, RunnableScheduledFuture<V> task, ScheduledThreadPoolExecutor executor){
		super(runnable, result);
		this.task = task;
	}

	//7
	@Override
	public long getDelay(TimeUnit unit) {
		if(!isPeriodic()){
			return task.getDelay(unit);
		}else{
			if(startDate == 0){
				return task.getDelay(unit);
			}else{
				Date now = new Date();
				long delay = startDate - now.getTime();
				return unit.convert(delay, TimeUnit.MILLISECONDS);
			}
		}
	}

	//8
	@Override
	public int compareTo(Delayed o) {
		return task.compareTo(o);
	}

	//9
	@Override
	public boolean isPeriodic() {
		return task.isPeriodic();
	}
	
	//10
	@Override
	public void run() {
		if(isPeriodic() && (!executor.isShutdown())){
			Date now = new Date();
			startDate = now.getTime() + period;
			executor.getQueue().add(this);
		}
		
		//11
		System.out.printf("Pre-MyScheduledTask: %s\n", new Date());
		System.out.printf("MyScheduledTask: Is periodic: %s\n", isPeriodic());
		super.runAndReset();
		System.out.printf("Post-MyScheduledTask: %s\n", new Date());
	}
	
	//12
	public void setPeriod(long period){
		this.period = period;
	}
	
	

}
