package com.wds.java.concurrency.chapter7.seventh;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

/**
 * 1
 * @author wds
 *
 */
public abstract class MyWorkerTask extends ForkJoinTask<Void> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//2
	private String name;
	
	//3
	public MyWorkerTask(String name){
		this.name = name;
	}
	
	//4
	@Override
	public Void getRawResult() {
		return null;
	}
	
	//5
	@Override
	protected void setRawResult(Void value) {
	}
	
	//6
	@Override
	protected boolean exec() {
		Date startDate = new Date();
		compute();
		Date finishDate = new Date();
		long diff = finishDate.getTime() - startDate.getTime();
		System.out.printf("MyWokerTask: %s : %d Milliseconds to complete.\n", name, diff);
		return true;
	}
	
	//7
	public String getName(){
		return name;
	}
	
	//8
	protected abstract void compute();
	
}
