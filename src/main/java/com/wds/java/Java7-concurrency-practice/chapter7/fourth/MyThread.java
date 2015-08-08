package com.wds.java.concurrency.chapter7.fourth;

import java.util.Date;

/**
 * 1
 * @author wds
 *
 */
public class MyThread extends Thread{
	
	//2
	private Date creationDate;
	private Date startDate;
	private Date finishDate;
	
	//3
	public MyThread(Runnable target, String name) {
		super(target, name);
		setCreationDate();
	}

	//4
	@Override
	public void run() {
		setStartDate();
		super.run();
		setFinishDate();
	}
	
	//5
	public void setCreationDate() {
		this.creationDate = new Date();
	}

	//6
	public void setStartDate() {
		this.startDate = new Date();
	}

	//7
	public void setFinishDate() {
		this.finishDate = new Date();
	}
	
	//8
	public long getExecutionTime(){
		return finishDate.getTime() - startDate.getTime();
	}
	
	//9
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(getName());
		buffer.append(": ");
		buffer.append(" Creation Date: ");
		buffer.append(creationDate);
		buffer.append(" : Running time: ");
		buffer.append(getExecutionTime());
		buffer.append(" Milliseconds.");
		return buffer.toString();
	}
	
}
