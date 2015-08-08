package com.wds.java.concurrency.chapter7.sixth;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 8
 * @author wds
 *
 */
public class MyRecursiveTask extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//9
	private int array[];
	
	//10
	private int start, end;
	
	//11
	public MyRecursiveTask(int[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	//12
	@Override
	protected Integer compute() {
		Integer ret;
		MyWorkerThread thread = (MyWorkerThread)Thread.currentThread();
		thread.addTask();
		//书中无此代码，根据源码加上去的
		if (end-start>100) {
			int mid=(start+end)/2;
			MyRecursiveTask task1=new MyRecursiveTask(array,start,mid);
			MyRecursiveTask task2=new MyRecursiveTask(array,mid,end);
			invokeAll(task1,task2);
			ret=addResults(task1,task2);
		} else {
			int add=0;
			for (int i=start; i<end; i++) {
				add+=array[i];
			}
			ret=new Integer(add);
		}
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	//13
	//源书中的参数为：Task,根据上下文修改为MyRecusiveTask
	private Integer addResults(MyRecursiveTask task1, MyRecursiveTask task2){
		int value;
		try {
			value = task1.get().intValue() + task2.get().intValue();
		} catch (InterruptedException | ExecutionException e) {
			//源书中的异常是两个，而JDK7支持多个异常的定义，将书的两个catch语句，合并成一个
			e.printStackTrace();
			value = 0;
		}
		
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return value;
	}

}
