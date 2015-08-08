package com.wds.java.concurrency.chapter1.eighth;

/**
 * 2、创建Task类实现Runnable接口并抛出非检查异常，通过将字符转成数字强制抛出异常
 * @author wds
 *
 */
public class Task implements Runnable {

	@Override
	public void run() {
		System.out.println(Integer.parseInt("tt"));
	}

}
