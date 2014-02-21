package com.wds.java.concurrency.chapter4.forth;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 1、创建UserValidator类，实现用户验证的逻辑
 * @author wds
 *
 */
public class UserValidator {
	//2、定义私有String属性name，保存用户验证系统的名称
	private String name;
	//3、实现构造方法，并初始化name属性
	public UserValidator(String name) {
		this.name = name;
	}
	//4、实现validate()方法，接收两个String类型的参数，一个是用户，一个是密码
	public boolean validate(String name, String password){
		//5、创建Random对象random
		Random random = new Random();
		//6、等待随机时间，模拟验证过程
		long duration = (long)(Math.random() * 10);
		
		System.out.printf("Validator %s: Validating a user during %d seconds\n", this.name, duration);
		
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		//7、返回随机的Boolean值，用户验证通过返回true，不通过返回false
		return random.nextBoolean();
	}
	
	//8、实现getName()方法，返回name属性的值
	public String getName() {
		return name;
	}
}
