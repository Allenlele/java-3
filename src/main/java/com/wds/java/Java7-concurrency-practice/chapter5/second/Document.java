package com.wds.java.concurrency.chapter5.second;

import java.util.Random;

/**
 * 1、创建Document类，生成字符矩阵，模拟一个文档
 * @author wds
 *
 */
public class Document {
	
	/*
	 * 2、创建有几个词的字符数组，该数组用于生产字符矩阵
	 */
	private String words[] = {"the", "hello","goodbye","packt", "java","thread","pool","random","class","main"};
	
	/*
	 * 3、实现generateDocument()方法，接受3个参数，一个为行数，一个每行的字符数，一个为待查找的字符，
	 * 返回字符矩阵
	 */
	public String[][] generateDocument(int numLines, int numWords, String word){
		
		//4、首先，创建一些必要的对象生成文档：String矩阵和Random对象生成随机数
		int counter = 0;
		String document[][] = new String[numLines][numWords];
		Random random = new Random();
		
		/*
		 * 5、用字符填充数组，字符矩阵中的字符来自words数组中的随机位置，如果产生的字符与word（待查找字符）相同，计数器加1
		 * 可以使用计数器检查程序是否正确的运行。
		 */
		for(int i = 0; i < numLines; i++){
			for(int j = 0; j < numWords; j++){
				int index = random.nextInt(words.length);
				document[i][j] = words[index];
				if(document[i][j].equals(word)){
					counter++;
				}
			}
		}
		
		//6、输出与待查找字符相同的字符数并返回生成的字符矩阵
		System.out.printf("DocumentMock: The word appears " + counter + " times in the document.\n");
		return document;
	}

	
	
}
