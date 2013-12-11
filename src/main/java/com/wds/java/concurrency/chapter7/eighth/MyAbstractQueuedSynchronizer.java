package com.wds.java.concurrency.chapter7.eighth;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 1
 * @author wds
 *
 */
public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//2
	private AtomicInteger state;
	
	//3
	public MyAbstractQueuedSynchronizer() {
		state = new AtomicInteger(0);
	}
	
	//4
	@Override
	protected boolean tryAcquire(int arg) {
		return state.compareAndSet(0, 1);
	}

	//5
	@Override
	protected boolean tryRelease(int arg) {
		return state.compareAndSet(1, 0);
	}

}
