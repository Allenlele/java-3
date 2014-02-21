package com.wds.java.concurrency.chapter4.third;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 1、创建名为FactorialCalculator的类，并实现Callable接口，其泛型参数是Integer
 * @author wds
 *
 */
public class FactorialCalculator implements Callable<Integer>{
	
	/**
	 * 2、声明一个私有变更number,该任务以number因子，用于计算
	 */
	private Integer number;
	
	/**
	 * 3、实现该类的构造方法，并初始化number的值
	 * @param number
	 */
	public FactorialCalculator(Integer number) {
		this.number = number;
	}

	/**
	 * 4、返回属性number的价乖
	 */
	public Integer call() throws Exception {
		//5、首先，创建并初始化方法内部变量
		int result = 1;
		
		//6、如果number的值是1或0，返回1，否则，计算机number的阶乘，两个乘法之间，为教育性目的，
		//使线程休眠20毫秒
		if((number == 0) || (number == 1)){
			result = 1;
		}else{
			for(int i = 2; i <= number; i++){
				result *= i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		//7、控制台输出操作结果
		System.out.printf("%s: %d\n",Thread.currentThread().getName(),result);
		//8、返回操作结果
		return result;
	}
	
}
