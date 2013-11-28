package com.wds.java.concurrency.chapter5.first;

/**
 * 1、创建名为Product的类，存放商品的名称和价格
 * @author wds
 *
 */
public class Product {
	
	/*
	 * 2、声明私有属性name和price
	 */
	private String name;
	private double price;
	
	/*
	 * 3、为两个属性生成getter和setter方法
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
