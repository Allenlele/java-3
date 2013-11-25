package com.wds.java.concurrency.chapter2.second;

/**
 * 9、实现TickentOffice1类，并实现Runnable接口
 * @author wds
 *
 */
public class TickentOffice1 implements Runnable {
	
	/*
	 * 10、声明Cinema对象，并在构造方法中初始此对象
	 */
	private Cinema cinema;
	
	public TickentOffice1(Cinema cinema) {
		this.cinema = cinema;
	}

	/*
	 * 11、实现run()方法，模拟对两个影院的操作 
	 */
	@Override
	public void run() {
		cinema.sellTicket1(3);
		cinema.sellTicket1(2);
		cinema.sellTicket2(2);
		cinema.returnTicket1(3);
		cinema.sellTicket1(5);
		cinema.sellTicket2(2);
		cinema.sellTicket2(2);
		cinema.sellTicket2(2);
	}

}
