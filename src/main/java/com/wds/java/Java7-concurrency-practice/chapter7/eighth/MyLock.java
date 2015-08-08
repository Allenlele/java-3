package com.wds.java.concurrency.chapter7.eighth;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 6
 * @author wds
 *
 */
public class MyLock implements Lock {
	
	//7
	private AbstractQueuedSynchronizer sync;
	
	//8
	public MyLock() {
		sync = new MyAbstractQueuedSynchronizer();
	}

	//9
	@Override
	public void lock() {
		sync.acquire(1);
	}

	//10
	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	//11
	@Override
	public boolean tryLock() {
		try{
			return sync.tryAcquireNanos(1, 1000);
		}catch(InterruptedException e){
			e.printStackTrace();
			return false;
		}
	}

	//12
	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		return sync.tryAcquireNanos(1, TimeUnit.NANOSECONDS.convert(time, unit));
	}

	//13
	@Override
	public void unlock() {
		sync.release(0);
	}

	//14
	@Override
	public Condition newCondition() {
		return sync.new ConditionObject();
	}

}
