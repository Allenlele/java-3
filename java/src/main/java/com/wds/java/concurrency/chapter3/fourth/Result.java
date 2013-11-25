package com.wds.java.concurrency.chapter3.fourth;

public class Result {
	private int data[];
	
	public Result(int size) {
		data = new int[size];
	}
	
	public void setData(int position, int value){
		data[position] = value;
	}
	
	public int[] getData(){
		return data;
	}
}
