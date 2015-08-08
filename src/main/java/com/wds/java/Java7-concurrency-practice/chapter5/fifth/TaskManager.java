package com.wds.java.concurrency.chapter5.fifth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * 2、创建TaskManager类，使用该类存储所有在ForkJoinPool执行的任务，由于ForkJoinPool和ForkJoinTask类的限制
 * 会使用这个类取消ForkJoinPool类中所有任务
 * @author wds
 *
 */
public class TaskManager {
	
	/*
	 * 3、声明一个ForkJoinTask列表，其参数化类型为Integer
	 */
	private List<ForkJoinTask<Integer>> tasks;
	
	/*
	 * 4、实现构造方法并初始化任务列表
	 */
	public TaskManager() {
		this.tasks = new ArrayList<>();
	}
	
	/*
	 * 5、实现addTask()方法，将ForkJoinTask对象添加到任务列表
	 */
	public void addTask(ForkJoinTask<Integer> task){
		tasks.add(task);
	}
	
	
	/*
	 * 6、实现cancelTasks()方法，它将使用cancel()方法取消所有在任务列表的ForkJoinTask对象 。
	 * 接受一个ForkJoinTask对象做为参数，而该对象就是想要取消除此任务之外的其它任务
	 */
	public void cancelTasks(ForkJoinTask<Integer> cancelTask){
		for(ForkJoinTask<Integer> task : tasks){
			if(task != cancelTask){
				task.cancel(true);
				((SearchNumberTask)task).writeCancelMessage();
			}
		}
	}
	
	
	
	
	
}
