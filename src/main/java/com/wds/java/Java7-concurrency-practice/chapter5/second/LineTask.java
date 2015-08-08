package com.wds.java.concurrency.chapter5.second;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * 18、创建LineTask类，继承RecursiveTask类，泛型参数为Integer，
 * 该类实现在行中计算出现过相似字符
 * @author wds
 *
 */
public class LineTask extends RecursiveTask<Integer>{

	/*
	 * 19、定义序列号UID，由父类RecursiveTask、ForkJoinTask类实现了Serializable接口，所以该元素是非常有必要的。
	 * 定义私有的字符数组名为line，两个int的私有属性名为start和end，最定义一个String类型的私有属性word
	 */
	private static final long serialVersionUID = 1L;
	private String line[];
	private int start, end;
	private String word;
	
	//20、实现构造方法并初始化属性
	public LineTask(String[] line, int start, int end, String word) {
		this.line = line;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	/*
	 * 21、实现compute()方法，如果end和start之间小于100，就会调用count()方法，在由start和end之间字符片段中查找
	 */
	@Override
	protected Integer compute() {
		Integer result = null;
		if(end - start < 100){
			result = count(line, start, end, word);
		//22、否则，这一组词拆分成两组，创建两个LineTask对象，去处理这两组字符，调用invokeAll()方法执行。
		}else{
			int mid = (start + end) / 2;
			LineTask task1 = new LineTask(line, start, mid, word);
			LineTask task2 = new LineTask(line, mid, end, word);
			invokeAll(task1, task2);
			
			//23、然后，计算机结果并返回
			try {
				result = groupResults(task1.get(), task2.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/*
	 * 24、实现count()方法，接受完整一行的字符数组，start、end属性及待查找的字符
	 */
	private Integer count(String[] line, int start, int end, String word) {
		//25、对比word与start和end之间的字符，如果相同就将counter加1
		int counter = 0;
		for(int i = start; i < end; i++){
			if(line[i].equals(word)){
				counter++;
			}
			//26、放慢执行进度，睡眠10毫秒
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//27、返回counter变量
		return counter;
	}

	/*
	 * 28、实现groupResults()方法，
	 */
	private Integer groupResults(Integer number1, Integer number2) {
		Integer result;
		result = number1 + number2;
		return result;
	}


}
