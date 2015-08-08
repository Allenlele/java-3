package com.wds.java.concurrency.chapter7.ninth;

/**
 * 15
 * @author wds
 *
 */
public class Event implements Comparable<Event>{

	//16
	private String thread;
	
	//17
	private int priority;
	
	//18
	public Event(String thread, int priority){
		this.thread = thread;
		this.priority = priority;
	}
	
	//19
	public String getThread(){
		return thread;
	}
	
	//20
	public int getPriority(){
		return priority;
	}

	//21
	@Override
	public int compareTo(Event e) {
		if(this.priority > e.getPriority()){
			return -1;
		}else if(this.priority < e.getPriority()){
			return 1;
		}else{
			return 0;
		}
	}

}
