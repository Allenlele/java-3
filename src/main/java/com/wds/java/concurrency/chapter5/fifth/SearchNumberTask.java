package com.wds.java.concurrency.chapter5.fifth;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 7
 * @author wds
 *
 */
public class SearchNumberTask extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 8
	 */
	private int numbers[];
	
	/*
	 * 9
	 */
	private int start, end;
	
	/*
	 * 10
	 */
	private int number;
	
	/*
	 * 11
	 */
	private TaskManager manager;
	
	/*
	 * 12
	 */
	private final static int NOT_FOUND = -1;
	
	/*
	 * 13
	 */
	public SearchNumberTask(int[] numbers, int start, int end, int number,
			TaskManager manager) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
		this.number = number;
		this.manager = manager;
	}

	/*
	 * 14
	 */
	@Override
	protected Integer compute() {
		System.out.println("Task: " + start + ":" + end);
		//15
		int ret;
		if(end - start > 10){
			ret = launchTasks();
		//16
		}else{
			ret = lookForNumber();
		}
		//17
		return ret;
	}

	/*
	 * 18
	 */
	private int lookForNumber() {
		//19
		for(int i = start; i < end; i++){
			if(numbers[i] == number){
				System.out.printf("Task: Number %d found is position %d\n", number, i);
				manager.cancelTasks(this);
				return i;
			}
			//20
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//21
		return NOT_FOUND;
	}

	/*
	 * 22
	 */
	private int launchTasks() {
		int mid = (start + end) / 2;
		//源代码为Task，修改为SearchNumberTask
		SearchNumberTask task1 = new SearchNumberTask(numbers, start, mid, number, manager);
		SearchNumberTask task2 = new SearchNumberTask(numbers, start, mid, number, manager);
		
		//23
		manager.addTask(task1);
		manager.addTask(task2);
		
		//24
		task1.fork();
		task2.fork();
		
		//25
		int returnValue;
		returnValue = task1.join();
		if(returnValue != -1){
			return returnValue;
		}
		
		returnValue = task2.join();
		return returnValue;
	} 

	/*
	 * 26
	 */
	public void writeCancelMessage() {
		System.out.printf("Task: Canceled task from %d to %d\n", start, end);
	}
	

}
