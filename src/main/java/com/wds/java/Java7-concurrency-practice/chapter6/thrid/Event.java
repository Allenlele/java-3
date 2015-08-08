package com.wds.java.concurrency.chapter6.thrid;

/**
 * 1
 * @author wds
 *
 */
public class Event implements Comparable<Event> {
	
	//2
	private int thread;
	
	//3
	private int priority;
	
	//4
	public Event(int thread, int priority) {
		this.thread = thread;
		this.priority = priority;
	}
	
	//5
	public int getThread() {
		return thread;
	}

	//6
	public int getPriority() {
		return priority;
	}

	//7
	@Override
	public int compareTo(Event e) {
		if(this.priority > e.priority){
			return -1;
		}else if(this.priority < e.getPriority()){
			return 1;
		}else{
			return 0;
		}
	}

}
