package com.wds.java.concurrency.chapter5.fifth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * 2
 * @author wds
 *
 */
public class TaskManager {
	
	/*
	 * 3
	 */
	private List<ForkJoinTask<Integer>> tasks;
	
	/*
	 * 4
	 */
	public TaskManager() {
		this.tasks = new ArrayList<>();
	}
	
	/*
	 * 5
	 */
	public void addTask(ForkJoinTask<Integer> task){
		tasks.add(task);
	}
	
	
	/*
	 * 6
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
