package com.wds.java.concurrency.chapter5.second;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * 7、创建DocumentTask类，继承RecursiveTask类，泛型的参数类型为Integer
 * 该类实现计算与word的相似的行数
 */
public class DocumentTask extends RecursiveTask<Integer> {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 8、声明字符矩阵document，两个私有int类型的属性start,end，一个私有的字符类型名为word
	 */
	private String document[][];
	private int start, end;
	private String word;
	
	/*
	 * 9、实现构造方法，并初始化所有私有属性
	 */
	public DocumentTask(String[][] document, int start, int end, String word) {
		this.document = document;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	/*
	 * 10、实现counte()方法，如果end和start之差小于10，调用processLines()方法计算在之间行中出现类似字符的数量
	 */
	@Override
	protected Integer compute() {
		Integer result = null;
		if(end - start < 10){
			result = processLines(document, start, end, word);
		
		/*
		 * 11、否则，将这组行拆分成两个对象，创建DocumentTask对象分别处理这两组，并调用invokeAll()方法执行。
		 */
		}else{
			int mid = (start + end) / 2;
			DocumentTask task1 = new DocumentTask(document, start, mid, word);
			DocumentTask task2 = new DocumentTask(document, mid, end, word);
			invokeAll(task1, task2);
			
			//12、然后，加上由groupResults()方法对这两个任务返回值的计算结果，最后返回任务计算的最终结果
			try {
				result = groupResults(task1.get(), task2.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * 13、实现processLines()方法，接收字符矩阵，start、end及word属性，以便进行查找。
	 */
	private Integer processLines(String[][] document, int start, int end,
			String word) {
		List<LineTask> tasks = new ArrayList<>();
		//14、对每一行，都会创建一个LineTask对象处理一个完整行，将这些任务存入一个tasks列中
		for(int i = start; i < end; i++){
			LineTask task = new LineTask(document[i], 0, document[i].length, word);
			tasks.add(task);
		}
		//15、调用invokeAll()处理所有任务
		invokeAll(tasks);
		
		//16、计算所有任务返回的结果，并返回计算的结果
		int result = 0;
		for(int i = 0; i < tasks.size(); i++){
			LineTask task = tasks.get(i);
			try {
				result = result + task.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		return new Integer(result);
	}

	/*
	 * 17、实现groupresults()方法，两个数相加，并返回
	 */
	private Integer groupResults(Integer number1, Integer number2) {
		Integer result;
		result = number1 + number2;
		return result;
	}


}
