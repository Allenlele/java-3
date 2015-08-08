package com.wds.java.concurrency.chapter1.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * 1、创建MyThreadFactory对象，并实现ThreadFactory接口
 * @author wds
 *
 */
public class MyThreadFactory implements ThreadFactory {
	
	/*
	 * 2、声明三个属性，数字类型的counter，存储创建线程的数量，
	 * 字符类型的name，为每个线程的名称，
	 * List<String>类型的stats，为创建线程的统计信息
	 * 实现构造方法并初始化这三个属性
	 */
	private int counter;
	private String name;
	private List<String> stats;
	
	public MyThreadFactory(String name) {
		this.counter = 0;
		this.name = name;
		this.stats = new ArrayList<>();
	}
	
	/**
	 * 3、实现newThread()方法，接收一个Runnable类型的参数，返回Thread对象
	 * 创建Thread对象，根据name属性命名其名字，并保存统计信息
	 */
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, name + "-Thread_" + counter);
		counter++;
		stats.add(String.format("Created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
		return t;
	}
	
	/**
	 * 4、实现统计方法，返回包含了统计数据的String对象
	 */
	public String getStats(){
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = stats.iterator();
		while(it.hasNext()){
			buffer.append(it.next());
			buffer.append("\n");
		}
		return buffer.toString();
	}

}
