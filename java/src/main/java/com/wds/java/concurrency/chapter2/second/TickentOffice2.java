package com.wds.java.concurrency.chapter2.second;

/**
 * 12、实现TickentOffice2对象，并实现Runnable接口
 * @author wds
 *
 */
public class TickentOffice2 implements Runnable {
	
	/*
	 * 13、定义Cinema对象，并在构造方法中初始化
	 */
	private Cinema cinema;
	
	public TickentOffice2(Cinema cinema) {
		this.cinema = cinema;
	}

	/*
	 * 14、实现run()方法，并模拟对两个影院的操作 
	 */
	@Override
	public void run() {
		cinema.sellTicket2(2);
		cinema.sellTicket2(4);
		cinema.sellTicket1(2);
		cinema.sellTicket1(1);
		cinema.returnTicket2(2);
		cinema.sellTicket1(3);
		cinema.sellTicket2(2);
		cinema.sellTicket1(2);
	}

}
