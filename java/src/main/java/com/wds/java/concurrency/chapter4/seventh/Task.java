package com.wds.java.concurrency.chapter4.seventh;

import java.util.Date;


public class Task implements Runnable {
	
	private String name;
	
	public Task(String name) {
		super();
		this.name = name;
	}

	public String call() throws Exception {
		System.out.printf("%s: Starting at %s\n", name, new Date());
		return "Hello, World";
	}

	@Override
	public void run() {
		System.out.printf("%s: Starting at %s\n", name, new Date());		
	}

}
