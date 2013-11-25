package com.wds.java.concurrency.chapter2.seventh;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 5、实现Buffer类，由生产者和消费者共享
 * @author wds
 *
 */
public class Buffer {
	
	/**
	 * 6.1、存储共享数据
	 */
	private LinkedList<String> buffer;
	/**
	 * 6.2、记录buffer长度
	 */
	private int maxSize;
	/**
	 * 6.3、ReentrantLock类型的lock控制对buffer的代码块
	 */
	private ReentrantLock lock;
	/*
	 * 6.4、两个Condition对象，分别为lines和space
	 */
	private Condition lines;
	private Condition space;
	/**
	 * 6.5、boolean类型表明是否有更的行
	 */
	private boolean pendingLines;
	
	
	/*
	 * 7、实现构造方法，并初始这些属性
	 */
	public Buffer(int maxSize){
		this.maxSize = maxSize;
		buffer = new LinkedList<>();
		lock = new ReentrantLock();
		space = lock.newCondition();
		lines = lock.newCondition();
		pendingLines = true;
	}
	
	/*
	 * 8、实现insert()方法，接收一个String类型的参数，并将其存入buffer，首先，
	 * 先得到锁，得到之后，判断buffer中有空余的空间，如果buffer已满，调用wait()方法在space条件中，
	 * 以待空间。当其它线程在space条件中调用sigal()或signalAll()方法时，线程就会被唤醒。
	 * 当这种情况发生时，线程将其存入buffer并在lines条件中调用signalAll()方法。如我们所见，这个条件
	 * 将唤醒所有等待buffer的线程
	 */
	public void insert(String line){
		lock.lock();
		try {
			while(buffer.size() == maxSize){
				space.await();
			}
			buffer.offer(line);
			System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(), buffer.size());
			lines.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	/*
	 * 9、实现get()方法，返回buffer中的第一个字符串，首先，获得锁，
	 * 得到之后，检查buffer中是否有元素，如果buffer为空，在lines条件中调用await()方法
	 * 以等待buffer中的元素。当其它线程在lines条件中调用signal()或signalAll()方法时
	 * 该线程就会被唤醒，之后得到buffer中的第一个元素，然后调用signalAll()方法，并返回
	 */
	public String get(){
		String line = null;
		lock.lock();
		try {
			while((buffer.size() == 0) && (hasPendingLines())){
				lines.await();
			}
			
			if(hasPendingLines()){
				line = buffer.poll();
				System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(), buffer.size());
				space.signalAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
		return line;
	}
	
	/*
	 * 10、实现setPendingLines方法，设置pendingLines的值，当木有字符产生时，由生产者调用
	 */
	public void setPendingLines(boolean pendingLines){
		this.pendingLines = pendingLines;
	}

	/*
	 * 11、实现hasPendingLines()方法，如果还有字符待处理返回true，否则为false
	 */
	public boolean hasPendingLines() {
		return pendingLines || buffer.size() > 0;
	}
}
