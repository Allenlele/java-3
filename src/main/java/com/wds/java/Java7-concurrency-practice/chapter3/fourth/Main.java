package com.wds.java.concurrency.chapter3.fourth;

import java.util.concurrent.CyclicBarrier;

/**
 * 32、最后实现样例的主类Main及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		//33、定义5个常量，保存程序的参数
		final int rows = 10000;
		final int number = 1000;
		final int search = 5;
		final int participants = 5;
		final int lines_participant=2000;
		//34、创建MatrixMock对象mock，有10000行，每行1000个元素，待查找的数字为5
		MatrixMock mock = new MatrixMock(rows, number, search);
		//35、创建Result对象有10000个元素
		Result result = new Result(rows);
		//36、创建Grouper对象grouper
		Grouper grouper = new Grouper(result);
		//37、创建CyclicBarrier类barrier，该对象将等待5个线程，当线程执行结束时，执行先前创建的Grouper对象
		CyclicBarrier barrier = new CyclicBarrier(participants, grouper);
		//38、创建5个Searcher对象，5个线程执行它们，并启动这个5个线程
		Searcher searchers[] = new Searcher[participants];
		for(int i = 0; i < participants; i++){
			searchers[i]=new Searcher(i*lines_participant, (i*lines_participant)+lines_participant, mock, result, 5,barrier);
			Thread thread=new Thread(searchers[i]);
			thread.start();
			}
		System.out.printf("Main: The main thread has finished.\n");
	}

}
