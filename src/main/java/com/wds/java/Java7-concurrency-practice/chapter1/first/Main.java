package com.wds.java.concurrency.chapter1.first;

/**
 * 4、实现主类，定义名包含main方法的名为Main的类
 * @author wds
 *
 */
public class Main {

	/**
	 * 5、在方法中创建一个10次for循环，
	 * 在循环体中，创建Calculator对象及Thread对象，
	 * 将Calculator对象作为Thread对象的参数，并调用Thread对象的start方法
	 */
	public static void main(String[] args) {
		for(int i = 1; i <= 10; i ++){
			Calculator cal = new Calculator(i);
			Thread t = new Thread(cal);
			t.start();
		}
	}

}
