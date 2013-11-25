package com.wds.java.concurrency.chapter2.third;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 1、创建EventStorage类，包括maxSize和LinkedList<Date>类型的属性storage
 * @author wds
 *
 */
public class EventStorage {
	private int maxSize;
	private List<Date> storage;
	
	//2、实现构造方法，并初始化
	public EventStorage() {
		maxSize = 10;
		this.storage = new LinkedList<>();
	}
	
	/*
	 * 3、实现同步方法set(),将事件存放到storage中，
	 * 首先检查storage是否满，如果已经放假，调用wait()方法直到storage有空位，
	 * 在方法结尾处，调用notifyAll()方法，叫醒所有睡眠线程
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized void set(){
		while(storage.size() == maxSize){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		((LinkedList)storage).offer(new Date());
		System.out.printf("Set : %d\n", storage.size());
		notifyAll();
	}
	
	/*
	 * 4、实现同步方法get()，获取存入storage中的事件，首先，检查storage是否有事件，
	 * 如果没有调用就wait()方法直接stroage中有事件为止，在方法结尾处，调用notifyAll()方法叫醒所有
	 * 已经睡眠的线程
	 */
	public synchronized void get(){
		while(storage.size() == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Get: %d: %s\n", storage.size(), ((LinkedList<?>)storage).poll());
		notifyAll();
	}
}
