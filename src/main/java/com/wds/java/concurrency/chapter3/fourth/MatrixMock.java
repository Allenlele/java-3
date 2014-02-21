package com.wds.java.concurrency.chapter3.fourth;

import java.util.Random;

/**
 * 1、开始这个Demo需要实现两个辅助的类，首先创建MatrixMock类。
 * 该类产生一个0到10之间的随机数矩阵，线程在这个矩阵中查找一个数字。
 * @author wds
 *
 */
public class MatrixMock {
	
	//2、定义矩阵data
	private int data[][];
	
	//3、实现构造方法，接受三个参数，矩阵的行数，每一行的长度及将要查找的数字，这三个参数为int类型
	public MatrixMock(int size, int length, int number) {
		//4、使用方法参数初始化三个变量
		int counter = 0;
		data = new int[size][length];
		Random random = new Random();
		
		//5、使用随机数填充矩阵，每产生一个数字，将其与待查找的数字对比，若相同，将计数器加1
		for(int i = 0; i < size; i++){
			for(int j = 0; j < length; j++){
				data[i][j] = random.nextInt(10);
				if(data[i][j] == number){
					counter++;
				}
			}
		}
		
		//6、最后，控制台输出信息，输出待查找的数字在矩阵中出现的次数，该信息用于检查线程执行结果是否正确
		System.out.printf("Mock: There are %d ocurrences of number in generated data.\n",counter,number);
	}
	
	//7、实现getRow()方法，接受一个int参数，指定矩阵中的行，如果存在返回这一行，若不存在返回null
	public int[] getRow(int row){
		if((row >= 0 && (row < data.length))){
			return data[row];
		}
		return null;
	}
	
}
