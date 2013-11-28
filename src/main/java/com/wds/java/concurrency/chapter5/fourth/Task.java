package com.wds.java.concurrency.chapter5.fourth;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 1
 * @author wds
 *
 */
public class Task extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 2
	 */
	private int array[];
	
	/*
	 * 3
	 */
	private int start, end;
	
	/*
	 * 4
	 */
	public Task(int[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	/*
	 * 5
	 */
	@Override
	protected Integer compute() {
		System.out.printf("Task: Start from %d to %d\n", start, end);
		
		//6
		if(end - start < 10){
			if((3 > start) && (3 < end)){
				throw new RuntimeException("This task throws an Excption: Task from " + start + " to " + end);
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//7
		}else{
			int mid = (end + start) / 2;
			Task task1 = new Task(array, start, mid);
			Task task2 = new Task(array, mid, end);
			invokeAll(task1, task2);
		}
		
		//8
		System.out.printf("Task: End from %d to %d\n", start, end);
		
		//9
		return 0;
	}

}
