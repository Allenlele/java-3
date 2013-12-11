package com.wds.java.concurrency.chapter7.tenth;

/**
 * 8
 * @author wds
 *
 */
public class Sensor1 implements Runnable {

	//9
	private ParkingCounter counter;
	
	//10
	public Sensor1(ParkingCounter counter) {
		this.counter = counter;
	}
	
	//11
	@Override
	public void run() {
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carOut();
		counter.carOut();
		counter.carOut();
		counter.carIn();
		counter.carIn();
		counter.carIn();
	}

}
