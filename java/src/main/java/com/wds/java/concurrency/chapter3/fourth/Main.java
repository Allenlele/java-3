package com.wds.java.concurrency.chapter3.fourth;

import java.util.concurrent.CyclicBarrier;

public class Main {

	public static void main(String[] args) {
		final int rows = 10000;
		final int number = 1000;
		final int search = 5;
		final int participants = 5;
		final int lines_participant=2000;
		
		MatrixMock mock = new MatrixMock(rows, number, search);
		
		Result result = new Result(rows);
		Grouper grouper = new Grouper(result);
		
		CyclicBarrier barrier = new CyclicBarrier(participants, grouper);
		
		Searcher searchers[] = new Searcher[participants];
		for(int i = 0; i < participants; i++){
			searchers[i]=new Searcher(i*lines_participant, (i*lines_participant)+lines_participant, mock, result, 5,barrier);
			Thread thread=new Thread(searchers[i]);
			thread.start();
			}
		System.out.printf("Main: The main thread has finished.\n");
	}

}
