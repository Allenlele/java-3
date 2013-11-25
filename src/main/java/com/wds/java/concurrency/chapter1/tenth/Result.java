package com.wds.java.concurrency.chapter1.tenth;

/**
 * 1、首先创建Result类，存放第一个结束的线程名称
 * 声明字符类型的私有属性name，生成getter和setter方法
 * @author wds
 *
 */
public class Result {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
