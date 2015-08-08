package com.wds.java.concurrency.chapter4.first;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 8、实现Server类，将执行接收到的每个任务，创建类Server类
 * @author wds
 *
 */
public class Server {
	//9、定义ThreadPoolExecutor属性executor
	private ThreadPoolExecutor executor;
	//10、实现构造方法并使用Executors初始化ThreadPoolExecutor对象
	public Server() {
		executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
	}
	//11、实现executeTask()方法，接受一个Task对象作为参数并将其发送给执行者，首先
	//在控制台输出信息表示一个新的任务已经到达
	public void executeTask(Task task){
		System.out.printf("Server: A new task has arrived\n");
		
		//12、调用execute()方法执行发送给执行者的任务
		executor.execute(task);
		
		//13、最后，控制台输出执行者的数据查看其状态
		System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
		
	}

	//14、实现endServer()方法，在这个方法中，调用执行者的shutDown()结束其执行
	public void endServer(){
		executor.shutdown();
	}
	
}
