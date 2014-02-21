package com.wds.java.concurrency.chapter5.fourth;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 1、创建Task类，实现RecursiveTask类，泛型化参数类型为Integer
 * @author wds
 *
 */
public class Task extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 2、定义属性有int数组为array，模拟将要处理的数据
	 */
	private int array[];
	
	/*
	 * 3、定义私有的int属性start和end，这两个属性决定任务将处理的元素
	 */
	private int start, end;
	
	/*
	 * 4、实现构造方法并初始化这些属性
	 */
	public Task(int[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	/*
	 * 5、实现computer()方法，由于参数化的类型为Integer类，该方法返回一个Integer对象
	 * 首先控制台输出信息及start和end属性的值
	 */
	@Override
	protected Integer compute() {
		System.out.printf("Task: Start from %d to %d\n", start, end);
		
		//6、如果处理的这块元素由start和end属性决定的大小 不超过10，检查元素中第4个位置（下标为3）在那个块中，如果有，就抛出
		 // RuntimeException异常，之后将任务休眠一秒。
		if(end - start < 10){
			if((3 > start) && (3 < end)){
				throw new RuntimeException("This task throws an Excption: Task from " + start + " to " + end);
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//7、否则，将这块元素一分为二，创建 Task对象并处理这些块，使用invokeAll()方法执行
		}else{
			int mid = (end + start) / 2;
			Task task1 = new Task(array, start, mid);
			Task task2 = new Task(array, mid, end);
			invokeAll(task1, task2);
		}
		
		//8、控制台输出信息表示任务结果，输出start和end属性的值
		System.out.printf("Task: End from %d to %d\n", start, end);
		
		//9、返回0
		return 0;
	}

}
