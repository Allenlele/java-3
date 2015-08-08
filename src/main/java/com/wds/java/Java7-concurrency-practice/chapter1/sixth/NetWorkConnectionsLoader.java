package com.wds.java.concurrency.chapter1.sixth;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 3、创建NetworkConnectionsLoader类，并实现Runnable接口，
 * 其run()方法体与DataSourcesLoader相同，但是要睡眠6秒
 * @author wds
 *
 */
public class NetWorkConnectionsLoader implements Runnable{

	@Override
	public void run() {
		System.out.printf("Beginning DataSourcesLoaderNetWorkConn loading: %s\n", new Date());
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.printf("Beginning DataSourcesLoaderNetWorkConn has finished: %s\n", new Date());
	}

}
