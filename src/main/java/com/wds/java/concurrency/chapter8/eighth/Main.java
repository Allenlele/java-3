package com.wds.java.concurrency.chapter8.eighth;

import edu.umd.cs.mtc.TestFramework;

/**
 * 8、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Throwable {
		//9、创建ProducerConsumerTest对象test
		ProducerConsumerTest test = new ProducerConsumerTest();
		
		//10、使用TestFramework类的runOnce方法执行测试
		System.out.printf("Main: Starting the test\n");
		TestFramework.runOnce(test);
		System.out.printf("Main: The test has finished\n");
	}

}
