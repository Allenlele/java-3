package com.wds.java.concurrency.chapter3.fourth;

/**
 * 8、实现类Results，将查找到的数字出现在矩阵中第一行的次数存入一个数组中。
 * @author wds
 *
 */
public class Result {
	//9、定义数组data
	private int data[];
	
	//10、实现构造方法，接 个整型参数，为数组元素的个数
	public Result(int size) {
		data = new int[size];
	}
	
	//11、实现setData()方法，接受数组的位置及值为参数，将值赋于data数组中下载为position的元素
	public void setData(int position, int value){
		data[position] = value;
	}
	
	//12实现getData()方法，返回数组data
	public int[] getData(){
		return data;
	}
}
