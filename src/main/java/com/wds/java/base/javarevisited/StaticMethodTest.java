package com.wds.java.base.javarevisited;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 该代码来自：http://javarevisited.blogspot.sg/2013/11/difference-between-static-vs-non-static-method-java.html
 * 翻译完的文章地址：http://blog.csdn.net/wangdongsong1229/article/details/17020765
 * 静态方法和非静态方法之间的区别
 * @author wds
 */
public class StaticMethodTest {
	
	private static int version;
	@SuppressWarnings("unused")
	private String name;
	
	private static final Logger logger = LoggerFactory.getLogger(StaticMethodTest.class);

	public static void main(String[] args) {
		
		//无须创建任何对象直接调用静态方法
		StaticMethodTest.staticMethod();
		
		//创建对象调用非静态方法
		StaticMethodTest myObject = new StaticMethodTest();
		myObject.nonStaticMethod();
	}

	private void nonStaticMethod() {
		logger.info("I am a non static method In java");;

		//可以在非静态方法中访问静态变量
		logger.info("static version from non static method " + version);
	}

	private static void staticMethod() {
		logger.info("I am a static method in Java, version : " + version);
		
		//System.out.println(name); //compile time error
	}

}
