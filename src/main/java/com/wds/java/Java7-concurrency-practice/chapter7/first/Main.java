package com.wds.java.concurrency.chapter7.first;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 2
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//10
		MyExecutor myExecutor = new MyExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
		
		//11
		List<Future<String>> results = new ArrayList<>();
		
		//12
		for(int i = 0; i < 10; i++){
			SleepTwoSecondsTask task = new SleepTwoSecondsTask();
			Future<String> result = myExecutor.submit(task);
			results.add(result);
		}
		
		//13
		for(int i = 0; i < 5; i++){
			String result;
			try {
				result = results.get(i).get();
				System.out.printf("Main: Result for Task %d %s \n", i , result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		//14
		myExecutor.shutdown();
		
		//15
		for(int i = 5; i < 10; i++){
			try {
				String result = results.get(i).get();
				System.out.printf("Main: Result for Task %d : %s\n", i ,result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			
		}
		
		//16
		try {
			myExecutor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//17
		System.out.printf("Main: End of the proram\n");
	}

}
