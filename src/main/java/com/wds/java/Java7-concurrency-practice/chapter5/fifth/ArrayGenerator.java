package com.wds.java.concurrency.chapter5.fifth;

import java.util.Random;

/**
 * 1、创建ArrayGenerator类，该类产生一个由随机整形数字组成的数组，大小于size指定。实现一个generateArray()方法，
 * 将会产生一个数字数组，接受数组大小做为参数
 * @author wds
 *
 */
public class ArrayGenerator {
	
	public int[] generateArray(int size){
		int[] array = new int[size];
		Random random = new Random();
		for(int i = 0; i < size; i++){
			array[i] = random.nextInt(10);
		}
		return array;
	}
}
