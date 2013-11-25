package com.wds.java.concurrency.chapter1.seventh;

import java.util.Date;

/**
 * 1、创建Event类，该存储程序使用到的事件信息，声明两个私有属性，
 * 一个为java.util.Date类型的date，一个为String类型的event,
 * 为其生成getter和setter方法
 * @author wds
 *
 */
public class Event {
	private Date date;
	private String event;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
	
}
