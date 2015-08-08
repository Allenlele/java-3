package com.wds.java.concurrency.chapter8.fourth;

import java.util.concurrent.RecursiveAction;

/**
 * 1、创建Task类，继承RecursiveAction类
 * @author wds
 *
 */
public class Task extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//2、声明私有int类型的数组属性array，保存做加法操作的元素
	private int array[];
	
	//3、定义int类型的start和end属性，保存任务处理一块元素的启始和结束索引
	private int start;
	private int end;
	
	
	//4、实现构造方法并初始化
	public Task(int[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}


	//5、实现compute()方法，该方法是任务的主要逻辑，如果任务要处理超过100个元素，将其拆分成两个部分，
	//创建两个任务分别执行，使用fork()方法启动执行，调用join()方法等待其执行结束。
	@Override
	protected void compute() {
		if(end - start > 100){
			int mid = (start + end) / 2;
			Task task1 = new Task(array, start, mid);
			Task task2 = new Task(array, mid, end);
			
			task1.fork();
			task2.fork();
			
			task1.join();
			task2.join();
		//6、如果任务处理的元素小于100个，每个元素加1，在此之后使线程睡眠5毫秒
		}else{
			for(int i = start; i < end; i++){
				array[i]++;
				
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
