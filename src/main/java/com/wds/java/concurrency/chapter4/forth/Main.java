package com.wds.java.concurrency.chapter4.forth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 16、现在，实现样例的主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//17、创建两个String对象 user和password，初始值都是test
		String username = "test";
		String password = "test";
		//18、定义两个UserValidator对象ldapValidator和dbValidator
		UserValidator ldapValidator = new UserValidator("LDAP");
		UserValidator dbValidator = new UserValidator("DataBase");
		//19、定义两个TaskValidator对象ldapTask和dbTask，以ldapValidator和dbValidator分别初始化
		TaskValidator ldapTask = new TaskValidator(ldapValidator, username, password);
		TaskValidator dbTask = new TaskValidator(dbValidator, username, password);
		//20、定义TaskValidator对象列表，将刚创建的两个任务添加进来
		List<TaskValidator> taskList = new ArrayList<>();
		taskList.add(ldapTask);
		taskList.add(dbTask);
		//21、使用newCacheThreadCool()方法创建ThreadPoolExecutor对象，定义String对象result
		ExecutorService executor = (ExecutorService)Executors.newCachedThreadPool();
		String result;
		try {
			//22、调用executor对象的invokeAny()方法，该方法接受一个taskList参数，返回String，同样将
			//返回的String对象输出到控制上
			result = executor.invokeAny(taskList);
			System.out.printf("Main: Result: %s\n",result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		//23、使用shutDown()方法关闭executor，控制台输出信息说明程序执行结束
		executor.shutdown();
		System.out.printf("Main: End of the Execution\n");
	}

}
