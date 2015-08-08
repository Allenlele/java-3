package com.wds.java.concurrency.chapter7.ninth;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1
 * @author wds
 *
 */
public class MyPriorityTransferQueue<E> extends PriorityBlockingQueue<E> implements
		TransferQueue<E> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//2
	private AtomicInteger counter;
	
	//3
	private LinkedBlockingQueue<E> transfered;
	
	//4
	private ReentrantLock lock;
	
	//5
	public MyPriorityTransferQueue(){
		counter = new AtomicInteger(0);
		lock = new ReentrantLock();
		transfered = new LinkedBlockingQueue<E>();
	}

	//6
	@Override
	public boolean tryTransfer(E e) {
		lock.lock();
		boolean value;
		if(counter.get() == 0){
			value = false;
		}else{
			put(e);
			value = true;
		}
		lock.unlock();
		return value;
	}

	//7
	@Override
	public void transfer(E e) throws InterruptedException {
		lock.lock();
		if(counter.get() != 0){
			put(e);
			lock.unlock();
		}else{
			transfered.add(e);
			lock.unlock();
			synchronized (e) {
				e.wait();
			}
		}
	}

	//8
	@Override
	public boolean tryTransfer(E e, long timeout, TimeUnit unit)
			throws InterruptedException {
		lock.lock();
		if(counter.get() != 0){
			put(e);
			lock.unlock();
			return true;
		}else{
			transfered.add(e);
			long newTimeout = TimeUnit.MILLISECONDS.convert(timeout, unit);
			lock.unlock();
			e.wait(newTimeout);
			lock.lock();
			if(transfered.contains(e)){
				transfered.remove(e);
				lock.unlock();
				return false;
			}else{
				lock.unlock();
				return true;
			}
		}
	}

	//9
	@Override
	public boolean hasWaitingConsumer() {
		return (counter.get() != 0);
	}

	//10
	@Override
	public int getWaitingConsumerCount() {
		return counter.get();
	}
	
	//11
	@Override
	public E take() throws InterruptedException {
		lock.lock();
		counter.incrementAndGet();
		
		//12
		E value = transfered.poll();
		if(value == null){
			lock.unlock();
			value = super.take();
			lock.lock();
		//13
		}else{
			synchronized (value) {
				value.notify();
			}
		}
		
		//14
		counter.decrementAndGet();
		lock.unlock();
		
		return value;
	}

}
