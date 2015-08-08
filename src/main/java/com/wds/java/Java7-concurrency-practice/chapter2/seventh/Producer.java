package com.wds.java.concurrency.chapter2.seventh;

/**
 * 12、现在转向生产者，实现生产者Producer并实现Runnable接口
 * @author wds
 *
 */
public class Producer implements Runnable {
	
	/*
	 * 13、声明两个属性，一个是FileMock对象，一个是Buffer对象
	 */
	private FileMock mock;
	private Buffer buffer;

	/*
	 * 14、实现构造方法并初始化
	 */
	public Producer(FileMock mock, Buffer buffer) {
		this.mock = mock;
		this.buffer = buffer;
	}

	/*
	 * 15、实现run()方法，调用FileMock中的元素，并调用insert()方法将其存入buffer中。
	 * 一旦完成，就调用setPendingLines()方法修改buffer，其不再生产元素
	 */
	@Override
	public void run() {
		buffer.setPendingLines(true);
		while(mock.hasMoreLines()){
			String line = mock.getLine();
			buffer.insert(line);
		}
		buffer.setPendingLines(false);
	}

}
