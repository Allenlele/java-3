package com.wds.java.concurrency.chapter2.fifth;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1、定义PricesInfo类，存放两个产品的价格信息
 * @author wds
 *
 */
public class PricesInfo {
	/*
	 * 2、声明两个doble类型的变量price1,price2
	 */
	private double price1;
	private double price2;
	
	/*
	 * 3、声明ReadWriteLock的对象lock
	 */
	private ReadWriteLock lock;
	
	/*
	 * 4、在构造方法初始化，对于lock，创建ReentrantReadWriteLock对象
	 */
	public PricesInfo() {
		this.price1 = 1.0;
		this.price2 = 2.0;
		lock = new ReentrantReadWriteLock();
	}
	
	/*
	 * 5、实现getPrice1()方法，返回属性price1的值，使用读锁控制对属性的访问
	 */
	public double getPrice1(){
		lock.readLock().lock();
		double value = price1;
		lock.readLock().unlock();
		return value;
	}
	
	/*
	 * 6、实现getPrice2()方法，返回属性price2的值，使用读锁控制对属性的访问
	 */
	public double getPrice2(){
		lock.readLock().lock();
		double value = price2;
		lock.readLock().unlock();
		return value;
	}
	
	/*
	 * 7、实现setPrices()方法，设置price1和price2的值，并使用写锁对其控制
	 */
	public void setPrices(double price1, double price2){
		lock.writeLock().lock();
		this.price1 = price1;
		this.price2 = price2;
		
		lock.writeLock().unlock();
	}
	
}
