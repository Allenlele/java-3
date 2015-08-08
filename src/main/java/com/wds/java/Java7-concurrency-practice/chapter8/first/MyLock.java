package com.wds.java.concurrency.chapter8.first;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、创建MyLock类，继承ReentrantLock类
 * @author wds
 *
 */
public class MyLock extends ReentrantLock {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//2、实现 getOwnerName()方法，该访问使用Lock类中被保护getOwner()方法返回拥有锁的线程名称
	public String getOwnerName(){
		if(this.getOwner() == null){
			return "None";
		}
		return this.getOwner().getName();
	}
	
	//3、实现getTrheads()方法，该方法使用Lock类中被保护的getQueuedThreads()方法返回在锁上队列的线程列表
	public Collection<Thread> getThreads(){
		return this.getQueuedThreads();
	}
	
	

}
