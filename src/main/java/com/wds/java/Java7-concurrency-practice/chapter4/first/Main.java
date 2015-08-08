package com.wds.java.concurrency.chapter4.first;

/**
 * 15、最后实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		Server server = new Server();
		for(int i = 0; i < 100; i++){
			Task task = new Task("Task-" + i);
			server.executeTask(task);
		}
		server.endServer();
	}
}
