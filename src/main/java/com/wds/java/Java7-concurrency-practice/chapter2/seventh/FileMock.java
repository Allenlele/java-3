package com.wds.java.concurrency.chapter2.seventh;

/**
 * 1、实现一个类，模拟一个文本文件，一个String数组的属性content，一个int类型的index，
 * 分别存储文件内容及检索行
 * @author wds
 *
 */
public class FileMock {
	
	private String content[];
	private int index;
	
	/*
	 * 2、实现构造方法，并初始化两个属性
	 */
	public FileMock(int size, int length) {
		content = new String[size];
		for(int i = 0; i < size; i++){
			StringBuilder buffer = new StringBuilder(length);
			for(int j = 0; j < length; j++){
				int indice = (int)(Math.random() * 255);
				buffer.append((char)indice);
			}
			content[i] = buffer.toString();
		}
		index = 0;
	}
	
	/*
	 * 3、实现hasMoreLines()方法，如果还有行待处理返回true，
	 * 到达文件尾部返回false
	 */
	public boolean hasMoreLines(){
		return index < content.length;
	}
	
	/*
	 * 4、实现getLine()方法，返回index所批向的内容，将将index加1
	 */
	public String getLine(){
		if(this.hasMoreLines()){
			System.out.println("Mock: " + (content.length - index));
			return content[index++];
		}
		return null;
	}
}
