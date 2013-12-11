package com.wds.java.concurrency.chapter6.fifth;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 5
 * @author wds
 *
 */
public class Task implements Runnable {
	
	//6
	private ConcurrentSkipListMap<String, Contact> map;
	
	//7
	private String id;
	
	//8
	public Task(ConcurrentSkipListMap<String, Contact> map, String id) {
		this.map = map;
		this.id = id;
	}

	/*
	 * 9
	 */
	@Override
	public void run() {
		for(int i = 0; i < 1000; i++){
			Contact contact = new Contact(id, String.valueOf(i + 1000));
			map.put(id + contact.getPhone(), contact);
		}
	}

}
