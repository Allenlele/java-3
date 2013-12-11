package com.wds.java.concurrency.chapter6.fifth;

/**
 * 1
 */
public class Contact {
	
	//2
	private String name;
	private String phone;
	
	//3
	public Contact(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}

	//4
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	
	
}
