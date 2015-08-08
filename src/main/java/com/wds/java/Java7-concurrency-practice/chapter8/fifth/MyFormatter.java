package com.wds.java.concurrency.chapter8.fifth;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * 1、创建名为MyFormatter的类，继承java.util.logging.Formatter类，实现抽象方法format()，
 * 它接收一个LogRecord对象作为参数，返回一个String对象的日志信息
 * @author wds
 *
 */
public class MyFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		StringBuilder sb=new StringBuilder();
		sb.append("["+record.getLevel()+"] - ");
		sb.append(new Date(record.getMillis())+" : ");
		sb.append(record.getSourceClassName()+ "."+record.getSourceMethodName() + " : ");
		sb.append(record.getMessage() + "\n");
		return sb.toString();
	}

}
