package com.wds.java.concurrency.chapter2.second;

/**
 * 1、创建Cinema类，并声明两个long类型的属性
 * @author wds
 *
 */
public class Cinema {
	private long vacancinesCinema1;
	private long vacancinesCinema2;
	
	//2、增加两个Object对象，名为controlCinema1和controlCinema2
	private final Object controlCinema1;
	private final Object controlCinema2;
	
	//3、实现构造方法，并初始化所有属性
	public Cinema() {
		controlCinema1 = new Object();
		controlCinema2 = new Object();
		
		vacancinesCinema1 = 20;
		vacancinesCinema2 = 20;
	}
	
	/*
	 * 4、实现sellTicket1方法
	 * 当电影院1售票时调用此方法，使用controlCinema1对象控制同步代码块
	 */
	public boolean sellTicket1(int number){
		synchronized (controlCinema1) {
			if(number < vacancinesCinema1){
				vacancinesCinema1 -= number;
				return true;
			}else{
				return false;
			}
		}
	}
	
	/*
	 * 5、实现sellTicket2方法
	 * 当电影院1售票时调用此方法，使用controlCinema2对象控制同步代码块
	 */
	public boolean sellTicket2(int number){
		synchronized (controlCinema2) {
			if(number < vacancinesCinema2){
				vacancinesCinema2 -= number;
				return true;
			}else{
				return false;
			}
		}
	}
	
	/*
	 * 6、当向电影院退票时，调用此方法，使用controlCinema1控制同步代码块
	 */
	public boolean returnTicket1(int number){
		synchronized (controlCinema1) {
			vacancinesCinema1 += number;
			return true;
		}
	}
	
	/*
	 * 7、当向电影院退票时，调用此方法，使用controlCinema2控制同步代码块
	 */
	public boolean returnTicket2(int number){
		synchronized (controlCinema2) {
			vacancinesCinema2 += number;
			return true;
		}
	}
	
	
	
	/*
	 * 8、实现另外两个方法，返回每个影院的空位数量
	 */
	public long getVacanciesCinema1(){
		return vacancinesCinema1;
	}
	
	public long getVacanciesCinema2(){
		return vacancinesCinema2;
	}
}
