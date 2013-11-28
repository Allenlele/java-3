package com.wds.java.concurrency.chapter5.fifth;

import java.util.Random;

/**
 * 1
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
