package com.wds.java.concurrency.chapter4.forth;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UserValidator {
	private String name;

	public UserValidator(String name) {
		this.name = name;
	}
	
	public boolean validate(String name, String password){
		Random random = new Random();
		
		long duration = (long)(Math.random() * 10);
		
		System.out.printf("Validator %s: Validating a user during %d seconds\n", this.name, duration);
		
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		
		return random.nextBoolean();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
