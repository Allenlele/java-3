package com.wds.java.concurrency.chapter5.fifth;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 7、实现SearchNumberTask类，继承RecursiveTask类，参数化类型为Integer类。该类在数字数组中的一块元素中查找一个数字
 * @author wds
 *
 */
public class SearchNumberTask extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 8、定义一个私有的int类型的数组
	 */
	private int numbers[];
	
	/*
	 * 9、定义两个私有的int属性start和end，这些属性决定了任务将要处理的元素
	 */
	private int start, end;
	
	/*
	 * 10、定义私有的int属性number，保存将要进行查找的数字
	 */
	private int number;
	
	/*
	 * 11、定义私有的TaskManager属性manager，使用此对象取消所有任务
	 */
	private TaskManager manager;
	
	/*
	 * 12、定义私有的int常量并初始为-1，当任务木有查找到数字时，将由任务返回此值
	 */
	private final static int NOT_FOUND = -1;
	
	/*
	 * 13、实现构造方法并初始化这些属性
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
	 * 14、实现compute()方法，控制台输出信息表示该方法启动，并输出start和end的值
	 */
	@Override
	protected Integer compute() {
		System.out.println("Task: " + start + ":" + end);
		//15、如果start和end之差大于10（任务处理的元素超过10个），调用lanchTasks()方法，将这个任务的工作拆分成两个子任务
		int ret;
		if(end - start > 10){
			ret = launchTasks();
		//16、否则，在任务调用lookForNumber()方法在这数组中一块元素中查找数字
		}else{
			ret = lookForNumber();
		}
		//17、返回任务的结果
		return ret;
	}

	/*
	 * 18、实现lookForNumber()方法
	 */
	private int lookForNumber() {
		//19、对于任务要处理的块中所有元素，比较其与待查找的数字，如果相等，控制台输出信息，在这种情况下，使用TaskManager的
		//cancelTasks()方法取消所有任务，并返回元素所在的位置。
		for(int i = start; i < end; i++){
			if(numbers[i] == number){
				System.out.printf("Task: Number %d found is position %d\n", number, i);
				manager.cancelTasks(this);
				return i;
			}
			//20、在循环里，将任务休眠1秒钟
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//21、最后，返回-1
		return NOT_FOUND;
	}

	/*
	 * 22、实现lanchTasks()方法，首先将这个处理的数字块一分炎二，创建两个任务处理它们
	 */
	private int launchTasks() {
		int mid = (start + end) / 2;
		//源代码为Task，修改为SearchNumberTask
		SearchNumberTask task1 = new SearchNumberTask(numbers, start, mid, number, manager);
		SearchNumberTask task2 = new SearchNumberTask(numbers, start, mid, number, manager);
		
		//23、添加两个任务到TaskManager对象
		manager.addTask(task1);
		manager.addTask(task2);
		
		//24、使用fork()方法异步执行两个任务
		task1.fork();
		task2.fork();
		
		//25、等待任务结束，如果结果不等于-1返回第一个任务的结果，否则返回第二个任务的结果
		int returnValue;
		returnValue = task1.join();
		if(returnValue != -1){
			return returnValue;
		}
		
		returnValue = task2.join();
		return returnValue;
	} 

	/*
	 * 26、实现writeCancelMessage()方法，当任务取消时输出信息
	 */
	public void writeCancelMessage() {
		System.out.printf("Task: Canceled task from %d to %d\n", start, end);
	}
	

}
