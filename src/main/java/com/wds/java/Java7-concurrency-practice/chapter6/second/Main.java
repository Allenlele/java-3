package com.wds.java.concurrency.chapter6.second;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 5
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		
		//6
		LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>(3);
		
		//7
		Client client = new Client(list);
		Thread thread = new Thread(client);
		thread.start();
		
		//8
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 3; j++){
			 	String request = list.take();
				System.out.printf("Main: Request: %s at %s. Size: %d\n", request, new Date(), list.size());
			}
			TimeUnit.MILLISECONDS.sleep(300);
		}
		
		//9
		System.out.printf("Main: End of the program.\n");
	}

}
