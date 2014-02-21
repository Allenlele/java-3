package com.wds.java.concurrency.chapter4.fifth;

/**
 * 1、创建Result类，保存并发任务执行的结果
 * @author wds
 *
 */
public class Result {
	//2、定义两个私有属性，一个String类型的name，一个int类型的value
	private String name;
	private int value;
	
	
	//3、生成get()和set()方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
