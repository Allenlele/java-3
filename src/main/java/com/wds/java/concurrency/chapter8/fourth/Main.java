package com.wds.java.concurrency.chapter8.fourth;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 7、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{
		//8、创建ForkJoinPool对象
		ForkJoinPool pool = new ForkJoinPool();
		
		//9、创建元素有10000个的int数组
		int array[] = new int[10000];
		
		//10、创建一个新的任务，处理整个数组
		Task task1 = new Task(array, 0, array.length);
		
		//11、使用execute()方法将任务发给pool执行，
		pool.execute(task1);
		
		//12、当任务未结束时，调用showLog()方法输出ForkJoinPool类的状态信息，并使线程睡眠1秒
		while(!task1.isDone()){
			showLog(pool);
			TimeUnit.SECONDS.sleep(1);
		}
		
		//13、使用shutDown()y方法关闭pool
		pool.shutdown();
		
		//14、使用awaitTermination()方法等待pool结束
		pool.awaitTermination(1, TimeUnit.DAYS);
		
		//15、调用showLog()方法，输出ForkJoinPool类的状态信息，并输出程序执行结束的信息
		showLog(pool);
		System.out.printf("Main: End of the program.\n");
	}
	
	
	//16、实现showLog()方法，接受一个ForkJoinPool对象作为参数，输出关于它的状态信息及线程、任务的执行信息
	private static void showLog(ForkJoinPool pool) {
		System.out.printf("**********************\n");
		System.out.printf("Main: Fork/Join Pool log\n");
		System.out.printf("Main: Fork/Join Pool: Parallelism: %d\n",pool.getParallelism());
		System.out.printf("Main: Fork/Join Pool: Pool Size: %d\n",pool.getPoolSize());
		System.out.printf("Main: Fork/Join Pool: Active Thread Count: %d\n",pool.getActiveThreadCount());
		System.out.printf("Main: Fork/Join Pool: Running Thread Count: %d\n",pool.getRunningThreadCount());
		System.out.printf("Main: Fork/Join Pool: Queued Submission: %d\n",pool.getQueuedSubmissionCount());
		System.out.printf("Main: Fork/Join Pool: Queued Tasks: %d\n",pool.getQueuedTaskCount());
		System.out.printf("Main: Fork/Join Pool: Queued Submissions: %s\n",pool.hasQueuedSubmissions());
		System.out.printf("Main: Fork/Join Pool: Steal Count: %d\n",pool.getStealCount());
		System.out.printf("Main: Fork/Join Pool: Terminated : %s\n",pool.isTerminated());
		System.out.printf("**********************\n");
	}

}
