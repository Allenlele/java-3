package com.wds.java.concurrency.chapter4.fifth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 14、最后，实现主类Main及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//15、使用Executors类的newCachedThreadPool()方法创建ThreadPoolExecutor对象
		ExecutorService executor = (ExecutorService)Executors.newCachedThreadPool();
		//16、创建一个Task对象的列表，创建3个Task对象并保存到列表中
		List<Task> taskList = new ArrayList<>();
		for(int i = 0; i < 3; i++){
			Task task = new Task(i + "");
			taskList.add(task);
		}
		//17、创建Future对象的列表，泛型参数是Result
		List<Future<Result>> resultList = null;
		//18、调用ThreadPoolExecutor类的invokeAll()方法，该类将返回给刚才创建的Future对象的列表
		try {
			resultList = executor.invokeAll(taskList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//19、调用shutDown()方法释放执行者
		executor.shutdown();
		//20、循环Future对象的列表，输出结果
		System.out.println("Main: Printing the results");
		for (int i = 0; i < resultList.size(); i++) {
			Future<Result> future = resultList.get(i);
			try {
				Result result = future.get();
				System.out.println(result.getName() + ": " + result.getValue());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
