package com.wds.java.concurrency.chapter4.forth;

import java.util.concurrent.Callable;

/**
 * 9、创建TaskValidator类，使用UserValidator对象以并行任务的方式执行验证任务，
 * 实现Callable接口，参数化类型为String类
 * @author wds
 *
 */
public class TaskValidator implements Callable<String> {
	//10、定义私有的UserValidator属性validator
	private UserValidator validator;
	//11、定义两个私有的String属性user和密码
	private String user;
	private String password;
	//12、实现构造方法初始化所有属性
	public TaskValidator(UserValidator validator, String user, String password) {
		super();
		this.validator = validator;
		this.user = user;
		this.password = password;
	}

	//13、实现call()方法，返回一个String对象
	@Override
	public String call() throws Exception {
		//14、如果UserValidator对象木有验证通过user，控制台输出信息说明情况并抛出Exception异常
		if(!validator.validate(user, password)){
			System.out.printf("%s: The user has not been found\n", validator.getName());
			throw new Exception("Error validating user");
		}
		//15、否则，控制台输出信息表示用户已经验证通过，返回UserValidator对象的名称
		System.out.printf("%s: The user has been found\n",validator.getName());
		return validator.getName();
	}

}
