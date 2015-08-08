package com.wds.java.concurrency.chapter7.second;

import java.util.concurrent.TimeUnit;

/**
 * 1
 * @author wds
 *
 */
public class MyPriorityTask implements Runnable, Comparable<MyPriorityTask> {
	
	//2
	private int priority;
	
	//3
	private String name;
	
	//4
	public MyPriorityTask(String name, int priority){
		this.name = name;
		this.priority = priority;
	}

	//5
	public int getPriority(){
		return this.priority;
	}

	//6
	@Override
	public int compareTo(MyPriorityTask o) {
		if(this.getPriority() < o.getPriority()){
			return 1;
		}
		if(this.getPriority() > o.getPriority()){
			return -1;
		}
		return 0;
	}

	@Override
	public void run() {
		System.out.printf("MyPriorityTask: %s Priority : %d \n", name, priority);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
