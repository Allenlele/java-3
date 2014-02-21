package com.wds.java.concurrency.chapter4.eleventh;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 1、创建RejectedTaskController类，实现RejectedExecutionHandler接口，
 * 实现接口中的rejectedExecution()方法，控制台输出被拒绝的任务名称、执行者名称及状态
 * @author wds
 *
 */
public class RejectedTaskController implements RejectedExecutionHandler {
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.printf(
				"RejectedTaskController: The task %s has been rejected\n",
				r.toString());
		System.out.printf("RejectedTaskController: %s\n", executor.toString());
		System.out.printf("RejectedTaskController: Terminating: %s\n",
				executor.isTerminating());
		System.out.printf("RejectedTaksController: Terminated:%s\n",
				executor.isTerminated());
	}

}
