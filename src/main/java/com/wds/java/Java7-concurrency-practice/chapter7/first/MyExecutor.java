package com.wds.java.concurrency.chapter7.first;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1
 * @author wds
 *
 */
public class MyExecutor extends ThreadPoolExecutor {
	
	//2
	private ConcurrentHashMap<String, Date> startTimes;

	//3
	public MyExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		startTimes = new ConcurrentHashMap<>();
	}

	//4
	@Override
	public void shutdown() {
		System.out.printf("MyExecutor: Going to shutdown.\n");
		System.out.printf("MyExecutor: Executed tasks: %d\n",getCompletedTaskCount());
		System.out.printf("MyExecutor: Running tasks: %d\n",getActiveCount());
		System.out.printf("MyExecutor: Pending tasks: %d\n",getQueue().size());
		super.shutdown();
	}
	
	//5
	@Override
	public List<Runnable> shutdownNow() {
		System.out.printf("MyExecutor: Going to immediately shutdown.\n");
		System.out.printf("MyExecutor: Executed tasks: %d\n",getCompletedTaskCount());
		System.out.printf("MyExecutor: Running tasks: %d\n",getActiveCount());
		System.out.printf("MyExecutor: Pending tasks: %d\n",getQueue().size());
		return super.shutdownNow();
	}
	
	//6
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.printf("MyExecutor: A task is beginning: %s : %s\n",t.getName(),r.hashCode());
		startTimes.put(String.valueOf(r.hashCode()), new Date());
	}
	
	//7
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		Future<?> result = (Future<?>) r;
		try {
			System.out.printf("*********************************\n");
			System.out.printf("MyExecutor: A task is finishing.\n");
			System.out.printf("MyExecutor: Result: %s\n", result.get());
			Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
			Date finishDate = new Date();
			long diff = finishDate.getTime() - startDate.getTime();
			System.out.printf("MyExecutor: Duration: %d\n", diff);
			System.out.printf("*********************************\n");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		super.afterExecute(r, t);
	}
	
}
