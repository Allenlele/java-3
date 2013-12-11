package com.wds.java.concurrency.chapter6.second;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 1
 * @author wds
 *
 */
public class Client implements Runnable {
	
	//2
	private LinkedBlockingDeque<String> requestList;
	
	//3
	public Client(LinkedBlockingDeque<String> requestList) {
		this.requestList = requestList;
	}

	//4
	@Override
	public void run() {
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 5; j++){
				StringBuilder request = new StringBuilder();
				request.append(i);
				request.append(":");
				request.append(j);
				requestList.push(request.toString());
				
				System.out.printf("Client: %s at %s.\n", request, new Date());
				
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.printf("Client: End.\n");
	}

}
