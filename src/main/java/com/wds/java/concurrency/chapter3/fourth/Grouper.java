package com.wds.java.concurrency.chapter3.fourth;

/**
 * 25、现在实现计算数字在矩阵中出现次数。使用Result对象计算，Result对象保存着矩阵中每一行出现的待查找的数字的次数，
 * 创建Grouper类，实现Runnable接口
 * @author wds
 *
 */
public class Grouper implements Runnable {
	
	//26、定义Result对象result
	private Result result;
	
	//27、实现构造方法并初始化Result对象
	public Grouper(Result result) {
		this.result = result;
	}

	//28、实现run()方法，计算数组中保存的待查找数字出现的次数
	@Override
	public void run() {
		//29、定义int属性，控制台输出消息表明程序启动
		int finalResult = 0;
		System.out.printf("Grouper: Processing results...\n");
		//30、调用getData()方法获取每一行中数字出现的次数，之后处理数组中所有元素，并将其加到finalResult变量
		int data[] = result.getData();
		for(int number : data){
			finalResult += number;
		}
		//31、控制台输出结果
		System.out.printf("Grouper: Total result: %d.\n",finalResult);
	}

}
