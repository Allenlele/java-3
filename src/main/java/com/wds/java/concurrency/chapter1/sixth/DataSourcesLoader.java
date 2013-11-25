package com.wds.java.concurrency.chapter1.sixth;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 1、创建DataSourceLoader类，并实现Runnable接口
 * @author wds
 *
 */
public class DataSourcesLoader implements Runnable {

	/**
	 * 2、实现run()方法，输出信息并启动执行，睡眠4秒并写另外一条信息表明其结束执行
	 */
	@Override
	public void run() {
		System.out.printf("Beginning data sources loading: %s\n", new Date());
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.printf("Beginning data sources has finished: %s\n", new Date());
	}

}
