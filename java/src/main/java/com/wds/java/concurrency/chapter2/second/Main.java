package com.wds.java.concurrency.chapter2.second;

/**
 * 15、实现主类和main()方法
 * @author wds
 *
 */
public class Main {

	
	public static void main(String[] args) {
		//16、定义Cinema对象
		Cinema cinema = new Cinema();
		
		//17、创建TickentOffice1对象和线程、运行
		TickentOffice1 o1 = new TickentOffice1(cinema);
		Thread t1 = new Thread(o1);
		
		//18、创建TickentOffice2对象和线程、运行
		TickentOffice2 o2 = new TickentOffice2(cinema);
		Thread t2 = new Thread(o2);
		
		//19、启动线程
		t1.start();
		t2.start();
		
		//20、等待线程执行结束
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//21、输出两个影院的空票数量
		System.out.printf("Room 1 Vacancies: %d\n", cinema.getVacanciesCinema1());
		System.out.printf("Room 2 Vacancies: %d\n", cinema.getVacanciesCinema2());
	}
	
}
