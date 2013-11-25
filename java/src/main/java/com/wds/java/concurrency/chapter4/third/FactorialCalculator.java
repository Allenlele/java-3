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
		//
		int result = 1;
		
		//
		if((number == 0) || (number == 1)){
			result = 1;
		}else{
			for(int i = 2; i <= number; i++){
				result *= i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		
		System.out.printf("%s: %d\n",Thread.currentThread().getName(),result);
		return result;
	}
	
}
